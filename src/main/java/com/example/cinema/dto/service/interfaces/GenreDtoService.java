package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.MovieDto;

public interface GenreDtoService {
    void save(GenreDto movie);

    GenreDto getById(Long id);

    Long delete(Long id);

    GenreDto update(GenreDto genre, Long id);
}
