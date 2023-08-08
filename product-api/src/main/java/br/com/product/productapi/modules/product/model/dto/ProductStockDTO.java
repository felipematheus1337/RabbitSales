package br.com.product.productapi.modules.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductStockDTO {


    private String salesId;

    private List<ProductQuantityDTO> products;
}
