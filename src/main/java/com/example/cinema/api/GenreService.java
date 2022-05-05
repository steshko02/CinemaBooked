package com.example.cinema.api;

import com.example.cinema.model.Genre;

public interface GenreService {

    void save(Genre genre);

    Genre getById(Long id);

    Long delete(Long id);

    Genre update(Long id, Genre genre);
}
