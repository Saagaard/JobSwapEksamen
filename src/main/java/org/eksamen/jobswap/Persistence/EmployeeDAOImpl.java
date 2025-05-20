package org.eksamen.jobswap.Persistence;

import org.eksamen.jobswap.Domain.Employee;
import org.eksamen.jobswap.Domain.Zip;
import org.eksamen.jobswap.Foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public boolean add(Employee employee) {
        return false;
    }

    public Employee read(int employeeID) {
        return null;
    }

    public List<Employee> readAll() throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM dbo.tblEmployee";

        Connection conn = SqlConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //try-with-resources lukker automatisk ResultSet
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int employeeID = rs.getInt("fldEmployeeID");
                String firstName = rs.getString("fldFirstName");
                String lastName = rs.getString("fldLastName");
                String email = rs.getString("fldEmail");
                String homeAddress = rs.getString("fldHomeAddress");
                Zip homeAddressZip = new Zip(rs.getInt("fldHomeAddressZip"));
                Employee test = new Employee(employeeID, firstName, lastName, email, homeAddress, homeAddressZip);
                System.out.println(test.getEmployeeID());
                System.out.println(test.getFirstName());
                System.out.println(test.getLastName());

                //employees.add(new Employee(employeeID, firstName, lastName, email, homeAddress, homeAddressZip));
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
