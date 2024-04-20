package com.example.HWSpring2.controller;

import com.example.HWSpring2.model.User;
import com.example.HWSpring2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting() {
        return "index";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        if(users.isEmpty()){
            model.addAttribute("users", null);
        } else {
            model.addAttribute("users", users);
        }

        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);

        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        if(user == null) {
            return "redirect:/user-create";
        }
        model.addAttribute("user", user);

        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}

