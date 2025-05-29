package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Workplace;

import java.util.List;

public interface WorkplaceDAO {
    // CRUD support med 5 metoder.

    //public boolean add(Workplace workplace) throws Exception;// Tilføje eller skrive et arbejdsplads eller Create i CRUD

    public Workplace read(int workplaceID) throws Exception; // Læse et enkelt arbejdsplads eller Read i CRUD

    public List<Workplace> readAll() throws Exception; // Læse alle arbejdspladser eller Read i CRUD

    //public void update(Workplace workplace); // Opdaterer et arbejdsplads eller Update i CRUD

    //public boolean delete(int workplaceID); // Sletter et arbejdsplads eller Delete i CRUD
}
