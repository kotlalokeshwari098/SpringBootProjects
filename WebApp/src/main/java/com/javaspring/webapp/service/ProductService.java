package com.javaspring.webapp.service;

import com.javaspring.webapp.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
//it is @Component behind the scene so object will be created first
//then autowired from productcontroller
public class ProductService {


    List<Product> products= new ArrayList<>(Arrays.asList(
            new Product(101,"iphone",50000),
            new Product(102,"samsung",40000)));
    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(n->n.getProdId()==id)
                .findFirst().orElse(new Product(100,"No Item",0));
    }

    public void addProducts(Product prod){
        System.out.println(prod);
        products.add(prod);
    }

    public void updateProduct(Product prod) {
        int index=0;
        for(int i=0;i<products.size();i++)
            if(products.get(i).getProdId()==prod.getProdId())
                index=i;

        products.set(index,prod);
    }


    public void deleteProduct(int id) {
        int index=0;
        for(int i=0;i<products.size();i++)
            if(products.get(i).getProdId()==id)
                index=i;
        products.remove(index);
    }
}
