package br.com.product.productapi.modules.product.model.repository;

import br.com.product.productapi.modules.product.model.Product;
import br.com.product.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findBySupplierId(Integer supplierId);

    Boolean existsByCategoryId(Integer categoryId);

    Boolean existsBySupplierId(Integer supplierId);
}
