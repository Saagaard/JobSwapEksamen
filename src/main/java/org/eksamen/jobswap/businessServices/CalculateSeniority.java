package org.eksamen.jobswap.businessServices;

import org.eksamen.jobswap.domain.Job;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A service class responsible for calculating the seniority of a Job
 */
public class CalculateSeniority {

    /**
     * @param job {@link Job}
     * @return Returns the calculated seniority in months
     */
    public static int calculateSeniority(Job job) {
        LocalDate now = LocalDate.now();
        return Math.toIntExact(ChronoUnit.MONTHS.between(job.getEmploymentDate(), now));
    }
}
