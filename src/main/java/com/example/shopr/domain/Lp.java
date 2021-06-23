package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "LP")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title" , "performer"}))

public class Lp extends Article{
    private String performer;
    @Enumerated
    private LpGenre lpGenre;

}
