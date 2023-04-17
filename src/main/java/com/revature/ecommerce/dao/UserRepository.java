package com.revature.ecommerce.dao;

import com.revature.ecommerce.model.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EcommerceUser, Long> {
    Optional<EcommerceUser> findByUsername(String username);
    Optional<EcommerceUser> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
