package com.revature.ecommerce.service;

import com.revature.ecommerce.dao.CartItemRepository;
import com.revature.ecommerce.dao.ProductRepository;
import com.revature.ecommerce.dao.UserRepository;
import com.revature.ecommerce.exceptions.ProductNotFoundException;
import com.revature.ecommerce.exceptions.UsernameNotFoundException;
import com.revature.ecommerce.model.CartItem;
import com.revature.ecommerce.model.EcommerceProduct;
import com.revature.ecommerce.model.EcommerceUser;
import org.springframework.stereotype.Service;

import java.util.List;

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

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
