package com.LOGIN.LOGIN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.LOGIN.LOGIN.model.User;
import com.LOGIN.LOGIN.repository.ProductRepository;
import com.LOGIN.LOGIN.repository.UserRepository;



@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    private final UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping ("/register")
    public String showRegisterPage () {
        return "registerPage.html";
    }

    @GetMapping ("/")
    public String index (Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "index.html";
    }

    @PostMapping("/registerUser")
    public String registerUser (@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam ("username") String username, @RequestParam ("password") String password){
		User user = new User();

		user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        String encryptedPassword = bcryptEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        

		return "redirect:/";
	}
   
}