package org.eksamen.jobswap.domain;

import org.eksamen.jobswap.foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Zip {
    int zipCode;
    String cityName;

    public Zip(int zipCode) throws Exception {
        this.zipCode = zipCode;

        String sql = "EXECUTE read_CityName_Zip @zipCode = ?";

        try (
                Connection conn = SqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, this.zipCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    this.cityName = rs.getString("fldCityName");
                } else {
                    throw new Exception("Zip code not found");
                }
            }
        }
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
}
