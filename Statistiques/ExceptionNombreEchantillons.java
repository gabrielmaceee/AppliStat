package com.example.statistiques;

public class ExceptionNombreEchantillons extends Exception{
    public ExceptionNombreEchantillons() {
        super("Minimum 2 √©chantillons pour une anova");
    }
}
