package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.model.RowDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RowDtoService {

    void save(RowDto row);

    RowDto getById(Long id);

    Long delete(Long id);

    RowDto update(RowDto row, Long id);

    List<RowDto> getByHall(Long id);

    RowDto getByNumber(Integer number);
}
