package com.petrunko.backup.todo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.petrunko.backup.todo.dao.UserRepository;
import com.petrunko.backup.todo.model.ToDo;
import com.petrunko.backup.todo.model.User;
import com.petrunko.backup.todo.service.util.ApiRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoService toDoService;

    @Value(value = "${todoItemServer.url}")
    private String url;

    @Autowired
    private ApiRequestService apiRequestService;

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveList(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> fetchAllFromServer() {
        String GET_USERS_URL = "/users";
        String usersJson = apiRequestService.sendGetRequest(url + GET_USERS_URL);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(usersJson, new TypeToken<List<User>>() {
        }.getType());
    }

    @Override
    public void saveUsersAndToDos(List<User> users) {
        for (User user : users) {
            List<ToDo> toDos = user.getTodos();
            toDoService.saveList(toDos);
        }
        saveList(users);
    }
}
