package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.MovieService;
import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.service.interfaces.MovieDtoService;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieDtoServiceImpl implements MovieDtoService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public void save(MovieDto movie) {
        movieService.save(conversionService.convert(movie,Movie.class));
    }

    @Override
    public MovieDto getById(Long id) {
        Movie movie = movieService.getById(id);
        return conversionService.convert(movie, MovieDto.class);
    }

    @Override
    public Long delete(Long id) {
        return movieService.delete(id);
    }

    @Override
    public MovieDto update(MovieDto movie, Long id) {
        return conversionService.convert(
                movieService.update(id,conversionService.convert(movie, Movie.class)), MovieDto.class);
    }

    @Override
    public List<MovieDto> getAll() {
        return movieService.getAll().stream().map(m->conversionService.convert(m, MovieDto.class)).collect(Collectors.toList());
    }

    @Override
    public MovieDto getBest() {
        return conversionService.convert(movieService.getBest(),MovieDto.class);
    }
}
