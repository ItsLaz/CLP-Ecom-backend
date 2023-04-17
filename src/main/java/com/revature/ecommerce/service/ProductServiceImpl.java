package com.revature.ecommerce.service;

import com.revature.ecommerce.dao.ProductRepository;
import com.revature.ecommerce.model.EcommerceProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<EcommerceProduct> getAllProducts() {
        return productRepository.findAll();
    }
}
