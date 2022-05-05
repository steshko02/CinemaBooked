package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.MovieService;
import com.example.cinema.api.MovieShowService;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.service.interfaces.MovieShowDtoService;
import com.example.cinema.model.Movie;
import com.example.cinema.model.MovieShow;
import com.example.cinema.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieShowDtoServiceImpl implements MovieShowDtoService {

    @Autowired
    private MovieShowService movieShowService;

    @Autowired
    private ConversionService conversionService;


    @Override
    public void save(MovieShowDto movie) {
        movieShowService.save(conversionService.convert(movie,MovieShow.class));
    }

    @Override
    public MovieShowDto getById(Long id) {
        MovieShow movieShow = movieShowService.getById(id);
        return conversionService.convert(movieShow, MovieShowDto.class);
    }

    @Override
    public Long delete(Long id) {
        return movieShowService.delete(id);
    }

    @Override
    public MovieShowDto update(MovieShowDto movie, Long id) {
        return conversionService.convert(
                movieShowService.update(id,conversionService.convert(movie, MovieShow.class)), MovieShowDto.class);

    }

    @Override
    public List<MovieShowDto> getByMovie(Long id) {
        return movieShowService.getByMovie(id).stream().
                map(m->conversionService.convert(m, MovieShowDto.class)).
                collect(Collectors.toList());
    }
}
