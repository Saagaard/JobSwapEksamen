package org.eksamen.jobswap.domain;

public class Zip {
    int zipCode;
    String cityName;

    public Zip(int zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
}
