package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Employee;
import org.eksamen.jobswap.domain.Zip;
import org.eksamen.jobswap.foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public boolean add(Employee employee) {
        return false;
    }

    public Employee read(int employeeID) throws Exception {
        String sql = "EXECUTE read_EmployeeID @employeeID = ?";
        ZipDAOImpl zipDAO = new ZipDAOImpl();

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

        ) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int newEmployeeID = rs.getInt("fldEmployeeID");
                    String firstName = rs.getString("fldFirstName");
                    String lastName = rs.getString("fldLastName");
                    String email = rs.getString("fldEmail");
                    String homeAddress = rs.getString("fldHomeAddress");
                    Zip homeAddressZip = zipDAO.read(rs.getInt("fldHomeAddressZip"));

                    return (new Employee(newEmployeeID, firstName, lastName, email, homeAddress, homeAddressZip));
                }
            }
            return null;
        }
    }

        public List<Employee> readAll() throws Exception {
        List<Employee> employees = new ArrayList<>();
        String sql = "EXECUTE readAll_Employee";
        ZipDAOImpl zipDAO = new ZipDAOImpl();

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                int employeeID = rs.getInt("fldEmployeeID");
                String firstName = rs.getString("fldFirstName");
                String lastName = rs.getString("fldLastName");
                String email = rs.getString("fldEmail");
                String homeAddress = rs.getString("fldHomeAddress");
                Zip homeAddressZip = zipDAO.read(rs.getInt("fldHomeAddressZip"));

                employees.add(new Employee(employeeID, firstName, lastName, email, homeAddress, homeAddressZip));
            }

            if (employees.isEmpty()) {
                System.out.println("Der blev ikke fundet nogle employees");
            }

            return employees;
        }
    }

    public void update(Employee employee) {

    }

    public boolean delete(int employeeID) {
        return false;
    }
}
