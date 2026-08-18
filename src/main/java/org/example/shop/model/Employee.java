package org.example.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Employee extends User{
    @Column(unique = true)
    private String employeeId;

    public Employee(Long id, String firstName, String lastName, String email, String password
            , String address, Integer age, Role role, String employeeId) {
        super(id, firstName, lastName, email, password, address, age, role);
        this.employeeId = employeeId;
    }
    public Employee(String employeeId) {
        super();
        this.employeeId = employeeId;
    }
    public Employee() {}

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                super.toString() +
                '}';
    }
}
