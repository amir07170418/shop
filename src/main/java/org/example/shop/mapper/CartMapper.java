package org.example.shop.mapper;

import org.example.shop.dto.CartRequest;
import org.example.shop.dto.CartResponse;
import org.example.shop.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(target = "totalPrice",ignore = true)
    CartResponse toCartResponse(Cart cart);
    @Mapping(target = "id", ignore = true)
    Cart toCart(CartRequest cartRequest);

}
