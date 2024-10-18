package com.tokioschool.myshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Un producto del catálogo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private float price;
    @Column
    private float discount;
    @Column
    private float taxes;
    @Column
    private boolean visible;
    @Column
    private int stock;
    @Column
    private String image;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }

    public void increasePrice(float percentage) {
        price *= percentage;
    }

    public void decreasePrice(float percentage) {
        price /= percentage;
    }
}
