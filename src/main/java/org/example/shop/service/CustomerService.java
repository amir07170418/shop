package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.CustomerRequest;
import org.example.shop.dto.CustomerResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.CustomerMapper;
import org.example.shop.model.Cart;
import org.example.shop.model.Customer;
import org.example.shop.model.Role;
import org.example.shop.repository.CartRepository;
import org.example.shop.repository.CustomerRepository;
import org.example.shop.security.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ShopService<CustomerRequest, CustomerResponse> {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper
            , PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
    }
    @Transactional
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        if (customerRepository.existsByEmail(customerRequest.getEmail()) || customerRepository.existsByCustomerId(customerRequest.getCustomerId())) {
            throw new ShopException("Customer Already Exist", HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setRole(Role.CUSTOMER);
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        customerRepository.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);
        customer.setCart(cart);
        return customerMapper.toCustomerResponse(customer);
    }

    @Transactional
    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow
                (() -> new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        customerCheck(customerRequest, customer);
        customerMapper.updateCustomer(customerRequest, customer);
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }
    @Transactional
    public CustomerResponse editProfile(CustomerRequest customerRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer=customerRepository.findByEmail(email).orElseThrow(()->new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        customerCheck(customerRequest, customer);
        customerMapper.updateCustomer(customerRequest, customer);
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findById(Long id) {
        Customer customer=customerRepository.findById(id).orElseThrow
                (() -> new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        return customerMapper.toCustomerResponse(customer);
    }
    public CustomerResponse findByCustomerId(String customerId) {
        Customer customer=customerRepository.findByCustomerId(customerId).orElseThrow
                (() -> new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer=customerRepository.findById(id).orElseThrow
                (() -> new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
    }

    @Override
    public Page<CustomerResponse> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::toCustomerResponse);
    }
    private void customerCheck(CustomerRequest customerRequest,Customer  customer) {
        if (!customer.getEmail().equals(customerRequest.getEmail()) &&
                customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new ShopException("Customer With New Email Exist", HttpStatus.BAD_REQUEST);
        }
        if (!customer.getCustomerId().equals(customerRequest.getCustomerId()) &&
                customerRepository.existsByCustomerId(customerRequest.getCustomerId())) {
            throw new ShopException("Customer With New CustomerId Exist", HttpStatus.BAD_REQUEST);
        }
    }
}
