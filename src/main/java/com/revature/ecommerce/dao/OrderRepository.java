package com.revature.ecommerce.dao;

import com.revature.ecommerce.model.EcommerceUser;
import com.revature.ecommerce.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUser(EcommerceUser user);
}
