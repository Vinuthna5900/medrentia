package com.example.server.dto;

import com.example.server.model.Role;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;

    // constructor
    public UserResponse(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
