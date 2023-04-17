package com.revature.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EcommerceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String imageUrl;

}
