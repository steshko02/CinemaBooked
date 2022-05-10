package com.example.cinema.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer duration;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String description;

    private Float rating;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String imgUrl;

    private String trailerUrl;

    @ManyToMany
    @JoinTable(name="movie_genre", joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy="movie", cascade = CascadeType.ALL)
    private Set<MovieShow> movieShows = new HashSet<>();

    @PrePersist
    public void addPositions() {
        movieShows.forEach(movies -> movies.setMovie(this));
    }

    @PreRemove
    public void removePositions() {
        movieShows.forEach(movies -> movies.setMovie(null));
    }

    public Movie() {
    }
}
