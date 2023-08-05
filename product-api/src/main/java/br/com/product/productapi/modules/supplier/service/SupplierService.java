package br.com.product.productapi.modules.supplier.service;


import br.com.product.productapi.config.exception.ValidationException;
import br.com.product.productapi.modules.category.controller.dto.CategoryRequest;
import br.com.product.productapi.modules.category.controller.dto.CategoryResponse;
import br.com.product.productapi.modules.category.controller.model.Category;
import br.com.product.productapi.modules.category.controller.repository.CategoryRepository;
import br.com.product.productapi.modules.supplier.model.Supplier;
import br.com.product.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.product.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier findById(Integer id){
        return supplierRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for this id"));
    }


    public SupplierResponse save(SupplierRequest request) {
         var supplier = supplierRepository.save(Supplier.of(request));
         return CategoryResponse.of(supplier);
    }

    private void validateCategoryNameInformed(SupplierRequest request) {
        if (isEmpty(request.getDescription())) {
           throw new ValidationException("The category description was not informed");
        }
    }

}
