package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode
@DiscriminatorColumn(name ="type")
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String isbn;
    private Integer pages;

}
