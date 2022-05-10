package com.example.cinema.controller;

import com.example.cinema.dto.model.MovieDto;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.service.interfaces.RowDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/row")
public class RowController {

    @Autowired
    private RowDtoService rowDtoService;

    @PostMapping("/save")
    public String save(@RequestBody RowDto row){
        rowDtoService.save(row);
        return "saved";
    }

    @GetMapping("/getByNumber")
    public RowDto get(@RequestParam("num") Integer number){
        return  rowDtoService.getByNumber(number);
    }


    @GetMapping("/get")
    public RowDto get(@RequestParam("id") Long id){
        return  rowDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  rowDtoService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public RowDto edit(@RequestBody RowDto row, @PathVariable Long id) throws EntityNotFoundException {
        return rowDtoService.update(row, id);
    }

    @GetMapping("/getRowByHall")
    public List<RowDto> getByHall(@RequestParam("id") Long id){
        return  rowDtoService.getByHall(id);
    }

}

