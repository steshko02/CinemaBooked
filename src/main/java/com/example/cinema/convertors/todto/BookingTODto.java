package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.model.Booking;
import com.example.cinema.model.Seat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookingTODto implements Converter<Booking, BookingDto> {
    @Override
    public BookingDto convert(Booking source) {

        BookingDto result = new BookingDto();
        result.setId(source.getId());
        result.setHall(source.getHall().getId());
        result.setMovieShow(source.getMovieShow().getId());
        result.setPrice(source.getPrice());
        result.setSeats(source.getSeats().stream().map(Seat::getId).collect(Collectors.toSet()));
        result.setUser(source.getUser().getId());
        return result;
    }
}
