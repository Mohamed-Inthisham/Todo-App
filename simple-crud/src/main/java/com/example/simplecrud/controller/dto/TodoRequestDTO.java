package com.example.simplecrud.controller.dto;

import lombok.Data;

@Data
public class TodoRequestDTO {
    private Long id;
    private String title;
    private String description;
}
