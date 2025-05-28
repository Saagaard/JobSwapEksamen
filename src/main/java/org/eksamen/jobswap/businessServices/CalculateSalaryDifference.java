package org.eksamen.jobswap.businessServices;

import org.eksamen.jobswap.domain.Job;

import static java.lang.Math.abs;

/**
 * A service class responsible for calculating the absolute difference between 2 job's salaries
 */
public class CalculateSalaryDifference {

    /**
     * @param job1 {@link Job}
     * @param job2 {@link Job}
     * @return Returns the absolute salary difference between 2 jobs
     */
    public static float calculateSalaryDifference(Job job1, Job job2) {
        float salaryDifference = abs(job1.getMonthlySalary() - job2.getMonthlySalary());
        float percentageIncrease = (salaryDifference / job1.getMonthlySalary()) * 100;
        //System.out.println("Procent stigning løn %: " + percentageIncrease);
        return percentageIncrease;
    }
}
