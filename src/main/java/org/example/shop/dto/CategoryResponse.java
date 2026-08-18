package org.example.shop.dto;

public class CategoryResponse {
    private String name;
    private Long id;

    public CategoryResponse(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public CategoryResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
