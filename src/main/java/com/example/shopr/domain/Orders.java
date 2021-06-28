package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookFiction > bookFictionList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookNonFiction> bookNonList;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lp> lpList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> gamesList;

    private ZonedDateTime orderDate;

    private Boolean isPayed;

}
