package com.example.cinema.convertors.todto;

import com.example.cinema.dto.model.SeatDto;
import com.example.cinema.dto.model.UserDto;
import com.example.cinema.model.Booking;
import com.example.cinema.model.Role;
import com.example.cinema.model.Seat;
import com.example.cinema.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserToDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {

        UserDto result = new UserDto();
        result.setId(source.getId());
        result.setEmail(source.getEmail());
        result.setUsername(source.getUsername());
        result.setBookings(source.getBookings().stream().map(Booking::getId).collect(Collectors.toSet()));
        result.setRoles(source.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));

        return result;
    }
}
