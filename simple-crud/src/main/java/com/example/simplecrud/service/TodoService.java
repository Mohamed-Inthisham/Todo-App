package com.example.simplecrud.service;

import com.example.simplecrud.controller.dto.TodoRequestDTO;
import com.example.simplecrud.controller.response.TodoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    List<TodoResponse> getAll();

    void delete(Long id);

    TodoResponse update(Long id, TodoRequestDTO requestDTO);

    TodoResponse create(TodoRequestDTO requestDTO);
}
