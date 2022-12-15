package com.example.statistique;

public class ExceptionTailleEchantillon extends Exception{
    public ExceptionTailleEchantillon(){
        super("Les échantillons doivent être de même taille");
    }
}
