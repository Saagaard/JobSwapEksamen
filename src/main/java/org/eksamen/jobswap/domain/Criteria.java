package org.eksamen.jobswap.domain;

public class Criteria {
    String jobTitle;
    int transportTime;
    float salaryDifference;
    int minimumSeniority;
    int maxSeniority;

    public Criteria(String jobTitle, int transportTime, float salaryDifference, int minimumSeniority, int maxSeniority) {
        this.jobTitle = jobTitle;
        this.transportTime = transportTime;
        this.salaryDifference = salaryDifference;
        this.minimumSeniority = minimumSeniority;
        this.maxSeniority = maxSeniority;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getTransportTime() {
        return transportTime;
    }

    public float getSalaryDifference() {
        return salaryDifference;
    }

    public int getMinimumSeniority() {
        return minimumSeniority;
    }

    public int getMaxSeniority() {
        return maxSeniority;
    }
}
