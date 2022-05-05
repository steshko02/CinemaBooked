package com.example.cinema.controller.domain;

import com.example.cinema.dto.model.GenreDto;
import com.example.cinema.dto.model.UserDto;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;


    @GetMapping("/get")
    public UserDto get(@RequestParam("id") Long id){
        return  conversionService.convert(userService.findUserById(id), UserDto.class);
    }

    @GetMapping("/getCurUser")
    public UserDto get(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return conversionService.convert(userService.findUserByName(auth.getName()),UserDto.class);
    }

}
