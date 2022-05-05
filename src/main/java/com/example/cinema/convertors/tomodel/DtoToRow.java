package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.HallService;
import com.example.cinema.api.SeatService;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoToRow implements Converter<RowDto, Row> {

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @Override
    public Row convert(RowDto source) {
        Row result = new Row();
        result.setId(source.getId());
        result.setNumber(source.getNumber());
        if(source.getHall()!=null)
           result.setHall(hallService.getById(source.getHall()));
        else result.setHall(null);
        result.setSeats(source.getSeats().stream().map(m->seatService.getById(m)).collect(Collectors.toSet()));
        return result;
    }
}
