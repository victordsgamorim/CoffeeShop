package com.dorset.coffeeshop.model.object;

import java.io.Serializable;

public class Contact implements Serializable {

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
