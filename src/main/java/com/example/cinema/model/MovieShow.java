package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class MovieShow {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany( mappedBy="movieShow",cascade={CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Booking> bookings = new HashSet<>();


    @PrePersist
    public void addPositions() {
        bookings.forEach(movies -> movies.setMovieShow(this));
    }

    @PreRemove
    public void removePositions() {
        bookings.forEach(movies -> movies.setMovieShow(null));
    }


    public MovieShow() {
    }
}
