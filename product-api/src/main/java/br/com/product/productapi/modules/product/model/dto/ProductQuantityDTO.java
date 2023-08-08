package br.com.product.productapi.modules.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductQuantityDTO {


    private Integer productId;
    private Integer quantity;



}
