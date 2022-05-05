package com.example.cinema.repository;

import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findBySeatType(SeatType type);
}
