package com.ShoeVibes.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;

    public Cart() {
    }
    public Cart(String item) {
        this.item=item;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }


}

