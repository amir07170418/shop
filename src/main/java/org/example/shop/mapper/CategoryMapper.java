package org.example.shop.mapper;

import org.example.shop.dto.CategoryRequest;
import org.example.shop.dto.CategoryResponse;
import org.example.shop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
