package com.revature.ecommerce.service;

import com.revature.ecommerce.model.CustomerOrder;

import java.util.List;

public interface OrderService {
    CustomerOrder placeOrder(Long userId);
    List<CustomerOrder> getUserOrders(Long userId);
}
