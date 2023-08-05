package br.com.product.productapi.modules.category.dto;



import br.com.product.productapi.modules.category.model.Category;
import br.com.product.productapi.modules.supplier.model.dto.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {


    private Integer id;
    private String description;

    private SupplierResponse supplier;
    private CategoryResponse category;

    public static CategoryResponse of(Category category) {
        var response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        return response;
    }
}
