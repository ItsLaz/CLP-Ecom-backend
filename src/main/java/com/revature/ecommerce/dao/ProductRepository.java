package com.revature.ecommerce.dao;

import com.revature.ecommerce.model.EcommerceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<EcommerceProduct, Long> {
}
