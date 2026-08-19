package org.example.shop.repository;

import org.example.shop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
    Optional<Employee> findByEmail(String email);
}
