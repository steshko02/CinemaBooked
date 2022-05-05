package com.example.cinema.controller;

import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.service.interfaces.BookingDtoService;
import com.example.cinema.dto.service.interfaces.GenreDtoService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

    @Autowired
    private BookingDtoService bookingDtoService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody BookingDto booking) {
        bookingDtoService.save(booking);
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public BookingDto edit(@RequestBody BookingDto booking, @PathVariable Long id) throws EntityNotFoundException {
        return bookingDtoService.update(booking, id);
    }

    @GetMapping("/get")
    public BookingDto get(@RequestParam("id") Long id){
        return  bookingDtoService.getById(id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam("id") Long id){
        return  bookingDtoService.delete(id);
    }

    @GetMapping("/getByShow")
    public List<BookingDto> getByShow(@RequestParam("id") Long id){
        return  bookingDtoService.getByShow(id);
    }

    @GetMapping("/getByUser")
    public List<BookingDto> getByUser(@RequestParam("id") Long id){
        return  bookingDtoService.getByUser(id);
    }

    @GetMapping("/getByUserAndShow")
    public List<BookingDto> getByUserAndShow(@RequestParam("user") Long user,@RequestParam("show") Long show){
        return  bookingDtoService.getByUserAndShow(user,show);
    }

}
