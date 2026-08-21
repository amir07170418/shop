package org.example.shop.controller;

import org.example.shop.dto.OrderRequest;
import org.example.shop.dto.OrderResponse;
import org.example.shop.dto.PaymentResponse;
import org.example.shop.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/checkout")
    public OrderResponse checkout(@RequestBody OrderRequest orderRequest) {
        return orderService.checkout(orderRequest);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/makePayment/{id}")
    public PaymentResponse paymentOrder(@PathVariable Long id) {
        return orderService.goPayment(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/pay/{id}")
    public PaymentResponse payOrder(@PathVariable Long id) {
        return orderService.pay(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/cancelPayment/{id}")
    public PaymentResponse cancelPayment(@PathVariable Long id) {
        return orderService.cancelPayment(id);
    }
}
