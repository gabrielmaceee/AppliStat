package fr.statface.Statistiques;

public class ExceptionTailleEchantillon extends Exception{
    public ExceptionTailleEchantillon(){
        super("Les échantillons doivent être de même taille");
    }
}
