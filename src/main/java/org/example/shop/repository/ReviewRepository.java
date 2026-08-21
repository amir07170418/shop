package org.example.shop.repository;

import org.example.shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select count(r)>0 from Review r where r.product.id=:productId and r.customer.id=:customerId")
    boolean existsByProductIdAndCustomerId(Long productId, Long customerId);
    @Query("select count(item)>0 from OrderItem item where item.product.id=:productId and item.order.customer.id=:customerId " +
            "and item.order.payment.status=org.example.shop.model.PaymentStatus.SUCCESS")
    boolean checkBuyProduct(Long productId, Long customerId);
}
