package com.revature.ecommerce.service;

import com.revature.ecommerce.dao.CartItemRepository;
import com.revature.ecommerce.dao.ProductRepository;
import com.revature.ecommerce.dao.UserRepository;
import com.revature.ecommerce.exceptions.ProductNotFoundException;
import com.revature.ecommerce.exceptions.ResourceNotFoundException;
import com.revature.ecommerce.exceptions.UsernameNotFoundException;
import com.revature.ecommerce.model.CartItem;
import com.revature.ecommerce.model.EcommerceProduct;
import com.revature.ecommerce.model.EcommerceUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public CartServiceImpl(CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CartItem> getCardItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public CartItem addToCart(Long userId, Long productId, Integer quantity) {
        EcommerceUser user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with the given user ID."));

        EcommerceProduct product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with the given product ID."));

        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);


        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            // Update the quantity of the existing cart item
            cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Create a new cart item
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(quantity);
        }

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void removeOneFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("Cart item not found with the given cart item ID"));

        if (cartItem.getQuantity() > 1) {
            // Decrement the quantity of the cart item
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        } else {
            // Delete the cart item if the quantity is 1
            cartItemRepository.deleteById(cartItemId);
        }
    }
}
