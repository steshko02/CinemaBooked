package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.GenreService;
import com.example.cinema.api.HallService;
import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.HallDto;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.service.interfaces.HallDtoService;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallDtoServiceImpl implements HallDtoService {

    @Autowired
    private HallService hallService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public void save(HallDto hallDto) {
        hallService.save(conversionService.convert(hallDto, Hall.class));
    }

    @Override
    public HallDto getById(Long id) {
        Hall hall = hallService.getById(id);
        return conversionService.convert(hall, HallDto.class);
    }

    @Override
    public Long delete(Long id) {
        return  hallService.delete(id);
    }

    @Override
    public HallDto update(HallDto hallDto, Long id) {
        return conversionService.convert(
                hallService.update(id,conversionService.convert(hallDto, Hall.class)), HallDto.class);
    }

    @Override
    public HallDto getByShowId(Long id) {
        return conversionService.convert(hallService.getByShowId(id), HallDto.class);
    }

    @Override
    public List<HallDto> getAll() {
         return hallService.getAll().stream().
                map(m->conversionService.convert(m, HallDto.class)).
                collect(Collectors.toList());
    }
}
