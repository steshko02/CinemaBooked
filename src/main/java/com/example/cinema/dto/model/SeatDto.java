package com.example.cinema.dto.model;

import com.example.cinema.model.modeltypes.SeatStatus;
import com.example.cinema.model.modeltypes.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private Long id;

    private Long number;

    private SeatType seatType;

    private SeatStatus seatStatus;

    private Long row;

    private Float price;
}
