package org.eksamen.jobswap.businessServices;

import org.eksamen.jobswap.domain.Job;

import static java.lang.Math.abs;

public class CalculateSalaryDifference {

    public static float calculateSalaryDifference(Job job1, Job job2) {
        float salaryDifference = abs(job1.getMonthlySalary() - job2.getMonthlySalary());
        float percentageIncrease = (salaryDifference / job1.getMonthlySalary()) * 100;
        System.out.println("Procent stigning løn %: " + percentageIncrease);
        return percentageIncrease;
    }
}
