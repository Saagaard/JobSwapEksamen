package org.eksamen.jobswap.domain;

/**
 * Represents the criteria for a job swap used in SearchController
 */
public class Criteria {
    private final String jobTitle;
    private final int transportTime;
    private final float salaryDifference;
    private final int minimumSeniority;
    private final int maxSeniority;

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
