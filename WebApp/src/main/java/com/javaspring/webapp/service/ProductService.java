package com.javaspring.webapp.service;

import com.javaspring.webapp.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class ProductService {


    List<Product> products= Arrays.asList(
            new Product(101,"iphone",50000),
            new Product(102,"samsung",40000));
    public List<Product> getProducts() {
        return products;
    }
}
