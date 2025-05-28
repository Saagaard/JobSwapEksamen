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

        //samle al data i en ny liste "matches" så vi kan regne med API metoden og videresende al data til match cards.
        List<Match> matchList = new ArrayList<>();

        for (int job1Index = 0; job1Index < jobList.size(); job1Index++) {
            Job job1 = jobList.get(job1Index);

            for (int job2Index = job1Index + 1; job2Index < jobList.size(); job2Index++) {

                Job job2 = jobList.get(job2Index);
                if (job1.getWorkplace().getWorkplaceID() == job2.getWorkplace().getWorkplaceID()){
                    continue;
                }

                //System.out.println(job1.getEmployee().getFirstName() + " og " + job2.getEmployee().getFirstName());

                // Jobtitel
                if (!criteria.getJobTitle().isEmpty()) {
                    if (!criteria.getJobTitle().equals(job1.getJobTitle()) || !criteria.getJobTitle().equals(job2.getJobTitle())) {
                        //System.out.println("Jobtitel matcher IKKE");
                        continue;
                    } else {
                        //System.out.println("jobtitel matcher");
                    }
                } else {
                    //System.out.println("Ingen jobtitel valgt");
                }

                // Lønafvigelse
                float salaryDifference1 = (job2.getMonthlySalary() - job1.getMonthlySalary());
                float salaryDifference2 = (job1.getMonthlySalary() - job2.getMonthlySalary());
                if (criteria.getSalaryDifference() != 0) {
                    //System.out.println("Lønforskel i kr: " +  abs(job1.getMonthlySalary() - job2.getMonthlySalary()));
                    if (calculateSalaryDifference(job1, job2) < criteria.getSalaryDifference()) {
                        //System.out.println("Lønafvigelse matcher");
                    } else {
                        //System.out.println("Lønafvigelse matcher IKKE");
                        continue;
                    }
                } else {
                    //System.out.println("Ingen lønafvigelse valgt");
                }


                // Minimum og max anciennitet
                //System.out.println("Job1 anciennitet: " + job1.calculateSeniority() + " Job2 anciennitet: " + job2.calculateSeniority());

                if (job1.calculateSeniority() >= criteria.getMinimumSeniority() && job2.calculateSeniority() >= criteria.getMinimumSeniority()
                        && job1.calculateSeniority() <= criteria.getMaxSeniority() && job2.calculateSeniority() <= criteria.getMaxSeniority()){

                    //System.out.println("Minimum og max anciennitet matcher");
                } else {
                    //System.out.println("Minimum og max anciennitet matcher IKKE");
                    continue;
                }

                // Transporttid til ny arbejdsplads START
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
                // Transporttid til ny arbejdsplads SLUT

                // Transporttid til ny arbejdsplads START
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
                // Transporttid til ny arbejdsplads SLUT


                // Transporttid check
                //System.out.println("Transporttid 1: " + newTransportDetails1.getTravelTime());
                //System.out.println("Transporttid 2: " + newTransportDetails2.getTravelTime());
                if (newTransportDetails1.getTravelTime() <= criteria.getTransportTime() && newTransportDetails2.getTravelTime() <= criteria.getTransportTime())
                {
                    //System.out.println("Transporttid matcher");
                } else {
                    //System.out.println("Transporttid matcher IKKE");
                    continue;
                }

                //System.out.println("Match fundet!!!!!!");
                matchList.add(new Match (job1, oldTransportDetails1, newTransportDetails1, salaryDifference1, job2, oldTransportDetails2, newTransportDetails2, salaryDifference2));

            }

        }
        //System.out.println("Matches:");
        for (Match match : matchList) {
            //System.out.println(match.getJob1().getEmployee().getFirstName() + " " + match.getJob1().getEmployee().getLastName());
            //System.out.println(match.getJob2().getEmployee().getFirstName() + " " + match.getJob2().getEmployee().getLastName());
            //System.out.println("-----------------");
        }
        return matchList;
    }

}
