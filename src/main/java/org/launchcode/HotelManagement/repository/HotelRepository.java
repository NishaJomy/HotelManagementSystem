package org.launchcode.HotelManagement.repository;

import java.awt.print.Book;

import org.launchcode.HotelManagement.models.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository <Hotel,Integer>{
}