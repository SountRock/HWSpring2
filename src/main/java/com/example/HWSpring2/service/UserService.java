package com.example.HWSpring2.service;

import com.example.HWSpring2.model.User;
import com.example.HWSpring2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        userRepository.updateById(user);
    }

    public User getOne(int id){
        return userRepository.getOne(id);
    }
}
