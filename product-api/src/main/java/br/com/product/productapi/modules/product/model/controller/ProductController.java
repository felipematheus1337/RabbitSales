package br.com.product.productapi.modules.product.model.controller;


import br.com.product.productapi.modules.category.controller.dto.CategoryRequest;
import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.product.model.dto.ProductRequest;
import br.com.product.productapi.modules.product.model.dto.ProductResponse;
import br.com.product.productapi.modules.product.model.service.CategoryService;
import br.com.product.productapi.modules.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
      return productService.save(request);
    }
}
