package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@DiscriminatorValue(value = "FICTION")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Embeddable
public class BookFiction extends Book{
    @Enumerated(EnumType.STRING)
    private BookGenre bookGenre;
    private String description;
}
