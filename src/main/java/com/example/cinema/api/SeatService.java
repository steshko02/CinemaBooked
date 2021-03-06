package com.example.cinema.api;

import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;

import java.util.Collection;
import java.util.List;

public interface SeatService {
    Seat getById(Long id);

    void save(Seat convert);

    Long delete(Long id);

    Seat update(Long id, Seat seat);

    List<Seat> getByType(SeatType type);

    void saveWithRow(Seat seat, Long rowNum, Long hall);

    Long[] deleteMany(Long[] ids);

    Long[] setVIPMany(Long[] ids);

    List<Seat> getByTypeAndHall(SeatType type,Long hall);
}
