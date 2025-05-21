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
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(); // initialisér employeeDAOImpl objektet.
        List<Employee> testEmployeeList = employeeDAO.readAll(); // Lav en arrayliste, og kald readAll() metoden på objektet for at fylde listen med data

        JobDAOImpl jobDAO = new JobDAOImpl();
        List<Job> testJobList = jobDAO.readAll();

        WorkplaceDAOImpl workplaceDAO = new WorkplaceDAOImpl();
        List<Workplace> testWorkplaceList = workplaceDAO.readAll();

        List<Match> matchList = new ArrayList<>();

        //samle al data i en ny liste "matches" så vi kan regne med API metoden og videresende al data til match cards.
    }

    public static void main(String[] args) throws Exception {
        employeeList();

    }
}
