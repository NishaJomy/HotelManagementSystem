package org.launchcode.HotelManagement.controllers;

import org.launchcode.HotelManagement.models.Hotel;
import org.launchcode.HotelManagement.repository.HotelRepository;
import org.launchcode.HotelManagement.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Optional;

@Controller
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ReviewRepository reviewRepository;


    @GetMapping("")
    public String displayHome(Model model) {

        return "home";
    }
    @GetMapping("home")
    public String displayHotels(Model model) {
        Iterable<Hotel> hotels;
        hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "index";
    }

    @GetMapping("add")

    public String displayAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());

        return "add";
    }

    @PostMapping("add")
    public String renderAddForm(@ModelAttribute Hotel hotel) {
       hotelRepository.save(hotel);
        return "redirect:";
    }

    @GetMapping("view/{id}")
    public String viewHotel(Model model, @PathVariable Integer id) {
        Optional<Hotel> result = hotelRepository.findById(id);
        if (result.isPresent()) {
            Hotel hotel= result.get();

            model.addAttribute("hotel", hotel);
        }
        return "view";
    }

    @GetMapping("delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("hotels", hotelRepository.findAll());
        return "delete";
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {

        return "signUp";
    }


}



