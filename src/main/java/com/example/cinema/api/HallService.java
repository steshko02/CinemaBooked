package com.example.cinema.api;

import com.example.cinema.model.Genre;
import com.example.cinema.model.Hall;

import java.util.Collection;
import java.util.List;

public interface HallService {
    void save(Hall convert);

    Hall getById(Long id);

    Long delete(Long id);

    Hall update(Long id, Hall hall);

    Hall getByShowId(Long id);

    List<Hall> getAll();
}
