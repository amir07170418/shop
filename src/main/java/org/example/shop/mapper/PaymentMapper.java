package org.example.shop.mapper;

import org.example.shop.dto.PaymentResponse;
import org.example.shop.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "order.id",target = "orderId")
    PaymentResponse toPaymentResponse(Payment payment);
}
