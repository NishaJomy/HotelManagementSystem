package org.launchcode.HotelManagement.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private int reviewId;
    @NonNull
    private int rating;
    private String comments;

    private String createdByUser;

    @ManyToOne
    @JoinColumn(name = "hotel_hId")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public Review() {
    }

    public Review(int reviewId, int rating, String comments, String createdByUser, Hotel hotel, User user) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comments = comments;
        this.createdByUser = createdByUser;
        this.hotel = hotel;
        this.user = user;
    }


    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
