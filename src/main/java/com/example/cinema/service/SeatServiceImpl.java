package com.example.cinema.service;

import com.example.cinema.api.SeatService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;
import com.example.cinema.repository.RowRepository;
import com.example.cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat getById(Long id) {
        return seatRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(Seat convert) {
        seatRepository.save(convert);
    }

    @Override
    public Long delete(Long id) {
        seatRepository.deleteById(id);
        return id;    }

    @Override
    public Seat update(Long id, Seat seat) {
        Seat seatForEdit = seatRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistRow(seat.getRow())){
            seatForEdit.setNumber(seat.getNumber());
            seatForEdit.setSeatType(seat.getSeatType());
            seatForEdit.setSeatStatus(seat.getSeatStatus());

          //  Row row = rowRepository.findById(seat.getRow().getId()).orElseThrow(EntityNotFoundException::new);
            seatForEdit.setRow(seat.getRow());
            seatRepository.save(seatForEdit);
            return seatForEdit;
        }
        throw new EntityNotFoundException(Row.class, "id", seat.getRow().getId().toString());

    }

    @Override
    public List<Seat> getByType(SeatType type) {
        return seatRepository.findBySeatType(type);
    }

    private boolean checkExistRow(Row row) {
        if(row==null){
        }
         else if(!rowRepository.existsById(row.getId()))
            throw new EntityNotFoundException(Row.class, "id", row.getId().toString());
        return true;
    }
}
