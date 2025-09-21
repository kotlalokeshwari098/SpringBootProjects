package com.javaspring.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private int prodId;


    public int getProdId() {
        return prodId;
    }

    public Product() {
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Product(int price, int prodId, String prodName) {
        this.price = price;
        this.prodId = prodId;
        this.prodName = prodName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                '}';
    }

    private String prodName;
    private int price;

}
