package com.example.cinema.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="genres", cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Movie> movies = new HashSet<>();

    @PrePersist
    public void addPositions() {
        movies.forEach(movies -> movies.getGenres().add(this));
    }

    @PreRemove
    public void removePositions() {
        movies.forEach(movies -> movies.getGenres().remove(this));
    }


    public Genre() {
    }
}
