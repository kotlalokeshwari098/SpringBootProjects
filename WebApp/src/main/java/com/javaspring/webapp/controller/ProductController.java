package com.javaspring.webapp.controller;


import com.javaspring.webapp.model.Product;
import com.javaspring.webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Component
@RestController
public class ProductController {

    @Autowired
    ProductService service;


    @RequestMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }
}
