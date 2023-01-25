package com.example.statistiques.Calcul;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * class générant des échantillons aléatoires
 */
public class GenerateurEchantillion {
    /**
     * list contenant l'échantillon
     */
    private final List<Number> ls;

    private static final Random rdm = new Random();

    /**
     * créé un échantillon de taille 50, et de valeur maximum 30
     */
    public  GenerateurEchantillion(){
        this(50,30);
    }

    /**
     * @param size
     * @param limit
     * créé un échantillon de taille size , et de valeur maximum limit
     */
    public GenerateurEchantillion(int size, int limit) {
        if (size < 0) {
            throw new IllegalArgumentException("Pas de taille négative");
        }
        ls = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            ls.add(rdm.nextDouble(limit+1));
        }
    }


    /**
     * @return l'echantillon sous forme d'une string
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ls.forEach(n -> res.append( n.toString() ).append(";"));
        return res.toString();
    }

    public static void main(String[] args) {
        GenerateurEchantillion genE = new GenerateurEchantillion();
        System.out.println(genE);
    }
}
