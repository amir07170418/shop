package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.OrderRequest;
import org.example.shop.dto.OrderResponse;
import org.example.shop.dto.PaymentResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.OrderMapper;
import org.example.shop.mapper.PaymentMapper;
import org.example.shop.model.*;
import org.example.shop.repository.CouponRepository;
import org.example.shop.repository.CustomerRepository;
import org.example.shop.repository.OrderItemRepository;
import org.example.shop.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final CouponRepository couponRepository;
    private final CartService cartService;
    private final CouponService couponService;
    private final PaymentMapper paymentMapper;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper, CouponRepository couponRepository, CartService cartService, CouponService couponService, PaymentMapper paymentMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.couponRepository = couponRepository;
        this.cartService = cartService;
        this.couponService = couponService;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    public OrderResponse checkout(OrderRequest orderRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ShopException("customer not found", HttpStatus.NOT_FOUND));
        Cart cart = customer.getCart();
        if (cart.getCartItems().isEmpty()) {
            throw new ShopException("cart is empty", HttpStatus.BAD_REQUEST);
        }
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem c : cart.getCartItems()) {
            OrderItem orderItem = createOrderItem(c);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        if (orderRequest.getCode() != null) {
            Coupon coupon = couponRepository.findByCode(orderRequest.getCode()).orElseThrow(
                    () -> new ShopException("coupon not found", HttpStatus.NOT_FOUND));
            if (coupon.isActive() && coupon.getUsageCount() < coupon.getUsageLimit()) {
                order.setCoupon(coupon);
            } else {
                throw new ShopException("coupon is not active", HttpStatus.BAD_REQUEST);
            }
        }
        if (order.getCoupon() != null) {
            order.setFinalPrice(useCoupon(calculateFinalPrice(order), order.getCoupon()));
        } else {
            order.setFinalPrice(calculateFinalPrice(order));
        }
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        cartService.clearCart();
        return orderMapper.toOrderResponse(order);
    }
    @Transactional
    public PaymentResponse goPayment(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ShopException("order not found", HttpStatus.NOT_FOUND));
        if (order.getPayment() != null) {
            throw new ShopException("payment already exists", HttpStatus.BAD_REQUEST);
        }
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getFinalPrice());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId(order.getId().toString()+order.getCustomer().getId());
        payment.setStatus(PaymentStatus.PENDING);
        order.setPayment(payment);
        orderRepository.save(order);
        return  paymentMapper.toPaymentResponse(payment);
    }
    @Transactional
    public PaymentResponse pay(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ShopException("order not found", HttpStatus.NOT_FOUND));
        checkUser(order);
        Payment payment = order.getPayment();
        if (payment == null) {
            throw new ShopException("payment not found", HttpStatus.NOT_FOUND);
        }
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new ShopException("payment cannot be processed", HttpStatus.BAD_REQUEST);
        }
        payment.setStatus(PaymentStatus.SUCCESS);
        order.setPayment(payment);
        orderRepository.save(order);
        return  paymentMapper.toPaymentResponse(payment);
    }
    @Transactional
    public PaymentResponse cancelPayment(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ShopException("order not found", HttpStatus.NOT_FOUND));
        checkUser(order);
        Payment payment = order.getPayment();
        if (payment == null) {
            throw new ShopException("payment not found", HttpStatus.NOT_FOUND);
        }
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new ShopException("payment cannot be canceled", HttpStatus.BAD_REQUEST);
        }
        payment.setStatus(PaymentStatus.FAILED);
        order.setPayment(payment);
        orderRepository.save(order);
        return  paymentMapper.toPaymentResponse(payment);
    }
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow
                (()-> new ShopException("order not found", HttpStatus.NOT_FOUND));
        checkUser(order);
        orderRepository.delete(order);
    }
    private void checkUser(Order order){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                ()-> new ShopException("customer not found", HttpStatus.NOT_FOUND));
        if (!order.getCustomer().getId().equals(customer.getId())) {
            throw new ShopException("Access denied", HttpStatus.FORBIDDEN);
        }
    }
    private OrderItem createOrderItem(CartItem cartItem) {
        Product product = cartItem.getProduct();
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProduct(product);
        if (cartItem.getQuantity() > product.getStock()) {
            throw new ShopException("quantity bigger than stock in" + cartItem.getProduct().getName(), HttpStatus.BAD_REQUEST);
        }
        product.setStock(product.getStock() - cartItem.getQuantity());
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
        return orderItem;
    }


    private Long calculateFinalPrice(Order order) {
        long finalPrice = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            finalPrice += orderItem.getTotalPrice();
        }
        return finalPrice;
    }

    private Long useCoupon(Long price, Coupon coupon) {
        long finalPrice;
        if (coupon.isActive() && coupon.getUsageCount() < coupon.getUsageLimit() && coupon.getEndDate().isAfter(LocalDateTime.now())) {
            if (coupon.getMinimumOrderAmount() > price) {
                throw new ShopException("minimum order amount is greater than price", HttpStatus.BAD_REQUEST);
            }
            if (coupon.getMaximumDiscount() < price * coupon.getDiscountPercent() / 100) {
                finalPrice = price - coupon.getMaximumDiscount();
            } else {
                finalPrice = price - price * coupon.getDiscountPercent()/100;
            }
        } else {
            throw new ShopException("coupon is not active", HttpStatus.BAD_REQUEST);
        }
        couponService.addUsage(coupon.getId());
        return finalPrice;
    }
}
