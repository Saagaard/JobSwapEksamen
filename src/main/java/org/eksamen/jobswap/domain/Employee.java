package org.eksamen.jobswap.domain;

public class Employee {
    int employeeID;
    String firstName;
    String lastName;
    String email;
    String homeAddress;
    Zip homeAddressZip;

    public Employee(int employeeID, String firstName, String lastName, String email, String homeAddress, Zip homeAddressZip) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homeAddress = homeAddress;
        this.homeAddressZip = homeAddressZip;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Zip getHomeAddressZip() {
        return homeAddressZip;
    }
}
