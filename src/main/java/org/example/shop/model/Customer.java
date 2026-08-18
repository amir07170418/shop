package org.example.shop.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends User{
    @Column(unique = true)
    private String customerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToMany(fetch =  FetchType.LAZY,mappedBy = "customer")
    private Set<Order> orders;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private Set<Review> reviews;

    public Customer( String customerId, Cart cart, Set<Order> orders, Set<Review> reviews) {
        super();
        this.customerId = customerId;
        this.cart = cart;
        this.orders = orders;
        this.reviews = reviews;
    }

    public Customer(Long id, String firstName, String lastName, String email, String password, String address,
                    Integer age, Role role, String customerId, Cart cart, Set<Order> orders, Set<Review> reviews) {
        super(id, firstName, lastName, email, password, address, age, role);
        this.customerId = customerId;
        this.cart = cart;
        this.orders = orders;
        this.reviews = reviews;
    }

    public Customer() {}

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                super.toString() +
                '}';
    }
}
