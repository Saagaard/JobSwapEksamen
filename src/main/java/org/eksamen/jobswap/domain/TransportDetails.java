package org.eksamen.jobswap.domain;

public class TransportDetails {

    private final int travelTime;
    private final int travelDistance;

    public TransportDetails(int travelTime, int travelDistance) {
        this.travelTime = travelTime;
        this.travelDistance = travelDistance;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getTravelDistance() {
        return travelDistance;
    }

}
