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
public class RowDto {
    private Long id;

    private Integer number;

    private Long hall;

    private Set<Long> seats = new HashSet<>();

}
