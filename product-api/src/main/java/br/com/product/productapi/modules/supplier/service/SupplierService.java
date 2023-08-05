package br.com.product.productapi.modules.supplier.service;


import br.com.product.productapi.config.exception.SuccessResponse;
import br.com.product.productapi.config.exception.ValidationException;
import br.com.product.productapi.modules.product.model.repository.ProductRepository;
import br.com.product.productapi.modules.product.model.service.ProductService;
import br.com.product.productapi.modules.supplier.model.Supplier;
import br.com.product.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.product.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductService productService;

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
    }

    public List<SupplierResponse> findAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }


    public List<SupplierResponse> findByName(String description) {
        return supplierRepository
                .findByNameIgnoreCaseContaining(description)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }
    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    public SupplierResponse update(SupplierRequest request,
                                   Integer id) {
        var supplier = Supplier.of(request);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return SupplierResponse.of(supplier);
    }

    public Supplier findById(Integer id){
        validateInformedId(id);
        return supplierRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for this id"));
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getDescription())) {
           throw new ValidationException("The supplier name was not informed");
        }
    }

    public SuccessResponse delete(Integer id) {
            validateInformedId(id);
            if (productService.existsBySupplierId(id)) {
                throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
            }
            supplierRepository.deleteById(id);
            return SuccessResponse.create("The supplier was deleted.");

    }

    private void validateInformedId(Integer id) {
            if (isEmpty(id)) {
                throw new ValidationException("The supplier ID was not informed");
            }
    }

}
