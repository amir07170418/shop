package org.example.shop.mapper;

import org.example.shop.dto.OrderRequest;
import org.example.shop.dto.OrderResponse;
import org.example.shop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "payment.status",target = "paymentStatus")
    @Mapping(source = "coupon.code",target = "couponCode")
    OrderResponse toOrderResponse(Order order);
    @Mapping(target = "orderStatus",ignore = true)
    @Mapping(target = "orderDate",ignore = true)
    @Mapping(target = "finalPrice",ignore = true)
    @Mapping(target = "id",ignore = true)
    Order  toOrder(OrderRequest orderRequest);
}
