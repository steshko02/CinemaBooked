package com.example.cinema.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowDto implements Serializable {

    private Long id;

    private Date date;
    
    private Long movie;

    private Long hall;

}
