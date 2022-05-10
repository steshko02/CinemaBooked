package com.example.cinema.dto.service.interfaces;

import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.model.modeltypes.SeatType;

import java.util.List;

public interface SeatDtoService {

    void save(SeatDto seat);

    SeatDto getById(Long id);

    Long delete(Long id);

    SeatDto update(SeatDto seat, Long id);

    List<SeatDto> getByType(SeatType type);

    void saveWithRow(SeatDto seat, Long rowNum,Long hall);

    Long[] deleteMany(Long[] ids);

    Long[] setVIPMany(Long[] ids);

    List<SeatDto> getByTypeAndHall(SeatType type, Long hall);
}
