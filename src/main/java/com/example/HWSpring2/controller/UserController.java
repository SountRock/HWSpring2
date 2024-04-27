package com.example.HWSpring2.controller;

import com.example.HWSpring2.model.User;
import com.example.HWSpring2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting() {
        return "index";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        log.info("Start of the operation findAll(/users):");
        List<User> users = userService.findAll();
        log.info("  Get users: " + users);

        if(users.isEmpty()){
            model.addAttribute("users", null);
            log.info("  Users not exist!");
        } else {
            model.addAttribute("users", users);
            log.info("  Operation successfully!");
        }

        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("Request to User Create Form");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        log.info("Start of the operation createUser(/user-create):");
        userService.saveUser(user);
        log.info("  Operation successfully");

        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        log.info("Start of the operation deleteUser(user-delete/{id}):");
        userService.deleteById(id);
        log.info("  Operation successfully!");
        log.info("  Delete User with ID=" + id);

        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        log.info("Start of the operation updateUser(/user-update/{id}):");
        User user = userService.getOne(id);
        if(user == null) {
            log.info("  User is null");
            return "redirect:/user-create";
        }
        log.info("  receiving User successfully");
        model.addAttribute("user", user);
        log.info("  Operation successfully!");

        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        log.info("Start of the operation updateUser(/user-update/{id}):");
        userService.updateUser(user);
        log.info("  Operation successfully!");

        return "redirect:/users";
    }
}

