package com.revature.ecommerce.service;


import com.revature.ecommerce.model.EcommerceUser;

public interface UserService {
    EcommerceUser signUp(EcommerceUser user);
    EcommerceUser signIn(String username, String password);
}
