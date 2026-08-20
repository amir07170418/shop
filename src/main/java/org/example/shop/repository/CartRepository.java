package org.example.shop.repository;

import org.example.shop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Query("delete from CartItem i where i.cart.id=:id")
    void deleteCartItems(@Param("id") Long id);
}
