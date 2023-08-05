package br.com.product.productapi.modules.product.model.controller;



import br.com.product.productapi.config.exception.SuccessResponse;
import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.product.model.dto.ProductRequest;
import br.com.product.productapi.modules.product.model.dto.ProductResponse;

import br.com.product.productapi.modules.product.model.service.ProductService;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
      return productService.save(request);
    }


    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }
    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable(name = "id") Integer id) {
        return ProductResponse.of(productService.findById(id));
    }

    @GetMapping("name/{name}")
    public List<ProductResponse> findByName(@PathVariable(name = "name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable(name = "categoryId") Integer id) {
        return productService.findByCategoryId(id);
    }

    @GetMapping("supplier/{supplierId}")
    public List<ProductResponse> findBySupplierId(@PathVariable(name = "supplierId") Integer id) {
        return productService.findBySupplierId(id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

    @PutMapping("{id}")
    public ProductResponse update(@RequestBody ProductRequest request,@PathVariable Integer id) {
        return productService.update(request,id);
    }
}
