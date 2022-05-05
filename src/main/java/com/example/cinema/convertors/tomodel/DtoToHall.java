package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.MovieShowService;
import com.example.cinema.api.RowService;
import com.example.cinema.dto.model.HallDto;
import com.example.cinema.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoToHall implements Converter<HallDto, Hall> {

    @Autowired
    private RowService rowService;

    @Autowired
    private MovieShowService movieShowService;

    @Override
    public Hall convert(HallDto source) {
        Hall result = new Hall();
        result.setId(source.getId());
        result.setPlaceName(source.getPlaceName());
        result.setSeatsCount(source.getSeatsCount());
        result.setRows(source.getRows().stream().map(m->rowService.getById(m)).collect(Collectors.toSet()));;
        result.setShows(source.getShows().stream().map(m->movieShowService.getById(m)).collect(Collectors.toSet()));;
        return result;
    }
}
