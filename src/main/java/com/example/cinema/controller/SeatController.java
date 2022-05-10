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

    @PostMapping("/saveWithRow")
    public String saveWithRow(@RequestBody SeatDto seat, @RequestParam("rowNum") Long rowNum,@RequestParam("hall") Long hall){
        seatDtoService.saveWithRow(seat,rowNum, hall);
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


    @DeleteMapping("/deleteMany")
    public Long[] deleteMany(@RequestParam("ids") Long[] ids){
        return  seatDtoService.deleteMany(ids);
    }

    @PostMapping("/setVIPMany")
    public Long[] setVIPMany(@RequestParam("ids") Long[] ids){
        return  seatDtoService.setVIPMany(ids);
    }
    @PutMapping("/edit/{id}")
    public SeatDto edit(@RequestBody SeatDto seat, @PathVariable Long id) throws EntityNotFoundException {
        return seatDtoService.update(seat, id);
    }

    @GetMapping("/getByType")
    public List<SeatDto> getByType(@RequestParam("type") SeatType type){
        return  seatDtoService.getByType(type);
    }

    @GetMapping("/getByTypeAndHall")
    public List<SeatDto> getByTypeAndHall(@RequestParam("type") SeatType type, @RequestParam("hall") Long hall){
        return  seatDtoService.getByTypeAndHall(type,hall);
    }
}
