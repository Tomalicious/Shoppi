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
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    private String type;
    private String title;
    private Double price;
    private String supplierId;


}
