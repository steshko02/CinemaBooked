package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.HallService;
import com.example.cinema.api.MovieShowService;
import com.example.cinema.api.SeatService;
import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.model.Booking;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoToBooking implements Converter<BookingDto, Booking> {

    @Autowired
    private HallService hallService;

    @Autowired
    private MovieShowService movieShowService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private UserService userService;


    @Override
    public Booking convert(BookingDto source) {
        Booking result = new Booking();
        result.setId(source.getId());
        result.setPrice(source.getPrice());
        result.setHall(hallService.getById(source.getHall()));
        result.setMovieShow(movieShowService.getById(source.getMovieShow()));
        result.setSeats(source.getSeats().stream().map(m->seatService.getById(m)).collect(Collectors.toSet()));
        result.setUser(userService.findUserById(source.getUser()));
        return result;
    }
}
