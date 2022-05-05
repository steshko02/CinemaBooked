package com.example.cinema.controller;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.service.interfaces.GenreDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/genre")
public class GenreController {

    @Autowired
    private GenreDtoService genreDtoService;

    @PostMapping("/save")
    public String save(@RequestBody GenreDto genre){
        genreDtoService.save(genre);
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public GenreDto edit(@RequestBody GenreDto genre, @PathVariable Long id) throws EntityNotFoundException {
        return genreDtoService.update(genre, id);
    }

    @GetMapping("/get")
    public GenreDto get(@RequestParam("id") Long id){
        return  genreDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  genreDtoService.delete(id);
    }

}
