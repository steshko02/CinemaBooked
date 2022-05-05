package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.BookingService;
import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.service.interfaces.BookingDtoService;
import com.example.cinema.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingDtoServiceImpl implements BookingDtoService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public void save(BookingDto booking) {
        bookingService.save(conversionService.convert(booking, Booking.class));
    }

    @Override
    public BookingDto update(BookingDto booking, Long id) {
        return conversionService.convert(
                bookingService.update(id,conversionService.convert(booking, Booking.class)), BookingDto.class);

    }

    @Override
    public BookingDto getById(Long id) {
        Booking booking = bookingService.getById(id);
        return conversionService.convert(booking, BookingDto.class);
    }

    @Override
    public Long delete(Long id) {
        return  bookingService.delete(id);
    }

    @Override
    public List<BookingDto> getByShow(Long id) {
        return bookingService.getByShow(id).stream().
                map(m->conversionService.convert(m, BookingDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getByUser(Long id) {
         return bookingService.getByUser(id).stream().
                map(m->conversionService.convert(m, BookingDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getByUserAndShow(Long user, Long show) {
        return bookingService.getByUserAndShow(user,show).stream().
                map(m->conversionService.convert(m, BookingDto.class)).
                collect(Collectors.toList());
    }
}
