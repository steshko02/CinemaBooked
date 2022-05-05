package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.RowDto;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RowToDto implements Converter<Row, RowDto> {

    @Override
    public RowDto convert(Row source) {
        RowDto result = new RowDto();
        result.setId(source.getId());
        if(result.getHall()==null){
            result.setHall(null);
        }else{
         result.setHall(source.getHall().getId());
         }
        result.setNumber(source.getNumber());
        result.setSeats(source.getSeats().stream().map(Seat::getId).collect(Collectors.toSet()));

        return result;
    }
}
