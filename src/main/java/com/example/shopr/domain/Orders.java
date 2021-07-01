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


    @ManyToMany()
    private List<BookFiction > bookFictionList;

    @ManyToMany()
    private List<BookNonFiction> bookNonList;


    @ManyToMany()
    private List<Lp> lpList;

    @ManyToMany()
    private List<Game> gamesList;

    private ZonedDateTime orderDate;

    private Boolean isPayed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

}
