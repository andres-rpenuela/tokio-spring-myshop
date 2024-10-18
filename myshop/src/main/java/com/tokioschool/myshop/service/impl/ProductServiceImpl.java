package com.tokioschool.myshop.service.impl;

import com.tokioschool.myshop.domain.Product;
import com.tokioschool.myshop.repository.ProductRepository;
import com.tokioschool.myshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String productName) {
        Product product = productRepository.findByName(productName);
        productRepository.delete(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void increasePrice(String category, float percentage) {
        Set<Product> products = productRepository.findByCategory(category);
        products.forEach(product -> product.increasePrice(percentage));
    }

    @Override
    public void decreasePrice(String category, float percentage) {
        Set<Product> products = productRepository.findByCategory(category);
        products.forEach(product -> product.decreasePrice(percentage));
    }

    @Override
    public Product findById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(new Product());
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public Set<Product> findAll(boolean visible) {
        return productRepository.findByVisible(visible);
    }

    @Override
    public Set<Product> findAllVisible() {
        return findAll(true);
    }

    @Override
    public Set<Product> findAllNoVisible() {
        return findAll(false);
    }

    @Override
    public Set<Product> findByPriceBetween(float minPrice, float maxPrice) {
        return null;
    }

    @Override
    public Set<Product> findByCategory(String category) {
        return null;
    }
}
