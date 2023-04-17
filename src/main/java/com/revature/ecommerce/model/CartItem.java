package com.revature.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private EcommerceProduct product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EcommerceUser user;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private CustomerOrder customerOrder;

}
