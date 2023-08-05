package br.com.product.productapi.modules.supplier.model;


import br.com.product.productapi.modules.category.model.Category;
import br.com.product.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@Table(name = "SUPPLIER")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;


    public static Supplier of(SupplierRequest request) {
        var supplier = new Supplier();
        BeanUtils.copyProperties(request, supplier);
        return supplier;
    }

}
