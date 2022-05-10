package com.example.cinema.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter

public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Hall hall;

    @ManyToMany( cascade=CascadeType.ALL)
    @JoinTable(name="booking_seats", joinColumns=@JoinColumn(name="booking_id"),
            inverseJoinColumns=@JoinColumn(name="seat_id"))
    private Set<Seat> seats = new HashSet<>();

    private Float price;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="show_id")
    private MovieShow movieShow;

    @ManyToOne
    private User user;

    public Booking() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(hall, booking.hall) && Objects.equals(seats, booking.seats) && Objects.equals(price, booking.price) && Objects.equals(movieShow, booking.movieShow) && Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hall, seats, price, movieShow, user);
    }
}
