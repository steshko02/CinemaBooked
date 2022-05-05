package com.example.cinema.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HallDto {
    private Long id;

    private String placeName;

    private Long seatsCount;

    private Set<Long> rows = new HashSet<>();

    private Set<Long> shows = new HashSet<>();
}
