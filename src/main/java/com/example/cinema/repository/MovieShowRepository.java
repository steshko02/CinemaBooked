package com.example.cinema.repository;

import com.example.cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<MovieShow,Long> {
}
