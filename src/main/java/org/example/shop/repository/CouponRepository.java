package org.example.shop.repository;

import org.example.shop.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByCode(String code);
    @Query("select count(o)>0 from Order o where o.coupon.id=:id")
    boolean existsOrderByCouponId(@Param("id") Long id);
    Optional<Coupon> findByCode(String code);
}
