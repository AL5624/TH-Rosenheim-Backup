package de.thro.inf.pvs.bsp;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Address {
    private String street;
    private String postalcode;
    private String city;

    public Address(String street, String postalcode, String city) {
        this.street = street;
        this.postalcode = postalcode;
        this.city = city;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
