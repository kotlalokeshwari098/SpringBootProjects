package com.javaspring.webapp.controller;


import com.javaspring.webapp.model.Product;
import com.javaspring.webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@RestController
public class ProductController {

    @Autowired
    ProductService service;


    @GetMapping("/products")
    public List<Product> getProducts(){

        return service.getProducts();
    }

    @RequestMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){

        return service.getProductById(id);
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody  Product prod){

        service.addProducts(prod);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody  Product prod){
        service.updateProduct(prod);
    }
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
    }
}
