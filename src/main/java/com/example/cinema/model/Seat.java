package com.example.cinema.model;

import com.example.cinema.model.modeltypes.SeatStatus;
import com.example.cinema.model.modeltypes.SeatType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long number;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "row_id",nullable = true)
    private Row row;

    @ManyToMany(mappedBy="seats", cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Booking> bookings = new HashSet<>();

    @PrePersist
    public void addPositions() {
        bookings.forEach(booking -> booking.getSeats().add(this));
    }

    @PreRemove
    public void removePositions() {
        bookings.forEach(booking -> booking.getSeats().remove(this));
    }

    public Seat(Long number, SeatType seatType, SeatStatus seatStatus, Float price) {
        this.number = number;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.price = price;
    }

    private Float price;

    public Seat() {
    }
}
