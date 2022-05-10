package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.RowService;
import com.example.cinema.api.SeatService;
import com.example.cinema.dto.model.MovieShowDto;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.dto.service.interfaces.SeatDtoService;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatDtoServiceImpl implements SeatDtoService {

    @Autowired
    private SeatService seatService;

    @Autowired
    private ConversionService conversionService;


    @Override
    public void save(SeatDto seat) {
        seatService.save(conversionService.convert(seat, Seat.class));
    }

    @Override
    public SeatDto getById(Long id) {
        Seat seat = seatService.getById(id);
        return conversionService.convert(seat, SeatDto.class);
    }

    @Override
    public Long delete(Long id) {
        return  seatService.delete(id);
    }

    @Override
    public SeatDto update(SeatDto seat, Long id) {
        return conversionService.convert(
                seatService.update(id,conversionService.convert(seat, Seat.class)), SeatDto.class);

    }

    @Override
    public List<SeatDto> getByType(SeatType type) {
        return seatService.getByType(type).stream().
                map(m->conversionService.convert(m, SeatDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public void saveWithRow(SeatDto seat, Long rowNum,Long hall) {
        seatService.saveWithRow(conversionService.convert(seat, Seat.class),rowNum, hall);
    }

    @Override
    public Long[] deleteMany(Long[] ids) {
        return seatService.deleteMany(ids);
    }

    @Override
    public Long[] setVIPMany(Long[] ids) {
        return seatService.setVIPMany(ids);
    }

    @Override
    public List<SeatDto> getByTypeAndHall(SeatType type, Long hall) {
        return seatService.getByTypeAndHall(type,hall).stream().
                map(m->conversionService.convert(m, SeatDto.class)).
                collect(Collectors.toList());
    }
}
