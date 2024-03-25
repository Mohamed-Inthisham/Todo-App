package com.example.simplecrud.service.impl;

import com.example.simplecrud.controller.dto.TodoRequestDTO;
import com.example.simplecrud.controller.response.TodoResponse;
import com.example.simplecrud.model.Todo;
import com.example.simplecrud.repository.TodoRepository;
import com.example.simplecrud.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    @Override
    public List<TodoResponse> getAll() {
       List<Todo> todoList= todoRepository.findAll();
       List<TodoResponse> todoResponseList=new ArrayList<>();
       for (Todo todo: todoList){
        TodoResponse todoResponse=new TodoResponse();
        todoResponse.setId(todo.getId());
        todoResponse.setTitle(todo.getTitle());
        todoResponse.setDescription(todo.getDescription());
        todoResponseList.add(todoResponse);
       }
        return todoResponseList;
    }

    @Override
    public void delete(Long id) {
      Todo todo=todoRepository.findById(id).orElseThrow(()-> new RuntimeException("todo not found"));
      todoRepository.delete(todo);
    }

    @Override
    public TodoResponse update(Long id, TodoRequestDTO requestDTO) {
       Optional<Todo> optionalTodo=todoRepository.findById(id);
       if (optionalTodo.isPresent()){
           Todo todo=optionalTodo.get();
           todo.setTitle(requestDTO.getTitle());
           todo.setDescription(requestDTO.getDescription());
           Todo updateTodo=todoRepository.save(todo);

           TodoResponse todoResponse=new TodoResponse();
           todoResponse.setTitle(updateTodo.getTitle());
           todoResponse.setDescription(updateTodo.getDescription());
           return  todoResponse;
       }
        return null;
    }

    @Override
    public TodoResponse create(TodoRequestDTO requestDTO) {
        Todo todo = new Todo();
        todo.setTitle(requestDTO.getTitle());
        todo.setDescription(requestDTO.getDescription());
        todoRepository.save(todo);

        TodoResponse todoResponse=new TodoResponse();
        todoResponse.setTitle(todo.getTitle());
        todoResponse.setDescription(todo.getDescription());
        return todoResponse;
    }
}
