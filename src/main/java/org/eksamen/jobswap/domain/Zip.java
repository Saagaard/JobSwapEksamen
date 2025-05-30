package org.eksamen.jobswap.domain;

public class Zip {
    private final int zipCode;
    private final String cityName;

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
