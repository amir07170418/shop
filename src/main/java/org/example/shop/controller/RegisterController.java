package org.example.shop.controller;

import org.example.shop.dto.CustomerRequest;
import org.example.shop.dto.CustomerResponse;
import org.example.shop.dto.EmployeeRequest;
import org.example.shop.dto.EmployeeResponse;
import org.example.shop.service.CustomerService;
import org.example.shop.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    public RegisterController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }
    @PostMapping("/customer")
    public CustomerResponse registerCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.save(customerRequest);
    }
    @PostMapping("/employee")
    public EmployeeResponse registerEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

}
