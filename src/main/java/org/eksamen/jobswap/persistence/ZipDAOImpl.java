package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Employee;
import org.eksamen.jobswap.domain.Zip;
import org.eksamen.jobswap.foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ZipDAOImpl implements ZipDAO {

    public boolean add(Zip zip) {
        return false;
    }

    public Zip read(int zipCode) throws SQLException {
        String sql = "EXECUTE read_ZipCode @zipCode = ?";

        //try-with-resources lukker automatisk ResultSet
        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, zipCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int newZipCode = rs.getInt("fldZipCode");
                    String cityName = rs.getString("fldCityName");

                    return (new Zip(newZipCode, cityName));
                }
            }
            return null;
        }
    }

    public List<Zip> readAll() {
        return null;
    }

    public void update(Zip zip) {

    }

    public boolean delete(int zipCode) {
        return false;
    }
}
