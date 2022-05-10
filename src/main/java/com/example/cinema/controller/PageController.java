package com.example.cinema.controller;

import com.example.cinema.dto.model.UserDto;
import com.example.cinema.model.User;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/moviePage")
    public String moviePage(Model model){
        model.addAttribute("user", getCurrentUser());
        return "movie";
    }

    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("user", getCurrentUser());
        return "main";
    }

    private UserDto getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = conversionService.convert(userService.findUserByName(auth.getName()),UserDto.class);
        return user;
    }

    @GetMapping("/home")
    public String homePage(){
        return "login";
    }

    @GetMapping("/hall")
    public String hallPage(){
        return "hall";
    }

    @GetMapping("/genres")
    public String genresPage(Model model){
        model.addAttribute("user", getCurrentUser());
        return "AddGenre";
    }

    @GetMapping("/add-movie")
    public String movies(Model model){
        model.addAttribute("user", getCurrentUser());
        return "AddMovie";
    }

    @GetMapping("/edit-hall")
    public String editHall(){
        return "EditHall";
    }

    @GetMapping("/edit-movie")
    public String editMovie(Model model){
        model.addAttribute("user", getCurrentUser());
        return "EditMovie";
    }

    @GetMapping("/shows")
    public String shows(Model model){
        model.addAttribute("user", getCurrentUser());
        return "movieShow";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("user", getCurrentUser());
        return "admin";
    }

    @GetMapping("/halls")
    public String halls(Model model){
        model.addAttribute("user", getCurrentUser());
        return "HallLlist";
    }
    @GetMapping("/account")
    public String registration(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());
        model.addAttribute("user", user);
        return "UserPage";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user")  User user, Model model) {

        userService.update(user);

        return "UserPage";
    }

    @GetMapping("/editUsernameEmail")
    public String editUsernameEmail(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());
        model.addAttribute("user", user);
        return "editUsernameEmail";
    }

    @PostMapping("/editUsernameEmail")
    public String updateUsernameEmail(@ModelAttribute("user")  User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User realUser = userService.findUserByName(auth.getName());
        if (!userService.checkPasswordByEqual(user.getPassword(),realUser.getPassword())){
            model.addAttribute("passIsOK", false);
            return "editUsernameEmail";
        }
        if(realUser.getEmail().equals(user.getEmail())){

        }else if(!userService.checkEmail(user.getEmail())){
            model.addAttribute("mailIsOK", false);
            return "editUsernameEmail";
        }
        if(realUser.getUsername().equals(user.getUsername())) {

        }else if (!userService.checkName(user.getUsername())){
            model.addAttribute("userIsOK", false);
            return "editUsernameEmail";
        }
        userService.editUsernameEmail(user);
        return "redirect:/logout";
    }

    @GetMapping("/editPassword")
    public String editPassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());
        model.addAttribute("user", user);
        return "editPasswordPage";
    }

    @PostMapping("/editPassword")
    public String editPassword(@ModelAttribute("user")  User user, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User realUser = userService.findUserByName(auth.getName());
        if (!userService.checkPasswordByEqual(user.getEmail(),realUser.getPassword())){
            model.addAttribute("passIsOK", false);
            return "editPasswordPage";
        }
        userService.editPassword(user);
        return "redirect:/logout";
    }

}
