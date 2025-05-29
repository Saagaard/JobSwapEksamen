package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Employee;

public interface EmployeeDAO {
    // CRUD support med 5 metoder.

    //public boolean add(Employee employee) throws Exception;// Tilføje eller skrive et employee eller Create i CRUD

    public Employee read(int employeeID) throws Exception; // Læse en enkelt employee eller Read i CRUD

    //public List<Employee> readAll() throws Exception; // Læse alle employees eller Read i CRUD

    //public void update(Employee employee); // Opdaterer en employee eller Update i CRUD

    //public boolean delete(int employeeID); // Sletter en employee eller Delete i CRUD
}
