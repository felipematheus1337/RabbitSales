package br.com.product.productapi.modules.sales.dto;

import br.com.product.productapi.modules.sales.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesConfirmationDTO {

    private String salesId;
    private SalesStatus status;
}
