package org.eksamen.jobswap.domain;
import org.eksamen.jobswap.persistence.EmployeeDAOImpl;
import org.eksamen.jobswap.persistence.JobDAOImpl;
import org.eksamen.jobswap.persistence.WorkplaceDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class MatchSearch {

    public List<Match> createMatches(Criteria criteria) {
        return null;
    }

    public static void employeeList() throws Exception {

        JobDAOImpl jobDAO = new JobDAOImpl();
        List<Job> testJobList = jobDAO.readAll();
        for (Job job : testJobList) {
            System.out.println("Home address: " + job.employee.getHomeAddress());
            System.out.println("Work address: " + job.workplace.getWorkAddress());

        }

        List<Match> matchList = new ArrayList<>();

        //samle al data i en ny liste "matches" så vi kan regne med API metoden og videresende al data til match cards.
    }

    public static void main(String[] args) throws Exception {
        employeeList();

    }
}
