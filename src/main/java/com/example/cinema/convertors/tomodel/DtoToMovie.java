package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.GenreService;
import com.example.cinema.api.MovieShowService;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class DtoToMovie implements Converter<MovieDto, Movie> {

    @Autowired
    private GenreService genreService;

//    @Autowired
//    private MovieShowService movieShowService;

    @Override
    public Movie convert(MovieDto source) {

        Movie movie = new Movie();
        movie.setId(source.getId());
        movie.setName(source.getName());
        movie.setDuration(source.getDuration());
        movie.setDescription(source.getDescription());
        movie.setImgUrl(source.getImgUrl());
        movie.setRating(source.getRating());
        movie.setTrailerUrl(source.getTrailerUrl());
        movie.setGenres(source.getGenres().stream().map(g->genreService.getById(g)).collect(Collectors.toSet()));
//        movie.setMovieShows(source.getMovieShows().stream().map(m->movieShowService.getById(m)).collect(Collectors.toSet()));

        return movie;
    }
}
