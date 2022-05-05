package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.HallService;
import com.example.cinema.api.MovieService;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.model.MovieShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DtoToMovieShow implements Converter<MovieShowDto, MovieShow> {

    @Autowired
    private MovieService movieService;
    @Autowired
    private HallService hallService;

    @Override
    public MovieShow convert(MovieShowDto source) {
        MovieShow movieShow = new MovieShow();
        movieShow.setId(source.getId());
        if(source==null){
            movieShow.setMovie(null);
        }else{
            movieShow.setMovie(movieService.getById(source.getMovie()));
        }
        if(source==null){
            movieShow.setHall(null);
        }else{
            movieShow.setHall(hallService.getById(source.getHall()));
        }
        movieShow.setDate(source.getDate());
        return movieShow;
    }
}
