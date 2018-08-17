package com.petrunko.backup.todo.service;

import com.petrunko.backup.todo.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoService {
    Optional<ToDo> findById(String id);

    List<ToDo> findAll();

    void save(ToDo toDo);

    void saveList(List<ToDo> toDos);
}
