package com.example.shopr.domain;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@DiscriminatorValue(value = "NON FICTION")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Embeddable
public class BookNonFiction extends Book {
    @Enumerated(EnumType.STRING)
    private Subject subject;
    private String description;



}
