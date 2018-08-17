package com.petrunko.backup.todo.dao;

import com.petrunko.backup.todo.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
