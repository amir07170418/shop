package org.example.shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
