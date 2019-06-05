package com.dorset.coffeeshop.model.object;

import java.io.Serializable;

public class OpenHour implements Serializable {

    private String[] week = new String[3];
    private String[] hour = new String[3];

    public String[] getWeek() {
        return week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }

    public String[] getHour() {
        return hour;
    }

    public void setHour(String[] hour) {
        this.hour = hour;
    }
}
