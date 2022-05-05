package com.example.cinema.controller;


import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.dto.service.interfaces.RowDtoService;
import com.example.cinema.dto.service.interfaces.SeatDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.modeltypes.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/seat")
public class SeatController {

    @Autowired
    private SeatDtoService seatDtoService;

    @PostMapping("/save")
    public String save(@RequestBody SeatDto seat){
        seatDtoService.save(seat);
        return "saved";
    }

    @GetMapping("/get")
    public SeatDto get(@RequestParam("id") Long id){
        return  seatDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  seatDtoService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public SeatDto edit(@RequestBody SeatDto seat, @PathVariable Long id) throws EntityNotFoundException {
        return seatDtoService.update(seat, id);
    }

    @GetMapping("/getByType")
    public List<SeatDto> getByType(@RequestParam("type") SeatType type){
        return  seatDtoService.getByType(type);
    }

}
