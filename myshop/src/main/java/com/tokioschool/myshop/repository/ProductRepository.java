package com.tokioschool.myshop.repository;

import com.tokioschool.myshop.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    // Query Methods
    Product findByName(String name);
    Set<Product> findByVisible(boolean visible);
    Set<Product> findByPriceBetween(float minPrice, float maxPrice);
    Set<Product> findByCategory(String category);

    // Consulta JPQL
    //@Query("SELECT p FROM Product p WHERE p.stock > 0")
    //Set<Product> findStockProducts();

    // Consulta nativa SQL
    @Query(value = "SELECT * FROM products WHERE stock < 0", nativeQuery = true)
    Set<Product> findNoStockProducts();
}
