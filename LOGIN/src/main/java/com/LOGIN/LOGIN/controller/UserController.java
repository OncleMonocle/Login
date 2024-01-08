package com.LOGIN.LOGIN.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.LOGIN.LOGIN.model.User;
import com.LOGIN.LOGIN.repository.ProductRepository;
import com.LOGIN.LOGIN.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@CrossOrigin
public class UserController {
    
    private final UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @GetMapping ("/")
    public String index (Model model) {
        model.addAttribute("productList", productRepository.findAll());
        System.out.println(userRepository.findById(1).get().getFirstName());
        return "index.html";
    }

    @GetMapping ("/register")
    public String showRegisterPage () {
        return "registerPage.html";
    }

    // @GetMapping ("/registerUser")
    // public String registerUser () {
    //     User user = new User();
    //     user.setUsername("Kalle");
    //     user.setFirstName("Lör");
    //     user.setLastName("Liljekrantz");
    //     user.setEmail("email.email.com");
    //     user.setPassword("1234");

    //     System.out.println("bra");

    //     userRepository.save(user);

    //     return "redirect:/";
    // }
    @PostMapping("/registerUser")
    public String registerUser (@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam ("username") String username, @RequestParam ("password") String password){

		User user = new User();

		user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

		return "redirect:/";
	}
    
}