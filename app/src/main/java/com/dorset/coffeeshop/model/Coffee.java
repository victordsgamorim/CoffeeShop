package com.dorset.coffeeshop.model;


import com.dorset.coffeeshop.model.object.CoffeeAddress;
import com.dorset.coffeeshop.model.object.CoffeeImage;
import com.dorset.coffeeshop.model.object.CoffeeOffer;
import com.dorset.coffeeshop.model.object.Contact;
import com.dorset.coffeeshop.model.object.OpenHour;
import com.dorset.coffeeshop.model.object.SocialMedia;

import java.io.Serializable;
import java.util.List;

public class Coffee implements Serializable {

    private String name;
    private List<CoffeeAddress> addresses;
    private String information;
    private List<CoffeeOffer> menu;
    private List<OpenHour> openHour;
    private List<CoffeeImage> image;
    private List<SocialMedia> socialMedia;
    private double rate;
    private List<Contact> contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CoffeeAddress> getAddress() {
        return addresses;
    }

    public void setAddress(List<CoffeeAddress> address) {
        this.addresses = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<CoffeeOffer> getMenu() {
        return menu;
    }

    public void setMenu(List<CoffeeOffer> menu) {
        this.menu = menu;
    }

    public List<OpenHour> getOpenHour() {
        return openHour;
    }

    public void setOpenHour(List<OpenHour> openHour) {
        this.openHour = openHour;
    }

    public List<CoffeeImage> getImage() {
        return image;
    }

    public void setImage(List<CoffeeImage> image) {
        this.image = image;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
}
