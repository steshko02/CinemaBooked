package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.BookingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingDtoService {
    void save(BookingDto booking);

    BookingDto update(BookingDto booking, Long id);

    BookingDto getById(Long id);

    Long delete(Long id);

    List<BookingDto> getByShow(Long id);

    List<BookingDto> getByUser(Long id);

    List<BookingDto> getByUserAndShow(Long user, Long show);
}
