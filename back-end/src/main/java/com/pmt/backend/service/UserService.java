package com.pmt.backend.service;

import com.pmt.backend.exceptions.UserException;
import com.pmt.backend.model.User;

import java.util.List;


public interface UserService {
    public User registerUser(User user);

    public User findUserById(Integer id) throws UserException;

    public User findUserByEmail(String email);

    public User followUser (Integer userId1, Integer userId2) throws UserException;

    public User updateUser(User user, Integer id) throws UserException;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);

}
