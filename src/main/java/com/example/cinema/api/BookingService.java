package com.example.cinema.api;

import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.model.Booking;

import java.util.Collection;
import java.util.List;

public interface BookingService {
    void save(Booking convert);


    Booking update(Long id, Booking convert);

    Booking getById(Long id);

    Long delete(Long id);

    List<Booking> getByShow(Long id);

    List<Booking> getByUser(Long id);

    List<Booking> getByUserAndShow(Long user, Long show);
}
