package com.example.shopr.domain;


import com.example.shopr.domainenums.GameGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@DiscriminatorColumn(name = "articleType")
@DiscriminatorValue(value = "GAME")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance
@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title" , "publisher"}))
public class Game extends Article {

    private int minimumAge;
    @Enumerated(EnumType.STRING)

    private GameGenre gameGenre;


}
