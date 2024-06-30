package com.hsgumussoy.javaodev2.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "basket_id")
    private Basket basket;


}
