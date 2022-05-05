package com.example.cinema.service;

import com.example.cinema.api.MovieShowService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.*;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieShowServiceImpl implements MovieShowService {

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void save(MovieShow movie) {
        movieShowRepository.save(movie);
    }

    @Override
    public MovieShow getById(Long id) {
        return movieShowRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Long delete(Long id) {
        movieShowRepository.deleteById(id);
        return id;
    }

    @Override
    public MovieShow update(Long id, MovieShow show) {
        MovieShow movieShowForEdit = movieShowRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistHall(show.getHall()) && checkExistMovie(show.getMovie())){
            movieShowForEdit.setDate(show.getDate());

            Hall hall = show.getHall();
            movieShowForEdit.setHall(hall);

            movieShowForEdit.setMovie(show.getMovie());

            save(movieShowForEdit);
            return movieShowForEdit;
        }
        throw new EntityNotFoundException(Row.class, "id", id.toString());

    }

    @Override
    public List<MovieShow> getByMovie(Long id) {
        return movieRepository.findByMovie(id);
    }

    private boolean checkExistMovie(Movie movie) {
        if(movie==null){

        } else if(!movieRepository.existsById(movie.getId()))
            throw new EntityNotFoundException(Movie.class, "id", movie.getId().toString());
        return true;
    }

    private boolean checkExistHall(Hall hall) {
        if(hall==null){

        } else if(!hallRepository.existsById(hall.getId()))
            throw new EntityNotFoundException(Hall.class, "id", hall.getId().toString());
        return true;
    }

}
