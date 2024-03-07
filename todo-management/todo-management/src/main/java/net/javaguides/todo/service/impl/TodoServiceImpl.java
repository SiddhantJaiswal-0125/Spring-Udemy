package net.javaguides.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert TodoDto into Todo JPA Entity;
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);

        //convert SavedTodo to TodoDTO Entity;
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo Not found by id "+id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos =  todoRepository.findAll();
        return  todos.stream().map( (todo)->modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto newTodo, Long id) {
        Todo currentTodo = todoRepository.findById(id).orElseThrow(()
                ->new ResourceNotFoundException("Todo Not found with ID : "+id));



        currentTodo.setTitle(newTodo.getTitle());
        currentTodo.setDescription(newTodo.getDescription());
        currentTodo.setCompleted(newTodo.getCompleted());

        Todo updatedTodo = todoRepository.save(currentTodo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
