package org.example.shop.mapper;

import org.example.shop.dto.ReviewRequest;
import org.example.shop.dto.ReviewResponse;
import org.example.shop.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "product.id",target = "productId")
    @Mapping(source = "customer.id",target = "customerId")
    ReviewResponse toReviewResponse(Review review);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Review toReview(ReviewRequest reviewRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateReview(ReviewRequest reviewRequest, @MappingTarget Review review);
}
