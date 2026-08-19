package org.example.shop.mapper;

import org.example.shop.dto.CustomerRequest;
import org.example.shop.dto.CustomerResponse;
import org.example.shop.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Customer  toCustomer(CustomerRequest customerRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateCustomer(CustomerRequest customerRequest, @MappingTarget Customer customer);
}
