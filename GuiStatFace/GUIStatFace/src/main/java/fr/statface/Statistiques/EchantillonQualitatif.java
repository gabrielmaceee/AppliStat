package fr.statface.Statistiques;

import java.util.*;

public class EchantillonQualitatif extends EchantillonAbstrait<String> {

    private final ArrayList<String> donnees = new ArrayList<String>();
    protected ArrayList<String> donneesTriees;

    protected final HashSet<String> donneesUniques = new HashSet<String>();

    public EchantillonQualitatif(String nomVariable, String values) throws ExceptionDonneesEntree {
        super(nomVariable);
        this.donnees.addAll(Arrays.asList(values.split(";")));
        donneesUniques.addAll(this.donnees);
    }

    protected void instanciationTrie(){
        donneesTriees = new ArrayList<>(donnees);
        Collections.sort(donneesTriees);
    }


    public int getTaille(){
        return donnees.size();
    }

    @Override
    public double getMoyenne() {
        return 0;
    }

    @Override
    public double getVariance() {
        return 0;
    }

    @Override
    public double getEcarttype() {
        return 0;
    }

    @Override
    public String getMaximum() {
        return Collections.max(donnees);
    }

    @Override
    public String getMinimum() {
        return Collections.min(donnees);
    }

    @Override
    public int getFrequence(int d) {
        return 0;
    }


    @Override
    public double getMediane() {
        return 0;
    }

    @Override
    public double getSCT() {
        return 0;
    }

    @Override
    public double getQuartiles(int quartile) {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + donnees;
    }

    @Override
    public ArrayList<String> getDonnees() {
        return donnees;
    }

}
