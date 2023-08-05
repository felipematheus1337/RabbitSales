package br.com.product.productapi.modules.product.model.dto;


import br.com.product.productapi.modules.category.controller.model.Category;
import br.com.product.productapi.modules.category.dto.CategoryResponse;
import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class ProductResponse {


    private Integer id;
    private String description;
    private integer quantityAvailable;
    private SupplierResponse supplier;
    private CategoryResponse category;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public static ProductResponse of(Product product) {
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .quantityAvailable(product.getQuantityAvailable())
                .createdAt(product.getCreatedAt())
                .supplier(SupplierResponse.of(product.getSupplier()))
                .category(CategoryResponse.of(product.getCategory()))
                .build();
    }
}
