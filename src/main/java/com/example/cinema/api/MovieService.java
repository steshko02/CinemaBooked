package com.example.cinema.api;

import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.model.Movie;

import java.util.List;

public interface MovieService {

    void save(Movie movie);

    Movie getById(Long id);

    Long delete(Long id);

    Movie update(Long id, Movie convert);

    List<Movie> getAll();

    Movie getBest();
}
