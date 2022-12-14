package fr.statface.Statistiques;

import java.util.Random;
import java.util.ArrayList;

public class GenerateurEchantillion {
    private final ArrayList<Number> ls;
    private static final Random rdm = new Random();

    public  GenerateurEchantillion(){
        this(50,30);
    }

    /**
     * Construit un échantillon qui sert pour alimenter l'interface
     * @param size taille échantillion entier positif
     * @param limit  entier
     */
    public GenerateurEchantillion(int size, int limit) {
        if (size < 0) {
            throw new IllegalArgumentException("Pas de taille négative");
        }
        ls = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ls.add(rdm.nextDouble(limit+1));
        }
    }


    /**
     * @return les nombres de la liste séparée par virgule
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ls.forEach(n -> res.append( n.toString() ).append(";"));
        return res.toString();
    }

    public static void main(String[] args) {
        GenerateurEchantillion genE = new GenerateurEchantillion();
        System.out.println(genE.toString());
    }
}
