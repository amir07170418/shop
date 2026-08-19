package org.example.shop.mapper;

import org.example.shop.dto.EmployeeRequest;
import org.example.shop.dto.EmployeeResponse;
import org.example.shop.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toEmployeeResponse(Employee employee);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Employee toEmployee(EmployeeRequest employeeRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEmployee(EmployeeRequest employeeRequest, @MappingTarget Employee employee);
}
