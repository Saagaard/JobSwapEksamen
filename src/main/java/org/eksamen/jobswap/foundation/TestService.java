package org.eksamen.jobswap.foundation;
import org.eksamen.jobswap.businessServices.CalculateSeniority;
import org.eksamen.jobswap.domain.Job;
import org.eksamen.jobswap.persistence.JobDAOImpl;

public class TestService {

    // Rule 1: Lønafvigelse må ikke overstige 15%
    public boolean isLønAfvigelseAcceptabel(double currentSalary, double newSalary) { // Boolean True/False
        double difference = Math.abs(currentSalary - newSalary); // trækker nuværende Løn fra den gammle løn
        return (difference / currentSalary) <= 0.15; // retunere dif som ikke må overstige 15%
    }

    // Rule 3: Minimum 6 måneders anciennitet
    public boolean hasRequiredAnciennitet(Job job)
    {return CalculateSeniority.calculateSeniority(job) >= 6;
    }

    // Samlet validering
    public boolean isValidMatch(Job job, Job job2) {
        return isLønAfvigelseAcceptabel(job.getMonthlySalary(), job2.getMonthlySalary()) &&
                hasRequiredAnciennitet(job);
    }

    public static void main(String[] args) throws Exception {
        TestService testService = new TestService();
        JobDAOImpl jobDAO = new JobDAOImpl();
        testService.hasRequiredAnciennitet(jobDAO.read(1));
    }

}
