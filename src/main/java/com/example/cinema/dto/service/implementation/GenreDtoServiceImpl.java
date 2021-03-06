package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.GenreService;
import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.service.interfaces.GenreDtoService;
import com.example.cinema.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreDtoServiceImpl implements GenreDtoService{

    @Autowired
    private GenreService genreService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public void save(GenreDto genre) {
        genreService.save(conversionService.convert(genre,Genre.class));
    }

    @Override
    public GenreDto getById(Long id) {
        Genre genre = genreService.getById(id);
        return conversionService.convert(genre, GenreDto.class);
    }

    @Override
    public Long delete(Long id) {
        return  genreService.delete(id);
    }

    @Override
    public GenreDto update(GenreDto genre, Long id) {
        return conversionService.convert(
                genreService.update(id,conversionService.convert(genre, Genre.class)), GenreDto.class);
    }

    @Override
    public List<GenreDto> getAll() {
        return genreService.getAll().stream().map(m->conversionService.convert(m, GenreDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<GenreDto> getByMovie(Long id) {
        return genreService.getByMovie(id).stream().map(m->conversionService.convert(m, GenreDto.class)).collect(Collectors.toList());
    }
}
