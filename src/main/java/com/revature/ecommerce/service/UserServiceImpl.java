package com.revature.ecommerce.service;

import com.revature.ecommerce.dao.UserRepository;
import com.revature.ecommerce.exceptions.BadCredentialsException;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UsernameNotFoundException;
import com.revature.ecommerce.model.EcommerceUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public EcommerceUser signUp(EcommerceUser user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public EcommerceUser signIn(String username, String password) {
        EcommerceUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!user.getPassword().equals(password)){
            throw new BadCredentialsException("Invalid password.");
        }
        return user;
    }
}
