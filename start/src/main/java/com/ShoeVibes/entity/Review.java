package com.ShoeVibes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
<<<<<<< HEAD
    private LocalDateTime createdAt;
=======

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
>>>>>>> b8391c76da59ef04a1c31c0327da3760070a4644

//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

    public Review() {
    }

    public Review(Long id, String reviewerName, String comment, Integer rating, Product product, LocalDateTime createdAt) {
        this.id = id;
        this.reviewerName = reviewerName;
        this.comment = comment;
        this.rating = rating;
        this.product = product;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
