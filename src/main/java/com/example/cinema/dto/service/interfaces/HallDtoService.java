package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.HallDto;

import java.util.List;

public interface HallDtoService {
    void save(HallDto hallDto);

    HallDto getById(Long id);

    Long delete(Long id);

    HallDto update(HallDto hallDto, Long id);

    HallDto getByShowId(Long id);

    List<HallDto> getAll();
}
