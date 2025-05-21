package org.eksamen.jobswap.foundation;

import java.sql.*;

public class SqlConnection {
    private static final String URL = CredentialsManager.getDbUrl();
    private static final String USER = CredentialsManager.getDbUser();
    private static final String PASS = CredentialsManager.getDbPass();

    private static Connection connection;

    private SqlConnection() {
        // Forhindrer instantiation fra andre klasser
    }

//    public static Connection getConnection() throws Exception {
//        if (connection == null || connection.isClosed()) {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(URL, USER, PASS);
//        }
//        return connection;
//    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
