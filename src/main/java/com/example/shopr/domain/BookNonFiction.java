package com.example.shopr.domain;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;


@DiscriminatorValue(value = "NON_FICTION")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class BookNonFiction extends Book {
    @Enumerated
    private Subject subject;

}
