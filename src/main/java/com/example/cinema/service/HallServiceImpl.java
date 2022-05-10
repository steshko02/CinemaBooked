package com.example.cinema.service;

import com.example.cinema.api.HallService;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.*;
import com.example.cinema.model.modeltypes.SeatStatus;
import com.example.cinema.model.modeltypes.SeatType;
import com.example.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private RowRepository rowRepository;

    @Override
    public void save(Hall hall) {
        Seat seat = new Seat(1L,  SeatType.DEFAULT,SeatStatus.BOOKED, 100f);
        Row row = new Row();
        row.setNumber(1);
        seat.setRow(row);
        row.getSeats().add(seat);
        hall.getRows().add(row);
        hall.getRows().stream().forEach(r->r.setHall(hall));
        hallRepository.save(hall);
    }

    @Override
    public Hall getById(Long id) {
        return hallRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public Long delete(Long id) {
        hallRepository.deleteById(id);
        return id;
    }


    @Override
    public Hall update(Long id, Hall hall) {
        Hall hallForEdit = hallRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (checkExistShow(hall.getShows()) && checkExistRows(hall.getRows())) {

            hallForEdit.setPlaceName(hall.getPlaceName());
            hallForEdit.setSeatsCount(hall.getSeatsCount());

            Set<MovieShow> shows = getExistShows(hall);
            Set<Row> rows = getExistRows(hall);

            hallForEdit.setShows(shows);
            hallForEdit.setRows(rows);
            save(hallForEdit);
            return hallForEdit;
        }
        throw new EntityNotFoundException(Genre.class, "id", id.toString());
    }

    @Override
    public Hall getByShowId(Long id) {
        return null;
    }

    @Override
    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    private Set<Row> getExistRows(Hall hall) {
        return hall.getRows().stream().
                map(g->rowRepository.getById(g.getId())).collect(Collectors.toSet());

    }

    private Set<MovieShow> getExistShows(Hall hall) {
        return hall.getShows().stream().
                map(g->movieShowRepository.getById(g.getId())).collect(Collectors.toSet());
    }

    private boolean checkExistShow(Set<MovieShow> shows) {
        for (MovieShow sh: shows) {
            if (!movieShowRepository.existsById(sh.getId()))
                throw new EntityNotFoundException(MovieShow.class, "id", sh.getId().toString());
        }
        return true;
    }
    private boolean checkExistRows(Set<Row> rows){
        for (Row sh: rows) {
            if (!rowRepository.existsById(sh.getId()))
                throw new EntityNotFoundException(Row.class, "id", sh.getId().toString());
        }
        return true;
    }
}