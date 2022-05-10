package com.example.cinema.api;

import com.example.cinema.model.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreService {

    void save(Genre genre);

    Genre getById(Long id);

    Long delete(Long id);

    Genre update(Long id, Genre genre);

    List<Genre> getAll();

    List<Genre> getByMovie(Long id);
}
