package com.dorset.coffeeshop.model.object;

import java.io.Serializable;

public class CoffeeAddress implements Serializable {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
