package org.eksamen.jobswap.domain;

public class Match {
    private final Job job1;
    private final TransportDetails job1OldTransportDetails;
    private final TransportDetails job1NewTransportDetails;
    private final float job1SalaryDifference;
    private final Job job2;
    private final TransportDetails job2OldTransportDetails;
    private final TransportDetails job2NewTransportDetails;
    private final float job2SalaryDifference;

    public Match(Job job1, TransportDetails job1OldTransportDetails, TransportDetails job1NewTransportDetails, float job1SalaryDifference, Job job2, TransportDetails job2OldTransportDetails, TransportDetails job2NewTransportDetails, float job2SalaryDifference) {
        this.job1 = job1;
        this.job1OldTransportDetails = job1OldTransportDetails;
        this.job1NewTransportDetails = job1NewTransportDetails;
        this.job1SalaryDifference = job1SalaryDifference;
        this.job2 = job2;
        this.job2OldTransportDetails = job2OldTransportDetails;
        this.job2NewTransportDetails = job2NewTransportDetails;
        this.job2SalaryDifference = job2SalaryDifference;
    }

    public Job getJob1() {
        return job1;
    }

    public TransportDetails getJob1OldTransportDetails() {
        return job1OldTransportDetails;
    }

    public TransportDetails getJob1NewTransportDetails() {
        return job1NewTransportDetails;
    }

    public float getJob1SalaryDifference() {
        return job1SalaryDifference;
    }

    public Job getJob2() {
        return job2;
    }

    public TransportDetails getJob2OldTransportDetails() {
        return job2OldTransportDetails;
    }

    public TransportDetails getJob2NewTransportDetails() {
        return job2NewTransportDetails;
    }

    public float getJob2SalaryDifference() {
        return job2SalaryDifference;
    }
}
