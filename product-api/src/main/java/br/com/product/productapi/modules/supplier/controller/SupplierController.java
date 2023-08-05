package br.com.product.productapi.modules.supplier.controller;



import br.com.product.productapi.config.exception.SuccessResponse;
import br.com.product.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.product.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
      return supplierService.save(request);
    }

    @GetMapping
    public List<SupplierResponse> findAll() {
        return supplierService.findAll();
    }
    @GetMapping("{id}")
    public SupplierResponse findById(@PathVariable(name = "id") Integer id) {
        return supplierService.findByIdResponse(id);
    }

    @GetMapping("name/{name}")
    public List<SupplierResponse> findByName(@PathVariable(name = "name") String name) {
        return supplierService.findByName(name);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }

    @PutMapping("{id}")
    public SupplierResponse update(@RequestBody SupplierRequest request,@PathVariable Integer id) {
        return supplierService.update(request, id);
    }
}
