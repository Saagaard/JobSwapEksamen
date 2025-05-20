package org.eksamen.jobswap.Persistence;

import org.eksamen.jobswap.Domain.Job;

import java.util.List;

public interface JobDAO {
    // CRUD support med 5 metoder.

    public boolean add(Job job) throws Exception;// Tilføje eller skrive et produkt eller Create i CRUD

    public Job read(int jobID) throws Exception; // Læse et enkelt produkt eller Read i CRUD

    public List<Job> readAll(); // Læse alle produkter eller Read i CRUD

    public void update(Job job); // Opdaterer et produkt eller Update i CRUD

    public boolean delete(int jobID); // Sletter et produkt eller Delete i CRUD
}

