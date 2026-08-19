package org.example.shop.repository;

import org.example.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByCustomerId(String customerId);
    Optional<Customer> findByCustomerId(String customerId);
    Optional<Customer> findByEmail(String email);
    @Query("select c.id from Customer c where c.email=:email")
    Optional<Long> findIdByEmail(String email);
}
