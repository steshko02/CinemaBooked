package com.example.cinema.controller;

import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.service.interfaces.MovieShowDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.MovieShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/show")
public class MovieShowController {

    @Autowired
    private MovieShowDtoService movieShowDtoService;

    @PostMapping("/save")
    public String save(@RequestBody MovieShowDto show){
        movieShowDtoService.save(show);
        return "saved";
    }

    @GetMapping("/get")
    public MovieShowDto get(@RequestParam("id") Long id){
        return  movieShowDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
            return  movieShowDtoService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public MovieShowDto edit(@RequestBody MovieShowDto movie, @PathVariable Long id) throws EntityNotFoundException {
        return movieShowDtoService.update(movie, id);
    }

    @GetMapping("/getByMovie")
    public List<MovieShowDto> getByMovie(@RequestParam("id") Long id){
        return  movieShowDtoService.getByMovie(id);
    }

    @GetMapping("/getAll")
    public List<MovieShowDto> getAll() {
        return  movieShowDtoService.getAll();
    }

}

