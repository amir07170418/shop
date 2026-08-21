package org.example.shop.controller;

import org.example.shop.dto.ReviewRequest;
import org.example.shop.dto.ReviewResponse;
import org.example.shop.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ReviewResponse addReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.save(reviewRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<ReviewResponse> getReviews(Pageable pageable) {
        return reviewService.findAll(pageable);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ReviewResponse getReview(@PathVariable Long id) {
        return reviewService.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ReviewResponse getReviewAdmin(@PathVariable Long id) {
        return reviewService.adminFindById(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adminDelete/{id}")
    public void deleteReviewAdmin(@PathVariable Long id) {
        reviewService.adminReviewDelete(id);
    }

}
