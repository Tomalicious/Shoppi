package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

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
    private String articleType;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private Double price;
    private String publisher;
    @Column(length = 100)
    private String supplierId;
    private Integer stock;
    private Long order_id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "quantity_id")
    private Quantity orderQuantity;
}
