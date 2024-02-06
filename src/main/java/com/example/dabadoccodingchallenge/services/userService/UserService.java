
package com.example.dabadoccodingchallenge.services.userService;


import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;

import java.util.List;

public interface UserService {

    public List<User> getUsers();

    public void deleteUser(Long id) throws AppException;
    public User getUserByID(long id) throws AppException;

    public User updateUser(long id,User newUser) ;


    public User createUser(User catalogue);
}
