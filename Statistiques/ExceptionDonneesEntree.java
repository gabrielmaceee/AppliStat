package com.example.statistiques;

/**
 * cette exception est levée si il y a un null
 */
public class ExceptionDonneesEntree extends Exception{
    public ExceptionDonneesEntree(){
        super("Les données doivent être des nombres non nuls");
    }
}
