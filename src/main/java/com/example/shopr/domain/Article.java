package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@MappedSuperclass
@DiscriminatorColumn(name= "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false , nullable = false)
    private String type;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Double price;
    private String publisher;
    private String supplierId;
    private Integer stock;
    private Integer orderQuantity;
    private Long order_id;

}
