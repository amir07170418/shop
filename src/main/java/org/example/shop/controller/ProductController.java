package org.example.shop.controller;

import org.example.shop.dto.ProductRequest;
import org.example.shop.dto.ProductResponse;
import org.example.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }
    @GetMapping()
    public Page<ProductResponse> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest){
        return productService.save(productRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return productService.update(id, productRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
