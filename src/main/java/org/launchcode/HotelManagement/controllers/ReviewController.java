package org.launchcode.HotelManagement.controllers;


import org.launchcode.HotelManagement.models.Review;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @PreAuthorize("USER")
    @GetMapping("add")
    public String displayReviewForm( Model model){
        model.addAttribute(new Review());
        return "reviews/add";
    }
}
