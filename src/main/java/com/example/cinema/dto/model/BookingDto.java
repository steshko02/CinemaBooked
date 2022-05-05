package com.example.cinema.dto.model;

import com.example.cinema.model.Hall;
import com.example.cinema.model.MovieShow;
import com.example.cinema.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto implements Serializable {

    private Long id;

    private Long hall;

    private Set<Long> seats = new HashSet<>();

    private Float price;

    private Long movieShow;

    private Long user;
}
