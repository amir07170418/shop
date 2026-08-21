package org.example.shop.repository;

import org.example.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    @Query("select count(p)>0 from Product p join p.orderItems i where i.product.id=:id")
    boolean existsInOrderItemsById(@Param("id") Long id);
}
