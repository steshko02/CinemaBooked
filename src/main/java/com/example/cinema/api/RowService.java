package com.example.cinema.api;

import com.example.cinema.dto.model.RowDto;
import com.example.cinema.model.Row;

import java.util.Collection;
import java.util.List;

public interface RowService {
    Row getById(Long id);

    Row update(Long id, Row convert);

    Long delete(Long id);

    void save(Row convert);

    List<Row> getByHall(Long id);

    Row getByNumber(Integer number);
}
