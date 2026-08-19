package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.CategoryRequest;
import org.example.shop.dto.CategoryResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.CategoryMapper;
import org.example.shop.model.Category;
import org.example.shop.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements  ShopService<CategoryRequest, CategoryResponse> {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new ShopException("Category With this Name Already Exist", HttpStatus.BAD_REQUEST);
        }
        Category  category = categoryMapper.toCategory(categoryRequest);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Transactional
    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ShopException("Category Not Found"
                , HttpStatus.NOT_FOUND));
        if (!category.getName().equals(categoryRequest.getName()) &&
                categoryRepository.existsByName(categoryRequest.getName())) {
            throw new ShopException("Category With this Name Already Exist", HttpStatus.BAD_REQUEST);
        }
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ShopException("Category Not Found",HttpStatus.NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ShopException("Category Not Found",HttpStatus.NOT_FOUND));
        if (categoryRepository.existsProductByCategoryId(category.getId())) {
            throw new ShopException("Product with this category Exist", HttpStatus.BAD_REQUEST);
        }
        categoryRepository.delete(category);
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper::toCategoryResponse);
    }
}
