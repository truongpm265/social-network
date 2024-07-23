package com.pmt.backend.service;

import com.pmt.backend.config.JwtProvider;
import com.pmt.backend.exceptions.UserException;
import com.pmt.backend.model.User;
import com.pmt.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());


        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not exist with userId + " + id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);

        User user2 = findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer id) throws UserException {
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isEmpty()){
            throw new UserException("User not exist with id + " + id);
        }
        User oldUser = user1.get();

        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

}
