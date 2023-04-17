package com.revature.ecommerce.service;

import com.revature.ecommerce.dao.CartItemRepository;
import com.revature.ecommerce.dao.OrderRepository;
import com.revature.ecommerce.dao.UserRepository;
import com.revature.ecommerce.exceptions.UsernameNotFoundException;
import com.revature.ecommerce.model.CartItem;
import com.revature.ecommerce.model.EcommerceUser;
import com.revature.ecommerce.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CustomerOrder placeOrder(Long userId) {
        EcommerceUser user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with the given user ID"));
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        if(cartItems.isEmpty()){
            throw new IllegalStateException("Cannot place an order with an empty cart.");
        }

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setUser(user);
        customerOrder.setOrderDate(LocalDateTime.now());
        customerOrder.setStatus("PLACED");

        CustomerOrder savedCustomerOrder = orderRepository.save(customerOrder);

        for(CartItem cartItem : cartItems){
            cartItem.setCustomerOrder(savedCustomerOrder);
            cartItem.setUser(null); //clear user reference
            cartItemRepository.save(cartItem);
        }

        savedCustomerOrder.setCartItems(cartItemRepository.findByCustomerOrderId(savedCustomerOrder.getId()));
        return savedCustomerOrder;
    }

    @Override
    public List<CustomerOrder> getUserOrders(Long userId) {
        EcommerceUser user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with the given user ID."));
        return orderRepository.findByUser(user);
    }
}
