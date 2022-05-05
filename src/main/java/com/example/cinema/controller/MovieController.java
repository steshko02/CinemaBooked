package com.example.cinema.controller;

import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.service.interfaces.MovieDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {

    @Autowired
    private MovieDtoService movieService;

    @PostMapping("/save")
    public String save(@RequestBody MovieDto movie){
        movieService.save(movie);
        return "saved";
    }

    @GetMapping("/get")
    public MovieDto get(@RequestParam("id") Long id){
        return  movieService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  movieService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public MovieDto edit(@RequestBody MovieDto movie, @PathVariable Long id) throws EntityNotFoundException {
        return movieService.update(movie, id);
    }

    @GetMapping("/getAll")
    public List<MovieDto> getAll(){
        return  movieService.getAll();
    }

    @GetMapping("/bestMovie")
    public MovieDto getBest(){
        return  movieService.getBest();
    }

}
