package org.eksamen.jobswap.Persistence;

import org.eksamen.jobswap.Domain.Workplace;

import java.util.List;

public interface WorkplaceDAO {
    // CRUD support med 5 metoder.

    public boolean add(Workplace workplace) throws Exception;// Tilføje eller skrive et produkt eller Create i CRUD

    public Workplace read(int workplaceID) throws Exception; // Læse et enkelt produkt eller Read i CRUD

    public List<Workplace> readAll(); // Læse alle produkter eller Read i CRUD

    public void update(Workplace workplace); // Opdaterer et produkt eller Update i CRUD

    public boolean delete(int workplaceID); // Sletter et produkt eller Delete i CRUD
}
