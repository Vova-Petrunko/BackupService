package com.petrunko.backup.todo.service;

import com.petrunko.backup.todo.dao.ToDoRepository;
import com.petrunko.backup.todo.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public Optional<ToDo> findById(String id) {
        return toDoRepository.findById(id);
    }

    @Override
    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    @Override
    public void save(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    @Override
    public void saveList(List<ToDo> toDos) {
        toDoRepository.saveAll(toDos);
    }
}
