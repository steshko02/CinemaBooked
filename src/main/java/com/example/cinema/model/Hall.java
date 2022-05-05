package com.example.cinema.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Hall {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String placeName;

    private Long seatsCount;

    @OneToMany(mappedBy="hall",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Row> rows = new HashSet<>();

    @OneToMany(mappedBy="hall",fetch = FetchType.LAZY)
    private Set<MovieShow> shows = new HashSet<>();

    public Hall() {
    }
}
