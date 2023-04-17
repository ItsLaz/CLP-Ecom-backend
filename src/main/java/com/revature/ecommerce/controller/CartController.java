package com.revature.ecommerce.controller;

import com.revature.ecommerce.model.CartItem;
import com.revature.ecommerce.service.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/cart")
public class CartController {

    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId){
        List<CartItem> cartItems = cartService.getCardItems(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<CartItem> addToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Integer quantity){
        CartItem cartItem = cartService.addToCart(userId, productId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId){
        cartService.removeFromCart(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
