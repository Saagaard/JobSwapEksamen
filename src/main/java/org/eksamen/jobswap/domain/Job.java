package org.eksamen.jobswap.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Job {
    private final int jobID;
    private final Employee employee;
    private final Workplace workplace;
    private final String jobTitle;
    private final LocalDate employmentDate;
    private final float monthlySalary;


    public Job(int jobID, Employee employee, Workplace workplace, String jobTitle, LocalDate employmentDate, float monthlySalary) {
        this.jobID = jobID;
        this.employee = employee;
        this.workplace = workplace;
        this.jobTitle = jobTitle;
        this.employmentDate = employmentDate;
        this.monthlySalary = monthlySalary;
    }

    public int calculateSeniority() {
        LocalDate now = LocalDate.now();
        return Math.toIntExact(ChronoUnit.MONTHS.between(this.getEmploymentDate(), now));
    }

    public int getJobID() {
        return jobID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public float getMonthlySalary() {
        return monthlySalary;
    }
}
