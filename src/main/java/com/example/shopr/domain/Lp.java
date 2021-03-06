package com.example.shopr.domain;


import com.example.shopr.domainenums.LpGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@DiscriminatorColumn(name = "articleType")
@DiscriminatorValue(value = "LP")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance
@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title" , "performer"}))

public class Lp extends Article{


    @Id
    @GeneratedValue
    Long id;
    private String performer;
    @Enumerated
    private LpGenre lpGenre;




}
