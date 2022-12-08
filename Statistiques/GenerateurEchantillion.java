package fr.statface.Statistiques;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GenerateurEchantillion {
    private final List<Number> ls;
    private static final Random rdm = new Random();

    public  GenerateurEchantillion(){
        this(50,30);
    }

    public GenerateurEchantillion(int size, int limit) {
        ls = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            ls.add(rdm.nextDouble(limit+1));
        }
    }



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
