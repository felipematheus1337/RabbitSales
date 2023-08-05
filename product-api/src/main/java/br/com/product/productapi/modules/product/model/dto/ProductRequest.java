package br.com.product.productapi.modules.product.model.dto;


import br.com.product.productapi.modules.category.dto.CategoryResponse;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Integer quantityAvailable;
    private Integer supplierId;
    private Integer categoryId;
    private String description;

}
