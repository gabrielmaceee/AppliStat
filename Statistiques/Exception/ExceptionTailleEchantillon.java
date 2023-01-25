package com.example.statistiques.Exception;

/**
 * cette exception est levée si les échantillons ne sont pas de même taille
 */
public class ExceptionTailleEchantillon extends Exception{
    public ExceptionTailleEchantillon(){
        super("Les échantillons doivent être de même taille");
    }
}
