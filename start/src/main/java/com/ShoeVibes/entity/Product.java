package com.ShoeVibes.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> b8391c76da59ef04a1c31c0327da3760070a4644
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String imageUrl;

    @ManyToMany(mappedBy = "products")
    private Set<Favorite> favorites;

<<<<<<< HEAD
    @OneToMany(mappedBy = "product")
    private Set<Image> images;

=======
>>>>>>> b8391c76da59ef04a1c31c0327da3760070a4644
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

<<<<<<< HEAD
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
=======
    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
>>>>>>> b8391c76da59ef04a1c31c0327da3760070a4644

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, Integer stock, String imageUrl, Set<Favorite> favorites, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.favorites = favorites;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }
}
