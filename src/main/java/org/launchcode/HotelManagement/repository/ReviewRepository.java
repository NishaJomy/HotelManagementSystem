package org.launchcode.HotelManagement.repository;

import org.launchcode.HotelManagement.models.Hotel;
import org.launchcode.HotelManagement.models.Review;
import org.launchcode.HotelManagement.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends CrudRepository<Review,Integer> {

    List<Review> findByHotel(Hotel hotel);

    List<Review> findByCreatedByUser(String createdByUser);
}
