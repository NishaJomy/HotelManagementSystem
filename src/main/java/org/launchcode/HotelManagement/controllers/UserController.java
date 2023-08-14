package org.launchcode.HotelManagement.controllers;

import org.launchcode.HotelManagement.dto.UserDto;
import org.launchcode.HotelManagement.models.Review;
import org.launchcode.HotelManagement.models.User;
import org.launchcode.HotelManagement.repository.ReviewRepository;
import org.launchcode.HotelManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
@Autowired
private ReviewRepository reviewRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String loggedInUsername = principal.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loggedInUsername);
        model.addAttribute("userDetails" , userDetails);

        // Retrieve the reviews associated with the logged-in user
        List<Review> userReviews = reviewRepository.findByCreatedByUser(loggedInUsername);

        // Add the user's reviews to the model
        model.addAttribute("userReviews", userReviews);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {

        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "register";

        }
        userService.save(userDto);
        return "redirect:/register?success";
    }

}
