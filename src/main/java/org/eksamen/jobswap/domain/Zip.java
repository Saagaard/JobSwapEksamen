package org.eksamen.jobswap.domain;

import org.eksamen.jobswap.foundation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Zip {
    int zipCode;
    String cityName;

    public Zip(int zipCode) {
        this.zipCode = zipCode;


//        List<Employee> employees = new ArrayList<Employee>();
//        String sql = "SELECT fldCityName FROM tblZip WHERE ;
//
//        Connection conn = SqlConnection.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        //try-with-resources lukker automatisk ResultSet
//        try (ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                this.cityName = rs.getString("fldCityName");
//            }
//        }
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
}
