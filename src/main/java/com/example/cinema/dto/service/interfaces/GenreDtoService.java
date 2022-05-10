package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.MovieDto;

import java.util.List;

public interface GenreDtoService {
    void save(GenreDto movie);

    GenreDto getById(Long id);

    Long delete(Long id);

    GenreDto update(GenreDto genre, Long id);

    List<GenreDto> getAll();

    List<GenreDto> getByMovie(Long id);
}
