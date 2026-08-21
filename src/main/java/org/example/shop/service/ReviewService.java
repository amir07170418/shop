package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.ReviewRequest;
import org.example.shop.dto.ReviewResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.ReviewMapper;
import org.example.shop.model.Customer;
import org.example.shop.model.Product;
import org.example.shop.model.Review;
import org.example.shop.repository.CustomerRepository;
import org.example.shop.repository.ProductRepository;
import org.example.shop.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements ShopService<ReviewRequest, ReviewResponse> {
    private final  ReviewRepository  reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ReviewMapper reviewMapper;
    public ReviewService(ProductRepository productRepository, CustomerRepository customerRepository, ReviewMapper mapper, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.reviewMapper= mapper;
        this.reviewRepository = reviewRepository;
    }
    @Override
    @Transactional
    public ReviewResponse save(ReviewRequest reviewRequest) {
        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow
                (() -> new ShopException("product not found", HttpStatus.NOT_FOUND));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer=customerRepository.findByEmail(email).orElseThrow
                (() -> new ShopException("customer not found", HttpStatus.NOT_FOUND));
        if (reviewRepository.existsByProductIdAndCustomerId(product.getId(), customer.getId())) {
            throw new ShopException("you cant review more than one for this product", HttpStatus.BAD_REQUEST);
        }
        if (!reviewRepository.checkBuyProduct(product.getId(), customer.getId())) {
            throw new ShopException("you dont buying this product", HttpStatus.BAD_REQUEST);
        }
        Review review = reviewMapper.toReview(reviewRequest);
        review.setProduct(product);
        review.setCustomer(customer);
        reviewRepository.save(review);
        return reviewMapper.toReviewResponse(review);
    }
    @Override
    @Transactional
    public ReviewResponse update(Long id, ReviewRequest reviewRequest) {
       Review review = reviewRepository.findById(id).orElseThrow
                (() -> new ShopException("review not found", HttpStatus.NOT_FOUND));
        checkUser(review);
       reviewMapper.updateReview(reviewRequest, review);
       return reviewMapper.toReviewResponse(review);
    }

    @Override
    public ReviewResponse findById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow
                (() -> new ShopException("review not found", HttpStatus.NOT_FOUND));
        checkUser(review);
        return reviewMapper.toReviewResponse(review);
    }
    public ReviewResponse adminFindById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow
                (() -> new ShopException("review not found", HttpStatus.NOT_FOUND));
        return  reviewMapper.toReviewResponse(review);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow
                (() -> new ShopException("review not found", HttpStatus.NOT_FOUND));
        checkUser(review);
        reviewRepository.delete(review);
    }
    @Transactional
    public void adminReviewDelete(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow
                (() -> new ShopException("review not found", HttpStatus.NOT_FOUND));
        reviewRepository.delete(review);
    }

    private void checkUser(Review review){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer=customerRepository.findByEmail(email).orElseThrow
                (()-> new ShopException("customer not found", HttpStatus.BAD_REQUEST));
        if (!review.getCustomer().getId().equals(customer.getId())) {
            throw new ShopException("this review is not yours", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public Page<ReviewResponse> findAll(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviews.map(reviewMapper::toReviewResponse);
    }


}
