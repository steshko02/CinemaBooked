package com.example.cinema.dto.model;

import com.example.cinema.model.Booking;
import com.example.cinema.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto  implements Serializable {

    private Long id;

    private String username;

    private String email;

    private Set<String> roles= new HashSet<>();

    private Set<Long> bookings = new HashSet<>();

}
