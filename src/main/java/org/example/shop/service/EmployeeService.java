package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.EmployeeRequest;
import org.example.shop.dto.EmployeeResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.EmployeeMapper;
import org.example.shop.model.Employee;
import org.example.shop.model.Role;
import org.example.shop.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements ShopService<EmployeeRequest, EmployeeResponse> {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        if (employeeRepository.existsByEmail(employeeRequest.getEmail()) ||
                employeeRepository.existsByEmployeeId(employeeRequest.getEmployeeId())) {
            throw new ShopException("Employee Already Exist", HttpStatus.BAD_REQUEST);
        }
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        employee.setRole(Role.ADMIN);
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }
    @Transactional
    @Override
    public EmployeeResponse update(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow
                (() -> new ShopException("Employee Not Found", HttpStatus.NOT_FOUND));
        employeeCheck(employeeRequest, employee);
        employeeMapper.updateEmployee(employeeRequest, employee);
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }
    @Transactional
    public EmployeeResponse editProfile(EmployeeRequest employeeRequest) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee=employeeRepository.findByEmail(email).orElseThrow(() -> new ShopException
                ("Employee Not Found", HttpStatus.NOT_FOUND));
        employeeCheck(employeeRequest, employee);
        employeeMapper.updateEmployee(employeeRequest, employee);
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }
    @Override
    public EmployeeResponse findById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow
                (() -> new ShopException("Employee Not Found", HttpStatus.NOT_FOUND));
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public void deleteById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow
                (() -> new ShopException("Employee Not Found", HttpStatus.NOT_FOUND));
        employeeRepository.delete(employee);
    }

    @Override
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(employeeMapper::toEmployeeResponse);
    }
    private void employeeCheck(EmployeeRequest employeeRequest, Employee employee) {
        if (!employee.getEmail().equals(employeeRequest.getEmail()) &&
                employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new ShopException("Employee With New Email Already Exist", HttpStatus.BAD_REQUEST);
        }
        if (!employee.getEmployeeId().equals(employeeRequest.getEmployeeId()) &&
                employeeRepository.existsByEmployeeId(employeeRequest.getEmployeeId())) {
            throw new ShopException("Employee With new EmployeeId Already Exist", HttpStatus.BAD_REQUEST);
        }
    }
}
