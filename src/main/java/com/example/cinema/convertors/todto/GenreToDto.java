package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import com.example.cinema.model.MovieShow;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GenreToDto implements Converter<Genre, GenreDto> {
    @Override
    public GenreDto convert(Genre source) {
        GenreDto result = new GenreDto();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setMovies(source.getMovies().stream().map(Movie::getId).collect(Collectors.toSet()));

        return result;
    }
}
