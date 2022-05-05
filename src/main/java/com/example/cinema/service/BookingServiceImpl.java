package com.example.cinema.service;

import com.example.cinema.api.BookingService;
import com.example.cinema.dto.model.BookingDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Booking;
import com.example.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeatRepository seatRepository;


    @Override
    public void save(Booking convert) {
        bookingRepository.save(convert);
    }

    @Override
    public Booking update(Long id, Booking convert) {
        return null;
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Long delete(Long id) {
        bookingRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Booking> getByShow(Long id) {
        return bookingRepository.findByShow(id);
    }

    @Override
    public List<Booking> getByUser(Long id) {
        return bookingRepository.getByUser(id);
    }

    @Override
    public List<Booking> getByUserAndShow(Long user, Long show) {
        List<Booking> bookingList =  getByUser(user);
        bookingList.retainAll(getByShow(show));
        return bookingList;
    }
}
