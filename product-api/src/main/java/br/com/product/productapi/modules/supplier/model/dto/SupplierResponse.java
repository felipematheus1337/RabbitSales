package br.com.product.productapi.modules.supplier.model.dto;


import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponse {


    private Integer id;
    private String description;

    public static SupplierResponse of(Supplier supplier) {
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }
}
