package com.redhat.demo;

import java.io.Serializable;

public class OrderResponse implements Serializable {
    private String delivery;
    private Double total;

    @Override
    public String toString() {
        return "OrderResponse [delivery=" + delivery + ", total=" + total + "]";
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
