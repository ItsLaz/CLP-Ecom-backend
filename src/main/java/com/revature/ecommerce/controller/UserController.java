package com.revature.ecommerce.controller;

import com.revature.ecommerce.model.EcommerceUser;
import com.revature.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<EcommerceUser> signUp(@RequestBody EcommerceUser user){
        EcommerceUser newUser = userService.signUp(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<EcommerceUser> signIn(@RequestBody EcommerceUser user){
        EcommerceUser signedInUser = userService.signIn(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(signedInUser, HttpStatus.OK);
    }
}
