package net.javaguides.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert TodoDto into Todo JPA Entity;

        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());

        Todo savedTodo = todoRepository.save(todo);

        //convert SavedTodo to TodoDTO Entity;
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setCompleted(savedTodo.isCompleted());
        savedTodoDto.setDescription(savedTodo.getDescription());

        return savedTodoDto;
    }
}
