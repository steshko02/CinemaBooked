package com.example.cinema.repository;

import com.example.cinema.dto.model.RowDto;
import com.example.cinema.model.MovieShow;
import com.example.cinema.model.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Long> {

    @Query("select distinct m from Row m inner join m.hall h where h.id=:id")
    List<Row> getByHall(@Param("id")  Long id);

    @Query("select distinct m from Row m where m.seats.size=:size")
    List<Row> findEmptySize(@Param("size")  Integer size);


    List<Row> findByNumber(Integer number);

}
