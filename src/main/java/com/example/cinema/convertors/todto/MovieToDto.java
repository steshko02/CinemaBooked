package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MovieToDto implements Converter<Movie, MovieDto> {

    @Override
    public MovieDto convert(Movie source) {
        MovieDto result = new MovieDto();
        result.setDuration(source.getDuration());
        result.setId(source.getId());
        result.setName(source.getName());
        result.setDescription(source.getDescription());
        result.setImgUrl(source.getImgUrl());
        result.setRating(source.getRating());
        result.setTrailerUrl(source.getTrailerUrl());
        result.setGenres(source.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        return result;
    }

}
