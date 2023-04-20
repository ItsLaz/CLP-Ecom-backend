package com.revature.ecommerce.dao;

import com.revature.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    List<CartItem> findByCustomerOrderId(Long id);

    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}
