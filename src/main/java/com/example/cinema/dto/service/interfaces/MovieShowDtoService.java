package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.MovieShowDto;

import java.util.List;

public interface MovieShowDtoService {

    void save(MovieShowDto movie);

    MovieShowDto getById(Long id);

    Long delete(Long id);

    MovieShowDto update(MovieShowDto movie, Long id);

    List<MovieShowDto> getByMovie(Long id);
}
