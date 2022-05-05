package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.model.MovieShow;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class MovieShowToDto implements Converter<MovieShow, MovieShowDto> {
    @Override
    public MovieShowDto convert(MovieShow source) {
        MovieShowDto result = new MovieShowDto();
        result.setId(source.getId());
        result.setMovie(source.getMovie().getId());
        result.setHall(source.getHall().getId());
        result.setDate(source.getDate());
        return result;
    }
}
