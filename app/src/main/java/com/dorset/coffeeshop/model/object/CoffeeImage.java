package com.dorset.coffeeshop.model.object;

import java.io.Serializable;

public class CoffeeImage implements Serializable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
