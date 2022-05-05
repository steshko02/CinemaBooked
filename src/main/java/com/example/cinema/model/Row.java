package com.example.cinema.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "RowsTable")
public class Row {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy="row",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Seat> seats = new HashSet<>();

    public Row() {
    }
}
