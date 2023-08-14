package org.launchcode.HotelManagement.controllers;


import org.launchcode.HotelManagement.models.Hotel;
import org.launchcode.HotelManagement.models.Review;
import org.launchcode.HotelManagement.models.UpdateReview;
import org.launchcode.HotelManagement.models.User;
import org.launchcode.HotelManagement.repository.HotelRepository;
import org.launchcode.HotelManagement.repository.ReviewRepository;
import org.launchcode.HotelManagement.repository.UserRepository;
import org.launchcode.HotelManagement.service.CustomUserDetails;
import org.launchcode.HotelManagement.service.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller

public class ReviewController {
@Autowired
private UserRepository userRepository;

@Autowired
private ReviewRepository reviewRepository;
@Autowired
private HotelRepository hotelRepository;

@Autowired
 private CustomUserDetailsServices customUserDetailsServices;
//    @GetMapping("/reviews/add")
//    public String displayReviewForm(@RequestParam(name = "hId") Integer hId, Model model) {
//        model.addAttribute("hId", hId);
//        model.addAttribute(new Review());
//
//        return "reviews/add";
//    }

    @GetMapping("/reviews/add")
    public String displayReviewForm( Model model){

        model.addAttribute("review",new Review());
        model.addAttribute("hotels",hotelRepository.findAll());
        LocalDate currentDate = LocalDate.now();
        model.addAttribute("currentDate", currentDate);
        return "reviews/add";
    }
    @PostMapping("/reviews/add")
    public String addReview(Review review, Principal principal, @RequestParam("hId")  int hId) {
        {
            if (principal != null) {
                // Get the currently logged-in user's username
                String username = principal.getName();

                review.setCreatedByUser(username);
                // Get the User object from the UserDetailsService
                UserDetails userDetails = customUserDetailsServices.loadUserByUsername(username);
                User user = userRepository.findByUsername(userDetails.getUsername());

                // Set the User object on the review
                review.setUser(user);

               Optional<Hotel>  result =hotelRepository.findById(hId);
               if(result.isPresent()){
                   Hotel hotel=result.get();
                    review.setHotel(hotel);
              }

                reviewRepository.save(review);

                // Redirect to a success page or the restaurant details page
                //return "redirect:/view/{id}";
                return "redirect:/view/" + hId;

            }
            // Redirect to a login page or display an error message
            return "redirect:/login";
        }

    }

    @GetMapping("/reviews/{reviewId}/update")
    public String displayUpdateReviewForm(@PathVariable Integer reviewId, Model model) {
        // Retrieve the review from the database using the reviewId
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review == null) {
            // Handle the case where the review with the given reviewId doesn't exist
            return "redirect:/error"; // or display an error page
        }

        // Create an UpdateReview DTO and populate it with the current review data
        UpdateReview updateReview = new UpdateReview();
        updateReview.setUpdateReviewId(review.getReviewId());
        updateReview.setRating(review.getRating());
        updateReview.setComments(review.getComments());

        model.addAttribute("updateReview", updateReview);
        model.addAttribute("hotels",hotelRepository.findAll());

        return "reviews/update"; // Adjust the template path based on your project structure
    }

    @PostMapping("/reviews/{reviewId}/update")
    public String updateReview(@PathVariable Integer reviewId, @ModelAttribute UpdateReview updateReview) {
        // Retrieve the existing review from the database using reviewId
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        if (existingReview == null) {
            // Handle the case where the review with the given reviewId doesn't exist
            return "redirect:/home"; // or display an error page
        }

        // Update the review properties with the data from the UpdateReview entity
        existingReview.setRating(updateReview.getRating());
        existingReview.setComments(updateReview.getComments());

        // Save the updated review back to the database
        reviewRepository.save(existingReview);

        // Redirect to the view page for the updated review
        return "redirect:/home" ; // or adjust the URL based on your project structure
    }
    @PostMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Integer reviewId, Principal principal) {
        // Get the currently logged-in username
        String loggedInUsername = principal.getName();

        // Retrieve the review from the database based on the reviewId
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        // Check if the review exists and if the logged-in user is the review creator
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            if (review.getCreatedByUser().equals(loggedInUsername)) {
                // User is authorized to delete the review, so delete it from the database
                reviewRepository.delete(review);
                // Redirect to a success page or another appropriate page after deletion
                return "redirect:/home"; // or adjust the URL based on your project structure
            } else {
                // User is not authorized to delete the review
                // You can handle this case with an error message or redirect to an error page
                return "error"; // or return a view that shows an error message
            }
        } else {
            // Review not found in the database
            // You can handle this case with an error message or redirect to an error page
            return "error"; // or return a view that shows an error message
        }
    }
}






