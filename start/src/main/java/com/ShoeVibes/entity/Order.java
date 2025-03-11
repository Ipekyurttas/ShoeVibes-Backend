package com.ShoeVibes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int totalPrice;
    private LocalDateTime dateTime;

    public Order() {
    }
    public Order(String address, int totalPrice, LocalDateTime dateTime) {
        this.address = address;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
