package br.com.product.productapi.modules.product.model.service;


import br.com.product.productapi.config.exception.ValidationException;
import br.com.product.productapi.modules.category.controller.dto.CategoryRequest;
import br.com.product.productapi.modules.category.controller.model.Category;
import br.com.product.productapi.modules.category.service.CategoryService;
import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.product.model.dto.ProductRequest;
import br.com.product.productapi.modules.product.model.dto.ProductResponse;
import br.com.product.productapi.modules.product.model.repository.ProductRepository;
import br.com.product.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, supplier, category));
        return ProductResponse.of(product);
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
            throw new ValidationException("The product's name was not informed");
        }

        if (isEmpty(request.getSupplierId()Id())) {
            throw new ValidationException("The product's name was not informed");
        }
    }

}