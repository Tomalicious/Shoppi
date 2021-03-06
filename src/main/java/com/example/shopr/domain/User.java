package com.example.shopr.domain;


import com.example.shopr.domainenums.Auth;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "webUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;



    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String message;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    @OneToMany(orphanRemoval=true ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @Fetch(FetchMode.SELECT)
    private List<Orders> orderList;

    @ManyToMany
    List<Assessment> assessments;


}
