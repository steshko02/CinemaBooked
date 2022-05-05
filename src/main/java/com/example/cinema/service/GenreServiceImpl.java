package com.example.cinema.service;

import com.example.cinema.api.GenreService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Genre;
import com.example.cinema.model.Movie;
import com.example.cinema.repository.GenreRepository;
import com.example.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Long delete(Long id) {
         genreRepository.deleteById(id);
         return id;
    }

    @Override
    public Genre update(Long id, Genre genre) {
        Genre genreForEdit = genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistMovies(genre.getMovies())){
            genreForEdit.setName(genre.getName());

            Set<Movie> movies = getExistMovies(genre);
            movies.stream().forEach(m->m.getGenres().add(genreForEdit));

            genreForEdit.setMovies(genre.getMovies());
            genreRepository.save(genreForEdit);
            return genreForEdit;
        }
        throw new EntityNotFoundException(Genre.class, "id", id.toString());
    }

    private boolean checkExistMovies(Set<Movie> movies){
        for (Movie m: movies) {
            if(!movieRepository.existsById(m.getId()))
                 throw new EntityNotFoundException(Movie.class, "id", m.getId().toString());
        }
        return true;
    }

    private Set<Movie>  getExistMovies(Genre genre){

        return genre.getMovies().stream().
                map(g->movieRepository.getById(g.getId())).collect(Collectors.toSet());
    }

}
