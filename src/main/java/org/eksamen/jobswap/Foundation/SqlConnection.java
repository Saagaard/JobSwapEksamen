package org.eksamen.jobswap.Foundation;
import java.sql.*;

public class SqlConnection {
    private static final String URL = "jdbc:sqlserver://162.19.246.106:1433;databaseName=jobbytte_test;encrypt=false;";
    private static final String USER = "eksamen";
    private static final String PASS = "7Gn334>EKWo£";

    private static Connection connection;

    private SqlConnection() {
        // Forhindrer instantiation fra andre klasser
    }

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }
}
