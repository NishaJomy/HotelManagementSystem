package org.launchcode.HotelManagement.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private int hId;
    private String hotelName;
    private String location;
    @OneToMany(mappedBy = "hotel")
    private List<Review> reviews;


    public Hotel() {
    }

    public Hotel(int hId, String hotelName, String location, List<Review> reviews) {
        this.hId = hId;
        this.hotelName = hotelName;
        this.location = location;
        this.reviews = reviews;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
