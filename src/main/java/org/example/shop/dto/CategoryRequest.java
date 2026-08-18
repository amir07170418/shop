package org.example.shop.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }
    public CategoryRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
