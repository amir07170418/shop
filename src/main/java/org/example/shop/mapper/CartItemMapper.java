package org.example.shop.mapper;

import org.example.shop.dto.CartItemRequest;
import org.example.shop.dto.CartItemResponse;
import org.example.shop.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "cart.id",target = "cartId")
    @Mapping(source = "product.id",target = "productId")
    CartItemResponse toCartItemResponse(CartItem cartItem);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    CartItem toCartItem(CartItemRequest cartItemRequest);
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateCartItem(CartItemRequest cartItemRequest, @MappingTarget CartItem cartItem);
}
