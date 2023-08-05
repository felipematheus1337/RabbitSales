package br.com.product.productapi.modules.category.controller.repository;

import br.com.product.productapi.modules.category.controller.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
