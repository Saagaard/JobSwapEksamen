package org.eksamen.jobswap.Domain;

import java.time.LocalDate;

public class Job {
    int jobID;
    Employee employee;
    Workplace workplace;
    String jobTitle;
    LocalDate employmentDate;
    float monthlySalary;


    public Job(int jobID, Employee employee, Workplace workplace, String jobTitle, LocalDate employmentDate, float monthlySalary) {
        this.jobID = jobID;
        this.employee = employee;
        this.workplace = workplace;
        this.jobTitle = jobTitle;
        this.employmentDate = employmentDate;
        this.monthlySalary = monthlySalary;
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
