package com.revature.ecommerce.controller;

import com.revature.ecommerce.model.EcommerceProduct;
import com.revature.ecommerce.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/products") @CrossOrigin("*")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<EcommerceProduct>> getAllProducts(){
        List<EcommerceProduct> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
