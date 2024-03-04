package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.service.TodoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todos")


@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add todo
}
