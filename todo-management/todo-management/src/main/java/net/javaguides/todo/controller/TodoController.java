package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")


@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add Todo REST API

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto)
    {
       TodoDto savedTodo= todoService.addTodo(todoDto);
       return  new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build GET TODO API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") long todoId)
    {
        TodoDto todoDto=   todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //Build GET ALL TODOS REST API
    @GetMapping
    public  ResponseEntity<List<TodoDto>> getAllTodos()
    {
        List<TodoDto> todos = todoService.getAllTodos();
        return  ResponseEntity.ok(todos);
    }


    //Build Update Todo REST API
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id)
    {
        TodoDto UpdatedTodoDto = todoService.updateTodo(todoDto, id);
        return  ResponseEntity.ok(UpdatedTodoDto);
    }
}
