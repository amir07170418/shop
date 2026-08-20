package org.example.shop.repository;

import org.example.shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select count(i)>0 from CartItem  i where i.cart.id=:cartId and i.product.id=:productId")
    boolean existsCartItemByCartIdAndProductId(@Param("cartId") Long cartId,@Param("productId") Long productId);
    @Query("select  i from CartItem  i where i.cart.id=:cartId and i.product.id=:productId")
    Optional<CartItem> findCartItemByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
