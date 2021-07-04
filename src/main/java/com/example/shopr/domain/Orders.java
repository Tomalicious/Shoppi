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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_fiction_list_id")
    private List<BookFiction > bookFictionList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_non_list_id")
    private List<BookNonFiction> bookNonList;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "games_list_id")
    private List<Lp> lpList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lp_list_id")
    private List<Game> gamesList;

    private ZonedDateTime orderDate;

    private Boolean isPayed = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User users;

}
