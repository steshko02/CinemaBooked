package com.example.cinema.repository;

import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findBySeatType(SeatType type);

//    @Query("select distinct m from Seat m inner join m.row h where h.id=:id")
//    List<Row> getByHall(@Param("id")  Long id);
}
