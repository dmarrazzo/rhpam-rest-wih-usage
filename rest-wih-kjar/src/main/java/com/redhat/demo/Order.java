package com.redhat.demo;

import java.io.Serializable;

public class Order implements Serializable {
    private String item;
    private Long quantity;
    private Double price;

    @Override
    public String toString() {
        return "Order [item=" + item + ", price=" + price + ", quantity=" + quantity + "]";
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
