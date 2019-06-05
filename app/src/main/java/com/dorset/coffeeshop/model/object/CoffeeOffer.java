package com.dorset.coffeeshop.model.object;

import java.io.Serializable;

public class CoffeeOffer implements Serializable {

    private String name;
    private String price;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
