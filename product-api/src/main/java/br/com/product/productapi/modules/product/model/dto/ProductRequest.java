package br.com.product.productapi.modules.product.model.dto;


import br.com.product.productapi.modules.category.dto.CategoryResponse;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import lombok.Data;

@Data
public class ProductRequest {

    private String description;
    private String name;
    private Integer quantityAvailable;
    private Integer supplierId;
    private Integer categoryId;
}
