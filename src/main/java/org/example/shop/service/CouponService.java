package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.CouponRequest;
import org.example.shop.dto.CouponResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.CouponMapper;
import org.example.shop.model.Coupon;
import org.example.shop.repository.CouponRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }
    @Transactional
    public CouponResponse save(CouponRequest couponRequest) {
        if (couponRepository.existsByCode(couponRequest.getCode())) {
            throw new ShopException("coupon with this code already exist", HttpStatus.BAD_REQUEST);
        }
        Coupon coupon = couponMapper.toCoupon(couponRequest);
        coupon.setActive(true);
        coupon.setStartDate(LocalDateTime.now());
        coupon.setEndDate(LocalDateTime.now().plusHours(couponRequest.getExpiredHours()));
        coupon.setUsageCount(0);
        couponRepository.save(coupon);
        return couponMapper.toCouponResponse(coupon);
    }
    @Transactional
    public CouponResponse addHoursToCoupon(Integer hours,Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(
                () -> new ShopException("coupon not found", HttpStatus.NOT_FOUND));
        coupon.setEndDate(coupon.getEndDate().plusHours(hours));
        if (coupon.getEndDate().isAfter(LocalDateTime.now())) {
            coupon.setActive(true);
        }
        couponRepository.save(coupon);
        return couponMapper.toCouponResponse(coupon);
    }
    @Transactional
    public CouponResponse deActivateCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(
                ()-> new ShopException("coupon not found", HttpStatus.NOT_FOUND));
        coupon.setActive(false);
        couponRepository.save(coupon);
        return couponMapper.toCouponResponse(coupon);
    }
    public void addUsage(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(()->
                new ShopException("coupon not found", HttpStatus.NOT_FOUND));
        coupon.setUsageCount(coupon.getUsageCount() + 1);
        couponRepository.save(coupon);
    }


    public CouponResponse findById(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow
                (()-> new ShopException("coupon not found", HttpStatus.NOT_FOUND));
        checkActive(coupon);
        return couponMapper.toCouponResponse(coupon);
    }
    @Transactional
    public void deleteById(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(()-> new ShopException("coupon not found"
                , HttpStatus.NOT_FOUND));
        if (couponRepository.existsOrderByCouponId(id)) {
            throw  new ShopException("order with this coupon exist", HttpStatus.BAD_REQUEST);
        }
        couponRepository.delete(coupon);
    }

    public Page<CouponResponse> findAll(Pageable pageable) {
        Page<Coupon> coupons = couponRepository.findAll(pageable);
        coupons.forEach(this::checkActive);
        return coupons.map(couponMapper::toCouponResponse);
    }
    private void checkActive(Coupon coupon) {
        if(coupon.getEndDate().isBefore(LocalDateTime.now())) {
            coupon.setActive(false);
        }else {
            coupon.setActive(true);
        }
    }
}
