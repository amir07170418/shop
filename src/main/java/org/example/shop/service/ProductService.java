package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.ProductRequest;
import org.example.shop.dto.ProductResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.ProductMapper;
import org.example.shop.model.Category;
import org.example.shop.model.Product;
import org.example.shop.repository.CategoryRepository;
import org.example.shop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ShopService<ProductRequest, ProductResponse> {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    @Override
    public ProductResponse save(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new ShopException("product already exist", HttpStatus.BAD_REQUEST);
        }
        Product product=productMapper.toProduct(productRequest);
        Category category=categoryRepository.findById(productRequest.getCategoryId()).orElseThrow
                (() -> new ShopException("category not found", HttpStatus.BAD_REQUEST));
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }
    @Transactional
    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product product=productRepository.findById(id).orElseThrow
                (() -> new ShopException("product not found", HttpStatus.BAD_REQUEST));
        if (!product.getName().equals(productRequest.getName()) && productRepository.existsByName(productRequest.getName())) {
            throw new ShopException("product already exist", HttpStatus.BAD_REQUEST);
        }
        Category category=categoryRepository.findById(productRequest.getCategoryId()).orElseThrow
                (() -> new ShopException("category not found", HttpStatus.BAD_REQUEST));
        productMapper.updateProduct(productRequest, product);
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product=productRepository.findById(id).orElseThrow
                (() -> new ShopException("product not found", HttpStatus.BAD_REQUEST));
        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteById(Long id) {
        Product product=productRepository.findById(id).orElseThrow
                (() -> new ShopException("product not found", HttpStatus.BAD_REQUEST));
        if (productRepository.existsInOrderItemsById(product.getId())) {
            throw new ShopException("product is in order Items", HttpStatus.BAD_REQUEST);
        }
        productRepository.delete(product);
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        Page<Product> products=productRepository.findAll(pageable);
        return products.map(productMapper::toProductResponse);
    }
}
