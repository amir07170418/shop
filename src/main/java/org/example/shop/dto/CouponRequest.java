package org.example.shop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CouponRequest {
    @NotBlank
    private String code;
    @NotNull
    private Integer discountPercent;
    @NotNull
    private Long expiredHours;
    @NotNull
    private Long minimumOrderAmount;
    @NotNull
    private Long maximumDiscount;
    @NotNull
    private Integer usageLimit;

    public CouponRequest(String code, Integer discountPercent, Long expiredHours,
                         Long miniumOrderAmount, Long maximumDiscount, Integer usageLimit) {
        this.code = code;
        this.discountPercent = discountPercent;
        this.expiredHours = expiredHours;
        this.minimumOrderAmount = miniumOrderAmount;
        this.maximumDiscount = maximumDiscount;
        this.usageLimit = usageLimit;
    }

    public CouponRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Long getExpiredHours() {
        return expiredHours;
    }

    public void setExpiredHours(Long expiredHours) {
        this.expiredHours = expiredHours;
    }

    public Long getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public void setMinimumOrderAmount(Long minimumOrderAmount) {
        this.minimumOrderAmount = minimumOrderAmount;
    }

    public Long getMaximumDiscount() {
        return maximumDiscount;
    }

    public void setMaximumDiscount(Long maximumDiscount) {
        this.maximumDiscount = maximumDiscount;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    @Override
    public String toString() {
        return "CouponRequest{" +
                "code='" + code + '\'' +
                ", discountPercent=" + discountPercent +
                ", expiredHours=" + expiredHours +
                ", miniumOrderAmount=" + minimumOrderAmount +
                ", maximumDiscount=" + maximumDiscount +
                ", usageLimit=" + usageLimit +
                '}';
    }
}
