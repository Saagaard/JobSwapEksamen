package org.eksamen.jobswap.domain;
import org.eksamen.jobswap.persistence.EmployeeDAOImpl;
import org.eksamen.jobswap.persistence.JobDAOImpl;
import org.eksamen.jobswap.persistence.WorkplaceDAOImpl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class MatchSearch {

    public static List<Match> createMatches(Criteria criteria) throws Exception {
        JobDAOImpl jobDAO = new JobDAOImpl();
        List<Job> jobList = jobDAO.readAll();
        List<Match> matchList = new ArrayList<>();

        for (int job1Index = 0; job1Index < jobList.size(); job1Index++) {
            Job job1 = jobList.get(job1Index);
            System.out.println("ny job1 iteration");

            for (int i = 0; i < jobList.size(); i++) {
                System.out.println("ny job2 iteration");
                if (i == job1Index) { continue; } // Now we are correctly skipping matching the same job
                Job job2 = jobList.get(i);

                // Jobtitel
                if (!criteria.getJobTitle().isEmpty()) {
                    if (!criteria.getJobTitle().equals(job1.getJobTitle()) || !criteria.getJobTitle().equals(job2.getJobTitle())) {
                        System.out.println("Jobtitel matcher IKKE");
                        continue;
                    } else {
                        System.out.println("jobtitel matcher");
                    }
                } else {
                    System.out.println("Ingen jobtitel valgt");
                }


//                    if (!criteria.getTransportTime() > Distance.getTransportDetails(job1.employee.getHomeAddress(), job2.workplace.getWorkAddress())
//                            &&
//                        !criteria.getTransportTime() > Distance.getTransportDetails(job2.employee.getHomeAddress(), job1.workplace.getWorkAddress()))
//                    {
//
//                    }

                // Lønafvigelse
                System.out.println("salary difference: " +  abs(job1.getMonthlySalary() - job2.getMonthlySalary()));
                if (criteria.getSalaryDifference() > abs(job1.getMonthlySalary() - job2.getMonthlySalary())) {
                    System.out.println("Lønafvigelse matcher");
                } else {
                    System.out.println("Lønafvigelse matcher IKKE");
                    continue;
                }

                // Minimum og max anciennitet
                System.out.println("Job1 anciennitet: " + job1.calculateSeniority() + " Job2 anciennitet: " + job2.calculateSeniority());

                if (job1.calculateSeniority() > criteria.getMinimumSeniority() && job2.calculateSeniority() > criteria.getMinimumSeniority()
                    && job1.calculateSeniority() < criteria.getMaxSeniority() && job2.calculateSeniority() < criteria.getMaxSeniority()){

                    System.out.println("Minimum og max anciennitet matcher");
                } else {
                    System.out.println("Minimum og max anciennitet matcher IKKE");
                    continue;
                }

                System.out.println("Match fundet!!!!!!");
                matchList.add(new Match (job1, 20, job2, 20));

            }

        }
        return matchList;
    }

    public static void employeeList() throws Exception {

        JobDAOImpl jobDAO = new JobDAOImpl();
        List<Job> testJobList = jobDAO.readAll();


        //samle al data i en ny liste "matches" så vi kan regne med API metoden og videresende al data til match cards.
    }

    public static void main(String[] args) throws Exception {
        List<Match> matchList = createMatches(new Criteria("", 20, 5000, 12, 100));
//        System.out.println("Matches:");
//        for (Match match : matchList) {
//            System.out.println(match.job1.getEmployee().getFirstName() + " " + match.job1.getEmployee().getLastName());
//            System.out.println(match.job2.getEmployee().getFirstName() + " " + match.job2.getEmployee().getLastName());
//        }

    }
}
