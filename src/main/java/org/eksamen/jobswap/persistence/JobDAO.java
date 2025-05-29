package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Job;

import java.util.List;

public interface JobDAO {
    // CRUD support med 5 metoder.

    //public boolean add(Job job) throws Exception;// Tilføje eller skrive et job eller Create i CRUD

    //public Job read(int jobID) throws Exception; // Læse et enkelt job eller Read i CRUD

    public List<Job> readAll() throws Exception; // Læse alle jobs eller Read i CRUD

    //public void update(Job job); // Opdaterer et job eller Update i CRUD

    //public boolean delete(int jobID); // Sletter et job eller Delete i CRUD
}

