package com.example.cinema.repository;

import com.example.cinema.model.Booking;
import com.example.cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select distinct m from Booking m inner join m.movieShow mov where mov.id=:id")
    List<Booking> findByShow(@Param("id")  Long id);

    @Query("select distinct m from Booking m inner join m.user mov where mov.id=:id")
    List<Booking> getByUser(@Param("id")  Long id);


//    @Query("select distinct b from Booking b inner join b.user us on us.id=:id inner join ")
//    List<Booking> getByUserAndShow(@Param("user") Long user, @Param("show") Long show);
}
