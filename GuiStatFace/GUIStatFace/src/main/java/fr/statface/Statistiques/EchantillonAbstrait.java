package fr.statface.Statistiques;

import java.util.ArrayList;
import java.util.List;

public abstract class EchantillonAbstrait<T extends Comparable<T>> {


    private final String nomVariable;

    /**
     * @param nomVariable
     */
    public EchantillonAbstrait(String nomVariable) throws ExceptionDonneesEntree {
        if (nomVariable == null || nomVariable.length() == 0)
            throw new ExceptionDonneesEntree();
        this.nomVariable = nomVariable;
    }


    protected abstract void instanciationTrie();

    public abstract int getTaille();

    public abstract double getMoyenne();

    public abstract double getVariance();
    public abstract double getEcarttype();


    public abstract T getMaximum();

    public abstract T getMinimum();



    public abstract int getFrequence(int d);


    public abstract double getMediane();
    public abstract double getSCT();
    public abstract double getQuartiles(int quartile);

    public String toString() {
        return nomVariable + ":";
    }


    public abstract ArrayList<T> getDonnees();

    public String getNomVariable() {
        return nomVariable;
    }
}
