package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.HallDto;
import com.example.cinema.model.Hall;
import com.example.cinema.model.Movie;
import com.example.cinema.model.MovieShow;
import com.example.cinema.model.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HallToDto implements Converter<Hall, HallDto> {

    @Override
    public HallDto convert(Hall source) {
        HallDto result = new HallDto();
        result.setId(source.getId());
        result.setPlaceName(source.getPlaceName());
        result.setSeatsCount(source.getSeatsCount());
        result.setRows(source.getRows().stream().map(Row::getId).collect(Collectors.toSet()));
        result.setShows(source.getShows().stream().map(MovieShow::getId).collect(Collectors.toSet()));

        return result;
    }
}
