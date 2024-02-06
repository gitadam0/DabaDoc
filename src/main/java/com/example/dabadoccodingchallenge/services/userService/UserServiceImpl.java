package com.example.dabadoccodingchallenge.services.userService;



import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws AppException {
        userRepository.findById(id).orElseThrow(()->new AppException("user to delete not found for "+id, HttpStatus.NOT_FOUND));

        userRepository.deleteById(id);
    }

    @Override
    public User getUserByID(long id) throws AppException {
        return userRepository.findById(id).orElseThrow(()->new AppException("user not found for "+id, HttpStatus.NOT_FOUND));
    }

    @Override
    public User updateUser(long id, User newUser) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
