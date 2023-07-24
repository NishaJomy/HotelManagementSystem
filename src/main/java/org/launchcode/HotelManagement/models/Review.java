package org.launchcode.HotelManagement.models;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private int reviewId;
    private float rating;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "hotel_hId")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {
    }

    public Review(int reviewId, float rating, String comments, Hotel hotel, User user) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comments = comments;
        this.hotel = hotel;
        this.user = user;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
