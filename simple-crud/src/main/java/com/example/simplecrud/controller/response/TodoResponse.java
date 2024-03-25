package com.example.simplecrud.controller.response;

import lombok.Data;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private String description;
}
