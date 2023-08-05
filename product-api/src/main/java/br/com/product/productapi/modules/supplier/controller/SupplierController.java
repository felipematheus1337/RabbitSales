package br.com.product.productapi.modules.supplier.controller;


import br.com.product.productapi.modules.category.controller.dto.CategoryRequest;
import br.com.product.productapi.modules.category.controller.dto.CategoryResponse;
import br.com.product.productapi.modules.category.service.CategoryService;
import br.com.product.productapi.modules.supplier.model.Supplier;
import br.com.product.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.product.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
      return supplierService.save(request);
    }
}
