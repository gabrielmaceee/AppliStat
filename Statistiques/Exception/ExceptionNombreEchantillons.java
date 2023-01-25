package com.example.statistiques.Exception;

/**
 * cette exception est levée si le nombre d'échantillons n'est pas respecté
 */
public class ExceptionNombreEchantillons extends Exception{
    public ExceptionNombreEchantillons() {
        super("Minimum 2 échantillons pour une anova");
    }
}
