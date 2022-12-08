package fr.statface.Statistiques;

public class ExceptionDonneesEntree extends Exception{
    public ExceptionDonneesEntree(){
        super("Les données doivent être des nombres non nuls");
    }
}
