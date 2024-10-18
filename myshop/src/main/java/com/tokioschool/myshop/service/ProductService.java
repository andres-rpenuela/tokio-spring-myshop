package com.tokioschool.myshop.service;

import com.tokioschool.myshop.domain.Product;

import java.util.Set;

/**
 * Service para gestión de productos
 */
public interface ProductService {

    Product add(Product product);
    Product update(Product product);
    void delete(String productName);
    void delete(Product product);

    void increasePrice(String category, float percentage);
    void decreasePrice(String category, float percentage);

    Product findById(long id);
    Product findByName(String name);
    Set<Product> findAll(boolean visible);
    Set<Product> findAllVisible();
    Set<Product> findAllNoVisible();
    Set<Product> findByPriceBetween(float minPrice, float maxPrice);
    Set<Product> findByCategory(String category);

}
