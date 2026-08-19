package org.example.shop.mapper;

import org.example.shop.dto.OrderItemRequest;
import org.example.shop.dto.OrderItemResponse;
import org.example.shop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "order.id",target = "orderId")
    @Mapping(source = "product.id",target = "productId")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateOrderItem(OrderItemRequest orderItemRequest, @MappingTarget OrderItem orderItem);
}
