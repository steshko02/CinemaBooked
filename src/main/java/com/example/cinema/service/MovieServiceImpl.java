package com.example.cinema.service;

import com.example.cinema.api.MovieService;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import com.example.cinema.model.MovieShow;
import com.example.cinema.repository.GenreRepository;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieShowRepository movieShowRepository;

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie getById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return movie;
    }

    @Override
    public Long delete(Long id) {
         movieRepository.deleteById(id);
         return id;
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie movieForEdit = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistGenres(movie.getGenres()) && checkExistMovieShows(movie.getMovieShows())){

            movieForEdit.setName(movie.getName());

            Set<Genre> genres = getExistGenres(movie);
//            Set<MovieShow> movieShows = getExistMovieShows(movie);

//            genres.stream().forEach(m->m.getMovies().add(movieForEdit));
//            movieShows.stream().forEach(m->m.setMovie(movieForEdit));

//            movieForEdit.setMovieShows(movieShows);
            movieForEdit.setGenres(genres);

            movieRepository.save(movieForEdit);
            return movieForEdit;
        }
        throw new EntityNotFoundException(Genre.class, "id", id.toString());
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getBest() {
        return movieRepository.findTopByOrderByRatingDesc();
    }


    private boolean checkExistGenres(Set<Genre> genres){
        for (Genre m: genres) {
            if(!genreRepository.existsById(m.getId()))
                throw new EntityNotFoundException(Movie.class, "id", m.getId().toString());
        }
        return true;
    }

    private boolean checkExistMovieShows(Set<MovieShow> movieShows){
        for (MovieShow m: movieShows) {
            if(!movieShowRepository.existsById(m.getId()))
                throw new EntityNotFoundException(Movie.class, "id", m.getId().toString());
        }
        return true;
    }

    private Set<Genre>  getExistGenres(Movie movie){
        return movie.getGenres().stream().
                map(g->genreRepository.getById(g.getId())).collect(Collectors.toSet());
    }

    private Set<MovieShow> getExistMovieShows(Movie movie) {
        return movie.getMovieShows().stream().
                map(g->movieShowRepository.getById(g.getId())).collect(Collectors.toSet());
    }

}
