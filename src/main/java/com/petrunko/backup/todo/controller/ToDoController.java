package com.petrunko.backup.todo.controller;

import com.petrunko.backup.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping(value = "todo")
    public @ResponseBody
    ResponseEntity getToDos() {
        return ResponseEntity.ok(toDoService.findAll());
    }

    @GetMapping(value = "todo/{toDoId}")
    public @ResponseBody
    ResponseEntity getToDo(@PathVariable("toDoId") String toDoId) {
        return ResponseEntity.ok(toDoService.findById(toDoId));
    }
}
