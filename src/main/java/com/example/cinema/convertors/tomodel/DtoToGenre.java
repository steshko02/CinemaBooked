package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.MovieService;
import com.example.cinema.api.MovieShowService;
import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoToGenre implements Converter<GenreDto, Genre> {

    @Autowired
    private MovieService movieService;

    @Override
    public Genre convert(GenreDto source) {
        Genre genre = new Genre();
        genre.setId(source.getId());
        genre.setName(source.getName());
        genre.setMovies(source.getMovies().stream().map(m->movieService.getById(m)).collect(Collectors.toSet()));
        return genre;
    }
}
