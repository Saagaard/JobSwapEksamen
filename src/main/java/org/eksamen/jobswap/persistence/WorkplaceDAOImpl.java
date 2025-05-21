package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Employee;
import org.eksamen.jobswap.domain.Workplace;
import org.eksamen.jobswap.domain.Zip;
import org.eksamen.jobswap.foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkplaceDAOImpl implements WorkplaceDAO {

    public boolean add(Workplace workplace) {
        return false;
    }

    public Workplace read(int workplaceID) throws Exception {
        String sql = "SELECT * FROM tblWorkplace WHERE fldWorkplaceID = ?";

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

        ) {
            pstmt.setInt(1, workplaceID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int newWorkplaceID = rs.getInt("fldWorkPlaceID");
                    String workAddress = rs.getString("fldWorkAddress");
                    Zip workAddressZip = new Zip(rs.getInt("fldWorkAddressZip"));

                    return (new Workplace(newWorkplaceID, workAddress, workAddressZip));
                }
            }
            return null;
        }
    }

    public List<Workplace> readAll() throws Exception {
        List<Workplace> workplaces = new ArrayList<>();
        String sql = "SELECT * FROM dbo.tblWorkplace";

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                int workplaceID = rs.getInt("fldWorkPlaceID");
                String workAddress = rs.getString("fldWorkAddress");
                Zip workAddressZip = new Zip(rs.getInt("fldWorkAddressZip"));
                workplaces.add(new Workplace(workplaceID, workAddress, workAddressZip));
            }

            if (workplaces.isEmpty()) {
                System.out.println("Der blev ikke fundet nogle workplaces");
            }

            return workplaces;
        }
    }

    public void update(Workplace workplace) {

    }

    public boolean delete(int workplaceID) {
        return false;
    }
}
