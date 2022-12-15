package com.example.statistique;

public class ExceptionNombreEchantillons extends Exception{
    public ExceptionNombreEchantillons() {
        super("Minimum 2 échantillons pour une anova");
    }
}
