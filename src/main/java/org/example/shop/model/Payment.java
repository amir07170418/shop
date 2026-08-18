package org.example.shop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private String transactionId;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(Long id, Long amount, String transactionId, LocalDateTime paymentDate, PaymentStatus status, Order order) {
        this.id = id;
        this.amount = amount;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
        this.status = status;
        this.order = order;
    }

    public Payment() {}

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
