package org.launchcode.HotelManagement.repository;

import org.launchcode.HotelManagement.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Integer> {

}
