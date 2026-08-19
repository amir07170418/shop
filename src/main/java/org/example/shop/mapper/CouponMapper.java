package org.example.shop.mapper;

import org.example.shop.dto.CouponRequest;
import org.example.shop.dto.CouponResponse;
import org.example.shop.model.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponResponse toCouponResponse(Coupon coupon);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "usageCount", ignore = true)
    @Mapping(target = "active", ignore = true)
    Coupon toCoupon(CouponRequest couponRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "usageCount", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateCoupon(CouponRequest couponRequest, @MappingTarget Coupon coupon);
}
