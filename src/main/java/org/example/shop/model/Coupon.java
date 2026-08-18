package org.example.shop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    private Integer discountPercent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long minimumOrderAmount;
    private Long maximumDiscount;
    private Integer usageLimit;
    private Integer usageCount;
    private boolean active;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "coupon")
    private Set<Order> orders;

    public Coupon(Long id, String code, Integer discountPercent, LocalDateTime startDate, LocalDateTime endDate
            , Long miniumOrderAmount, Long maximumDiscount, Integer usageLimit, Integer usageCount, boolean active) {
        this.id = id;
        this.code = code;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minimumOrderAmount = miniumOrderAmount;
        this.maximumDiscount = maximumDiscount;
        this.usageLimit = usageLimit;
        this.usageCount = usageCount;
        this.active = active;
    }
    public Coupon() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public void setMinimumOrderAmount(Long miniumOrderAmount) {
        this.minimumOrderAmount = miniumOrderAmount;
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

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(id, coupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountPercent=" + discountPercent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", miniumOrderAmount=" + minimumOrderAmount +
                ", maximumDiscount=" + maximumDiscount +
                ", usageLimit=" + usageLimit +
                ", usageCount=" + usageCount +
                ", active=" + active +
                '}';
    }
}
