package com.ShoeVibes.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "favorite_product", joinColumns = @JoinColumn(name = "favorite_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    //private boolean isActive = true;
    public Favorite() {
    }

    public Favorite(Long id, User user, Set<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
