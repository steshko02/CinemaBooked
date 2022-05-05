package com.example.cinema.convertors.tomodel;

import com.example.cinema.api.BookingService;
import com.example.cinema.dto.model.UserDto;
import com.example.cinema.model.User;
import com.example.cinema.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class DtoToUser implements Converter< UserDto,User> {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User convert(UserDto source) {

        User result = new User();
        result.setId(source.getId());
        result.setEmail(source.getEmail());
        result.setUsername(source.getUsername());
        result.setBookings(source.getBookings().stream().map(g->bookingService.getById(g)).collect(Collectors.toSet()));
        result.setRoles(source.getRoles().stream().map(g->roleRepository.findByName(g)).collect(Collectors.toSet()));

        return result;
    }
}
