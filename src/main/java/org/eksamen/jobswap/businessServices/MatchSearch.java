package org.eksamen.jobswap.businessServices;
import org.eksamen.jobswap.domain.Criteria;
import org.eksamen.jobswap.domain.Job;
import org.eksamen.jobswap.domain.Match;
import org.eksamen.jobswap.domain.TransportDetails;
import org.eksamen.jobswap.persistence.JobDAO;
import org.eksamen.jobswap.persistence.JobDAOImpl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static org.eksamen.jobswap.businessServices.CalculateSalaryDifference.calculateSalaryDifference;

/**
 * Service class responsible for finding potential jobswap matches.
 */
public class MatchSearch {

    private final JobDAO jobDAO; // Interface
    private final CalculateTransport calculateTransport; // Interface

    public MatchSearch(JobDAO jobDAO, CalculateTransport calculateTransport) { // Her bliver instantiations smidt ind som parametre, så vi kun er afhængige af interfaces i denne klasse
        this.jobDAO = jobDAO;
        this.calculateTransport = calculateTransport;
    }

    /**
     *
     * @param criteria A Criteria-object created by SearchController
     * @return A {@link List} of {@link Match} objects tht match the given {@link Criteria} object
     * @throws Exception If an error occurs during data retrieval
     */
    public List<Match> createMatches(Criteria criteria) throws Exception {
        List<Job> jobList = jobDAO.readAll();

        // Collects all potential matches in an ArrayList
        List<Match> matchList = new ArrayList<>();

        // Job 1 iteration START
        for (int job1Index = 0; job1Index < jobList.size(); job1Index++) {
            Job job1 = jobList.get(job1Index);

            // Job title check - Job1
            if (!criteria.getJobTitle().isEmpty()) {
                if (!criteria.getJobTitle().equals(job1.getJobTitle())) {
                    continue;
                }
            }

            // Seniority check - Job1
            if (job1.calculateSeniority() < criteria.getMinimumSeniority() || job1.calculateSeniority() > criteria.getMaxSeniority()) {
                continue;
            }

            // Job 2 iteration START
            for (int job2Index = job1Index + 1; job2Index < jobList.size(); job2Index++) {

                Job job2 = jobList.get(job2Index);

                // Check if both jobs have the same workplace
                if (job1.getWorkplace().getWorkplaceID() == job2.getWorkplace().getWorkplaceID()){
                    continue;
                }

                // Jobtitle check - Job 2
                if (!criteria.getJobTitle().isEmpty()) {
                    if (!criteria.getJobTitle().equals(job2.getJobTitle())) {
                        continue;
                    }
                }

                // Seniority check - Job2
                if (job2.calculateSeniority() < criteria.getMinimumSeniority() || job2.calculateSeniority() > criteria.getMaxSeniority()) {
                    continue;
                }

                // Salary Difference check
                float salaryDifference1 = (job2.getMonthlySalary() - job1.getMonthlySalary());
                float salaryDifference2 = (job1.getMonthlySalary() - job2.getMonthlySalary());
                if (criteria.getSalaryDifference() != 0) {
                    if (calculateSalaryDifference(job1, job2) > criteria.getSalaryDifference()) {
                        continue;
                    }
                }

                // Transport time to old workplace START
                TransportDetails oldTransportDetails1 = calculateTransport.calculateTransportDetails(
                        job1.getEmployee().getHomeAddress(),
                        job1.getWorkplace().getWorkAddress(),
                        job1.getEmployee().getHomeAddressZip().getZipCode(),
                        job1.getWorkplace().getWorkAddressZip().getZipCode()
                );

                TransportDetails oldTransportDetails2 = calculateTransport.calculateTransportDetails(
                        job2.getEmployee().getHomeAddress(),
                        job2.getWorkplace().getWorkAddress(),
                        job2.getEmployee().getHomeAddressZip().getZipCode(),
                        job2.getWorkplace().getWorkAddressZip().getZipCode()
                );
                // Transport time to old workplace STOP

                // Transport time to new workplace START
                TransportDetails newTransportDetails1 = calculateTransport.calculateTransportDetails(
                        job1.getEmployee().getHomeAddress(),
                        job2.getWorkplace().getWorkAddress(),
                        job1.getEmployee().getHomeAddressZip().getZipCode(),
                        job2.getWorkplace().getWorkAddressZip().getZipCode()
                );

                TransportDetails newTransportDetails2 = calculateTransport.calculateTransportDetails(
                        job2.getEmployee().getHomeAddress(),
                        job1.getWorkplace().getWorkAddress(),
                        job2.getEmployee().getHomeAddressZip().getZipCode(),
                        job1.getWorkplace().getWorkAddressZip().getZipCode()
                );
                // Transport time to new workplace STOP


                // Transport time check
                if (newTransportDetails1.getTravelTime() > criteria.getTransportTime() || newTransportDetails2.getTravelTime() > criteria.getTransportTime()) {
                    continue;
                }

                //System.out.println("Match fundet!!!!!!");
                matchList.add(new Match (job1, oldTransportDetails1, newTransportDetails1, salaryDifference1, job2, oldTransportDetails2, newTransportDetails2, salaryDifference2));

            } // Job 2 iteration STOP

        } // Job 1 iteration STOP
        return matchList;
    }

}
