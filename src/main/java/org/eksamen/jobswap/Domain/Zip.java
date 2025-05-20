package org.eksamen.jobswap.Domain;

public class Zip {
    int zipCode;
    String cityName;

    public Zip(int zipCode) {
        this.zipCode = zipCode;
        //this.cityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
}
