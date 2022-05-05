package com.example.cinema.service;

import com.example.cinema.api.RowService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.*;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.RowRepository;
import com.example.cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RowServiceImpl implements RowService {

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private HallRepository hallRepository;


    @Override
    public Row getById(Long id) {
        return rowRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public Long delete(Long id) {
        rowRepository.deleteById(id);
        return id;
    }

    @Override
    public void save(Row convert) {
        convert.getSeats().stream().forEach(s->s.setRow(convert));
        rowRepository.save(convert);
    }

    @Override
    public List<Row> getByHall(Long id) {
        return rowRepository.getByHall(id);
    }

    @Override
    public Row update(Long id, Row row) {
        Row rowForEdit = rowRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistSeats(row.getSeats()) && checkExistHall(row.getHall())){
            rowForEdit.setNumber(row.getNumber());

            Set<Seat> seats = getExistSeats(row);

            Hall hall = row.getHall();

            rowForEdit.setHall(hall);
            rowForEdit.setSeats(seats);

            save(rowForEdit);
            return rowForEdit;
        }
        throw new EntityNotFoundException(Row.class, "id", id.toString());

    }

    private Set<Seat> getExistSeats(Row row) {
        return row.getSeats().stream().
                map(g->seatRepository.getById(g.getId())).collect(Collectors.toSet());

    }

    private boolean checkExistHall(Hall hall) {
           if(hall==null){

           } else if(!hallRepository.existsById(hall.getId()))
                throw new EntityNotFoundException(Hall.class, "id", hall.getId().toString());
        return true;
    }

    private boolean checkExistSeats(Set<Seat> seats) {
        for (Seat m: seats) {
            if(!seatRepository.existsById(m.getId()))
                throw new EntityNotFoundException(Seat.class, "id", m.getId().toString());
        }
        return true;

    }
}
