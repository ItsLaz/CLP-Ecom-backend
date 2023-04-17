package com.revature.ecommerce.service;

import com.revature.ecommerce.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCardItems(Long userId);
    CartItem addToCart(Long userId, Long productId, Integer quantity);
    void removeFromCart(Long cartItemId);
}
