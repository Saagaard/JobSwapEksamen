package org.eksamen.jobswap.persistence;

import org.eksamen.jobswap.domain.Zip;

public interface ZipDAO {
    // CRUD support med 5 metoder.

    //public boolean add(Zip zip) throws Exception;// Tilføje eller skrive et postnummer eller Create i CRUD

    public Zip read(int zipCode) throws Exception; // Læse et enkelt postnummer eller Read i CRUD

    //public List<Zip> readAll(); // Læse alle postnummer eller Read i CRUD

    //public void update(Zip zip); // Opdaterer et postnummer eller Update i CRUD

    //public boolean delete(int zipCode); // Sletter et postnummer eller Delete i CRUD
}
