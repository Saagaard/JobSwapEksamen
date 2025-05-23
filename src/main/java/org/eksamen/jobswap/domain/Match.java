package org.eksamen.jobswap.domain;

public class Match {
    private final Job job1;
    private final int job1NewTransportTime;
    private final Job job2;
    private final int job2NewTransportTime;

    public Match(Job job1, int job1NewTransportTime, Job job2, int job2NewTransportTime) {
        this.job1 = job1;
        this.job1NewTransportTime = job1NewTransportTime;
        this.job2 = job2;
        this.job2NewTransportTime = job2NewTransportTime;
    }

    public Job getJob1() {
        return job1;
    }

    public int getJob1NewTransportTime() {
        return job1NewTransportTime;
    }

    public Job getJob2() {
        return job2;
    }

    public int getJob2NewTransportTime() {
        return job2NewTransportTime;
    }
}
