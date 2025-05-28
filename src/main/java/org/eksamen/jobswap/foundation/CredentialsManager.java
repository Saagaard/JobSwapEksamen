package org.eksamen.jobswap.foundation;

import java.io.InputStream;
import java.util.Properties;

/**
 * Is responsible for handling the local-stored credentials.properties file
 * The file contains the API key and database password and similar secrets
 */
public class CredentialsManager {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = CredentialsManager.class.getClassLoader().getResourceAsStream("credentials.properties")) {
            if (input == null) {
                throw new RuntimeException("FATAL: Could not find credentials.properties");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("FATAL: Failed to load credentials: " + e.getMessage(), e);
        }
    }

    public static String getDbUrl() {
        return props.getProperty("db.url");
    }

    public static String getDbUser() {
        return props.getProperty("db.user");
    }

    public static String getDbPass() {
        return props.getProperty("db.pass");
    }

    public static String getMapsApiKey() {
        return props.getProperty("maps.api");
    }
}