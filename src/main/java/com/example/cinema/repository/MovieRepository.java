package com.example.cinema.repository;

import com.example.cinema.model.Movie;
import com.example.cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findTopByOrderByRatingDesc();

    @Query("select distinct m from MovieShow m inner join m.movie mov where mov.id=:id")
    List<MovieShow> findByMovie(@Param("id")  Long id);

}
