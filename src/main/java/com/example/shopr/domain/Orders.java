package com.example.shopr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> bookList;

    @OneToMany
    private List<Lp> lpList;

    @OneToMany
    private List<Game> gamesList;

    private Date orderDate;
}
