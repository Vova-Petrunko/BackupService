package com.petrunko.backup.todo.service;

import com.petrunko.backup.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(String id);

    List<User> findAll();

    void save(User user);

    void saveList(List<User> users);

    List<User> fetchAllFromServer();

    void saveUsersAndToDos(List<User> users);
}
