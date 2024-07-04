package com.hsgumussoy.javaodev2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int count;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "basket_id")//Bir varlık sınıfında, başka bir varlık sınıfına ait bir alanı (foreign key) tanımlamak için kullanılır.
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
