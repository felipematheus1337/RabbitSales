package br.com.product.productapi.modules.product.model.service;


import br.com.product.productapi.config.exception.SuccessResponse;
import br.com.product.productapi.config.exception.ValidationException;
import br.com.product.productapi.modules.category.service.CategoryService;
import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.product.model.dto.*;
import br.com.product.productapi.modules.product.model.repository.ProductRepository;
import br.com.product.productapi.modules.sales.client.SalesClient;
import br.com.product.productapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.product.productapi.modules.sales.enums.SalesStatus;
import br.com.product.productapi.modules.sales.rabbitmq.SalesConfirmationSender;
import br.com.product.productapi.modules.supplier.model.Supplier;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.product.productapi.modules.supplier.service.SupplierService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@Slf4j
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SalesConfirmationSender salesConfirmationSender;

    @Autowired
    private SalesClient salesClient;

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, supplier, category));
        return ProductResponse.of(product);
    }

    public ProductResponse update(ProductRequest request,
                                Integer id) {
        validateProductDataInformed(request);
        validateInformedId(id);
        validateCategoryAndSupplierInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = Product.of(request, supplier, category);
        product.setId(id);
        productRepository.save(product);
        return ProductResponse.of(product);
    }

    public Product findById(Integer id){
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for this id"));
    }

    public List<ProductResponse> findBySupplierId(Integer id) {
        return productRepository
                .findBySupplierId(id)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name) {
        return productRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByCategoryId(Integer id) {
        return productRepository
                .findByCategoryId(id)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    private void validateProductDataInformed(ProductRequest request) {
        if (isEmpty(request.getDescription())) {
           throw new ValidationException("The product's name was not informed");
        }

        if (isEmpty(request.getQuantityAvailable())) {
            throw new ValidationException("The product's quantity was not informed");
        }

        if (isEmpty(request.getQuantityAvailable() <= ZERO)) {
            throw new ValidationException("The quantity should not be equal or less than zero");
        }

    }

    private void validateCategoryAndSupplierInformed(ProductRequest request) {
        if (isEmpty(request.getCategoryId())) {
            throw new ValidationException("The category ID was not informed");
        }

        if (isEmpty(request.getSupplierId())) {
            throw new ValidationException("The supplier ID name was not informed");
        }
    }

    public List<ProductResponse> findAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        productRepository.deleteById(id);
        return SuccessResponse.create("The product was deleted.");

    }

    public Boolean existsByCategoryId(Integer categoryId) {
      return productRepository.existsByCategoryId(categoryId);
    }

    public Boolean existsBySupplierId(Integer supplierId) {
        return productRepository.existsBySupplierId(supplierId);
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed");
        }
    }

    public void updateProductStock(ProductStockDTO productStockDTO) {
        try {
            validateStockUpdateData(productStockDTO);
            updateStock(productStockDTO);
        } catch (Exception e) {
            log.error("Erro while trying to update stock for message with error: {}", e.getMessage(), e);
            var rejectedMessage = new SalesConfirmationDTO(productStockDTO.getSalesId(), SalesStatus.REJECTED, productStockDTO.getTransactionId());
            salesConfirmationSender.sendSalesConfirmationMessage(rejectedMessage);

        }
    }

    @Transactional
    private void updateStock(ProductStockDTO productStockDTO) {
        var productsForUpdate = new ArrayList<Product>();

        productStockDTO
                .getProducts()
                .forEach(salesProduct -> {
                    var existingProduct = findById(salesProduct.getProductId());
                    validateQuantityInStock(salesProduct, existingProduct);
                    existingProduct.updateStock(salesProduct.getQuantity());
                    productsForUpdate.add(existingProduct);
                });

        if (!isEmpty(productsForUpdate)) {
            productRepository.saveAll(productsForUpdate);
            var approvedMessage = new SalesConfirmationDTO(productStockDTO.getSalesId(), SalesStatus.APPROVED);
            salesConfirmationSender.sendSalesConfirmationMessage(approvedMessage);
        }

    }

    @Transactional
    private void validateStockUpdateData(ProductStockDTO productStockDTO) {
      if (isEmpty(productStockDTO) || isEmpty(productStockDTO.getSalesId())) {
          throw new ValidationException("The product data or sales ID cannot be null.");
      }
      if (isEmpty(productStockDTO.getProducts())) {
          throw new ValidationException("The sale's product must be informed.");
      }
        productStockDTO.getProducts()
                .forEach(salesProduct -> {
                    if(isEmpty(salesProduct.getProductId())) {
                        throw new ValidationException("The productID and the quantity must be informed.");
                    }
                });
    }

    private void validateQuantityInStock(ProductQuantityDTO salesProduct, Product existingProduct) {
        if (salesProduct.getQuantity() > existingProduct.getQuantityAvailable()) {
            throw new ValidationException(
                    String.format("The product %s is out of stock.", existingProduct.getId()));
        }
    }

    public ProductSalesResponse findProductSales(Integer id) {
        var product = findById(id);
        try {
            var sales = salesClient.findSalesByProductId(product.getId())
                    .orElseThrow(() -> new ValidationException("The sale was not found for this product"));
            return ProductSalesResponse.of(product, sales.getSalesId());
        } catch(Exception e) {
            e.printStackTrace();
            throw new ValidationException("There was an error trying to get the product.");
        }
    }
}
