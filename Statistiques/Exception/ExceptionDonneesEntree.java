package Statistiques.Exception;

/**
 * cette exception est levée s'il y a un null
 */
public class ExceptionDonneesEntree extends Exception{
    public ExceptionDonneesEntree(){
        super("Les données doivent être des nombres non nuls");
    }
}
