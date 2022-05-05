package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.model.Movie;

import java.util.List;

public interface MovieDtoService {
    void save(MovieDto movie);

    MovieDto getById(Long id);

    Long delete(Long id);

    MovieDto update(MovieDto movie, Long id);

    List<MovieDto> getAll();

    MovieDto getBest();
}
