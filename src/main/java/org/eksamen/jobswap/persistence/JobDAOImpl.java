package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Job;
import org.eksamen.jobswap.foundation.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO {

    public boolean add(Job job) {
        return false;
    }

    public Job read(int jobID) {
        return null;
    }

    public List<Job> readAll() throws Exception {
        List<Job> jobs = new ArrayList<>();
        String sql = "EXECUTE readAll_Job";
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        WorkplaceDAOImpl workplaceDAO = new WorkplaceDAOImpl();

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                int jobID = rs.getInt("fldJobID");
                int employeeID = rs.getInt("fldEmployeeID");
                int workplaceID = rs.getInt("fldWorkPlaceID");
                String jobTitle = rs.getString("fldJobTitle");
                LocalDate employmentDate = rs.getDate("fldEmploymentDate").toLocalDate();
                float monthlySalary = rs.getFloat("fldMonthlySalary");

                jobs.add(new Job(jobID, employeeDAO.read(employeeID), workplaceDAO.read(workplaceID), jobTitle, employmentDate, monthlySalary));
            }

            if (jobs.isEmpty()) {
                System.out.println("Der blev ikke fundet nogle jobs");
            }

            return jobs;
        }
    }

    public void update(Job job) {

    }

    public boolean delete(int jobID) {
        return false;
    }
}
