package com.example.elonuz.dto;

import com.example.elonuz.enums.Role;

public record UserCreateDto (String username, String password, String email, String role){
}
