package com.example.cinema.service;

import com.example.cinema.api.SeatService;
import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Hall;
import com.example.cinema.model.Row;
import com.example.cinema.model.Seat;
import com.example.cinema.model.modeltypes.SeatType;
import com.example.cinema.repository.RowRepository;
import com.example.cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public void saveWithRow(Seat seat, Long rowNum,Long hall) {

        List<Row> rows = rowRepository.getByHall(hall);
      List<Row> row = rowRepository.findByNumber(Math.toIntExact(rowNum));
        row.retainAll(rows);
        seat.setRow(row.get(0));
        seatRepository.save(seat);
    }

    @Override
    public Long[] deleteMany(Long[] ids) {
        Seat seat = seatRepository.getById(ids[0]);
        Hall hall = seat.getRow().getHall();

        for (Long id: ids) {
            seatRepository.deleteById(id);
        }
        List<Row> rows = rowRepository.findEmptySize(0);
        if(rows.size() !=0) {
            for (int i = 0; i < rows.size(); i++) {
                rowRepository.deleteById(rows.get(i).getId());
            }
        }
        List<Row> allRows =  rowRepository.getByHall(hall.getId());

        Collections.sort(allRows, new Comparator<Row>(){

            public int compare(Row o1, Row o2)
            {
                return o1.getNumber().compareTo(o2.getNumber());
            }
        });

        for (int i = 0; i < allRows.size(); i++) {
            allRows.get(i).setNumber(i+1);
            rowRepository.save(allRows.get(i));
        }

        return ids;
    }

    @Override
    public Long[] setVIPMany(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Seat seat = seatRepository.getById(ids[i]);
            if(seat.getSeatType()==SeatType.VIP){
                seat.setSeatType(SeatType.DEFAULT);
            }else {
                seat.setSeatType(SeatType.VIP);
            }
            seatRepository.save(seat);
        }

        return ids;
    }

    @Override
    public List<Seat> getByTypeAndHall(SeatType type, Long hall) {
        List<Seat>seats = seatRepository.findBySeatType(type);
        List<Seat> filter = new ArrayList<>();
        for (Seat s : seats) {
            if(s.getRow().getHall().getId() == hall){
                filter.add(s);
            }
        }
         return seats;
    }

    private boolean checkExistRow(Row row) {
        if(row==null){
        }
         else if(!rowRepository.existsById(row.getId()))
            throw new EntityNotFoundException(Row.class, "id", row.getId().toString());
        return true;
    }
}
