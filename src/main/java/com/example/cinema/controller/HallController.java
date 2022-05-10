package com.example.cinema.controller;

import com.example.cinema.dto.model.HallDto;
import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.service.interfaces.HallDtoService;
import com.example.cinema.dto.service.interfaces.MovieDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hall")
public class HallController {
    @Autowired
    private HallDtoService hallDtoService;

    @PostMapping("/save")
    public String save(@RequestBody HallDto hallDto){
        hallDtoService.save(hallDto);
        return "saved";
    }

    @GetMapping("/get")
    public HallDto get(@RequestParam("id") Long id){
        return  hallDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  hallDtoService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public HallDto edit(@RequestBody HallDto hallDto, @PathVariable Long id) throws EntityNotFoundException {
        return hallDtoService.update(hallDto, id);
    }

    @GetMapping("/getByShowId")
    public HallDto getByShowId(@RequestParam("id") Long id){
        return  hallDtoService.getByShowId(id);
    }

    @GetMapping("/getAll")
    public List<HallDto> getAll(){
        return  hallDtoService.getAll();
    }


}
