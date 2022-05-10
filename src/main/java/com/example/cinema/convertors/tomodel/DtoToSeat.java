package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.RowService;
import com.example.cinema.api.SeatService;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DtoToSeat implements Converter<SeatDto, Seat> {

    @Autowired
    private RowService rowService;

    @Override
    public Seat convert(SeatDto source) {
        Seat result = new Seat();

        result.setId(source.getId());
        result.setPrice(source.getPrice());
        result.setNumber(source.getNumber());
        result.setSeatStatus(source.getSeatStatus());
        result.setSeatType(source.getSeatType());
        if(source.getRow()!=null)
          result.setRow(rowService.getById(source.getRow()));
        else   result.setRow(null);
//            throw new EntityNotFoundException(Row.class, "id", source.getRow().toString());
        return result;
    }
}
