package com.example.cinema.repository;


import com.example.cinema.model.Hall;
import com.example.cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

//    @Query("select distinct m from Hall m inner join m.movie mov where mov.id=:id")
//    Hall getByShowId(@Param("id")  Long id);
}
