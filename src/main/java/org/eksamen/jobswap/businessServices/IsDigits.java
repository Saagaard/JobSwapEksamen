package org.eksamen.jobswap.businessServices;

/**
 * A service class responsible for checking if a string only has digits
 */
public class IsDigits {

    /**
     *
     * @param s Any given String
     * @return boolean - true if the String is only digits
     */
    public static boolean isDigits(String s) {

        return s.matches("[0-9]+");
    }
}
