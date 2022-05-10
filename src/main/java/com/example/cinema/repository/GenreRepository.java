package com.example.cinema.repository;

import com.example.cinema.model.Genre;
import com.example.cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {

//    @Query("select distinct m from Genre m inner join m.movies mov where mov.id=:id")
//    List<Genre> getByMovie(@Param("id")  Long id);

}
