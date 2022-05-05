package com.example.cinema.api;

import com.example.cinema.model.MovieShow;

import java.util.List;


public interface MovieShowService {

    void save(MovieShow movie);

    MovieShow getById(Long id);

    Long delete(Long id);

    MovieShow update(Long id, MovieShow convert);

    List<MovieShow> getByMovie(Long id);
}
