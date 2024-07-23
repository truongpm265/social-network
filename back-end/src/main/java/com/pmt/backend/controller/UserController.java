package com.pmt.backend.controller;

import com.pmt.backend.exceptions.UserException;
import com.pmt.backend.model.User;
import com.pmt.backend.repository.UserRepository;
import com.pmt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable("id") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        User savedUser = userService.registerUser(user);
        return savedUser;
    }


    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);

        User updateUser = userService.updateUser(user,reqUser.getId());
        return updateUser;
    }

    @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);

        User user = userService.followUser(reqUser.getId(),userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt ){
        User user = userService.findUserByJwt(jwt);
        System.out.println("jwt----" + jwt);
        user.setPassword(null);
        return  user;
    }
}
