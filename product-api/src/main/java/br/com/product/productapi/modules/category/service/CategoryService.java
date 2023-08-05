package br.com.product.productapi.modules.category.service;


import br.com.product.productapi.config.exception.ValidationException;
import br.com.product.productapi.modules.category.controller.dto.CategoryRequest;
import br.com.product.productapi.modules.category.controller.dto.CategoryResponse;
import br.com.product.productapi.modules.category.controller.model.Category;
import br.com.product.productapi.modules.category.controller.repository.CategoryRepository;
import br.com.product.productapi.modules.category.dto.CategoryResponse;
import br.com.product.productapi.modules.supplier.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse save(CategoryRequest request) {
         var category = categoryRepository.save(Category.of(request));
         return CategoryResponse.of(category);
    }

    public Category findById(Integer id){
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for this id"));
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
           throw new ValidationException("The category description was not informed");
        }
    }

}
