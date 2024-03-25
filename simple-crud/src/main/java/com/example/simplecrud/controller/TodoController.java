package com.example.simplecrud.controller;

import com.example.simplecrud.controller.dto.TodoRequestDTO;
import com.example.simplecrud.controller.response.TodoResponse;
import com.example.simplecrud.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    private TodoService todoService;
    @PostMapping("/create/todo")
    public TodoResponse create(@RequestBody TodoRequestDTO requestDTO){
       return todoService.create(requestDTO);
    }
    @PutMapping("/todo/{todo-id}" )
    public TodoResponse update(@PathVariable ("todo-id") Long id,@RequestBody TodoRequestDTO requestDTO){
        return todoService.update(id,requestDTO);
    }
    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable ("id") Long id){
        todoService.delete(id);
    }
    @GetMapping("/todo")
    public List<TodoResponse> getAll(){
        return todoService.getAll();
    }
}
