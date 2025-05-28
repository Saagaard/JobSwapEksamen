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

        // Collect all data in a new ArrayList called "matches" so we can refer to them with calculations and the Google routes API call
        List<Match> matchList = new ArrayList<>();

        // Primary loop for running through all Job objects
        for (int job1Index = 0; job1Index < jobList.size(); job1Index++) {
            Job job1 = jobList.get(job1Index);

            // Secondary loop for cross-referencing current Job object versus all other Job objects
            for (int job2Index = job1Index + 1; job2Index < jobList.size(); job2Index++) {

                // Grabs the second Job object and verifies that the two Jobs do not have the same workplace
                Job job2 = jobList.get(job2Index);
                if (job1.getWorkplace().getWorkplaceID() == job2.getWorkplace().getWorkplaceID()) {
                    continue;
                }

                // Verifies if Job title exists otherwise ignore.
                // If Job title exists it verifies that they match between the two Job objects
                if (!criteria.getJobTitle().isEmpty()) {
                    if (!criteria.getJobTitle().equals(job1.getJobTitle()) || !criteria.getJobTitle().equals(job2.getJobTitle())) {
                        continue;
                    }
                }

                // Checks salary difference between the two Job objects
                float salaryDifference1 = (job2.getMonthlySalary() - job1.getMonthlySalary());
                float salaryDifference2 = (job1.getMonthlySalary() - job2.getMonthlySalary());
                if (criteria.getSalaryDifference() != 0) {
                    if (calculateSalaryDifference(job1, job2) < criteria.getSalaryDifference()) {
                    } else {
                        continue;
                    }
                }


                // Minimum and maximum seniority setup
                // Fails the match if the seniority is not within min or max
                if (job1.calculateSeniority() >= criteria.getMinimumSeniority() && job2.calculateSeniority() >= criteria.getMinimumSeniority()
                        && job1.calculateSeniority() <= criteria.getMaxSeniority() && job2.calculateSeniority() <= criteria.getMaxSeniority()){
                } else {
                    continue;
                }

                // Transport time to new workplace for Job A START
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
                // Transport time to new workplace for Job A END

                // Transport time to new workplace for Job B START
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
                // Transport time to new workplace for job B END


                // Transport time check
                // If transport time is not within the criteria for both Jobs it fails the check
                if (newTransportDetails1.getTravelTime() <= criteria.getTransportTime() && newTransportDetails2.getTravelTime() <= criteria.getTransportTime())
                {
                } else {
                    continue;
                }
                // adds the found match to the matchList ArrayList
                matchList.add(new Match (job1, oldTransportDetails1, newTransportDetails1, salaryDifference1, job2, oldTransportDetails2, newTransportDetails2, salaryDifference2));
            }

        }
        return matchList;
    }

}
