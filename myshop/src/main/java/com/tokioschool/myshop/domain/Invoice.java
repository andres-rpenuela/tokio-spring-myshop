package com.tokioschool.myshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Set;

/**
 * Una factura de un pedido
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String number;
    @Column
    private String nif;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column
    private String address;
    @Column
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private float subtotal;
    @Column
    private float taxes;
    @Column
    private float total;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Invoice{" +
                "number='" + number + '\'' +
                '}';
    }
}
