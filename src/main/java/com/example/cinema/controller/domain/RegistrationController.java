package com.example.cinema.controller.domain;

import com.example.cinema.model.User;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "regist";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm")  User userForm, Model model) {

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passIsOK", false);
            return "regist";
        }
        if(!userService.checkEmail(userForm.getEmail())){
            model.addAttribute("mailIsOK", false);
            return "regist";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("userIsOK", false);
            return "regist";
        }
        return "redirect:/login";
    }
}