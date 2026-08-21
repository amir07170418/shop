package org.example.shop.controller;

import org.example.shop.dto.CouponRequest;
import org.example.shop.dto.CouponResponse;
import org.example.shop.service.CouponService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CouponResponse create(@RequestBody CouponRequest couponRequest) {
        return couponService.save(couponRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public CouponResponse findById(@PathVariable Long id) {
        return  couponService.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/{hours}")
    public CouponResponse addHoursToCoupon(@PathVariable Integer hours,@PathVariable Long id){
        return couponService.addHoursToCoupon(hours,id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deActive/{id}")
    public CouponResponse deActive(@PathVariable Long id){
        return couponService.deActivateCoupon(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<CouponResponse> findAll(Pageable pageable) {
        return  couponService.findAll(pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCoupon(@PathVariable Long id){
        couponService.deleteById(id);
    }




}
