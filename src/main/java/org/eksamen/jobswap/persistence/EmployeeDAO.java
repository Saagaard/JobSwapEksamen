package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Employee;

import java.util.List;

public interface EmployeeDAO {
    // CRUD support med 5 metoder.

    public boolean add(Employee employee) throws Exception;// Tilføje eller skrive et produkt eller Create i CRUD

    public Employee read(int employeeID) throws Exception; // Læse et enkelt produkt eller Read i CRUD

    public List<Employee> readAll() throws Exception; // Læse alle produkter eller Read i CRUD

    public void update(Employee employee); // Opdaterer et produkt eller Update i CRUD

    public boolean delete(int employeeID); // Sletter et produkt eller Delete i CRUD
}
