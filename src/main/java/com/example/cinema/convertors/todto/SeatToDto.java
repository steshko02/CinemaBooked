package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class SeatToDto implements Converter<Seat, SeatDto> {
    @Override
    public SeatDto convert(Seat source) {
        SeatDto result = new SeatDto();
        result.setId(source.getId());
        result.setNumber(source.getNumber());
        result.setSeatType(source.getSeatType());
        result.setSeatStatus(source.getSeatStatus());
        result.setPrice(source.getPrice());
        if(source.getRow()!= null)
           result.setRow(source.getRow().getId());
        else  result.setRow(null);
        return result;
    }
}
