package Statistiques.Calcul;

import Statistiques.Exception.ExceptionDonneesEntree;

import java.util.*;


/**
 * class permettant de créer et manipuler un echantillon
 */
public class Echantillon {

    /**
     * liste des donnees de l'echantillon
     */
    private List<Double> donnees;
    /**
     * taille de l'échantillon
     */
    private int taille;
    /**
     * donnees triées dans l'ordre croissant
     */
     private List<Double> donneesTriees;

    public List<Double> getDonnees() {
        return donnees;
    }

    /**
     * @param s : les donnees sous forme d'une string
     * @throws ExceptionDonneesEntree : n'accepte pas une string null ou de taille 0
     */
    public Echantillon(String s) throws ExceptionDonneesEntree {

        if (s == null || s.length() == 0) {throw new ExceptionDonneesEntree();}

                String[] sprim = s.split(";");
                donnees = new ArrayList<>();
                for (int i = 0; i < sprim.length; i++) {
                    //if((sprim[i] instanceof String) == false) throw new ExceptionDonneesEntree();
                    try {
                       double c = Double.valueOf(sprim[i]);
                        donnees.add(c);
                    }catch (Exception e) {
                        //System.out.println(e.getMessage());
                        throw new ExceptionDonneesEntree();
                    }
                }
                instanciationTrie(donnees);
    }


    /**
     * @param TabDonnees : tableau de double contenant les donnees
     */
    public Echantillon(double[] TabDonnees) {
        if(TabDonnees.length ==0) throw new IllegalArgumentException("le tableau est vide");
        donnees = new ArrayList<>();
        for(int i=0;i<TabDonnees.length;i++)this.donnees.add(TabDonnees[i]);
        instanciationTrie(donnees);
    }

    /**
     * @param tab  : instancie la lsit triee
     */
    void instanciationTrie(List<Double> tab){
        donneesTriees = new ArrayList<>();
        taille = tab.size();
        donneesTriees.addAll(donnees);
        Collections.sort(donneesTriees);
    }

    /**
     * @return taille
     */
    public int getTaille(){
        return taille;
    }

    /** calcul la moyenne
     * @return la moyenne
     */
    public double getMoyenne(){
        double moyenne = 0;
        for(int i = 0; i<taille; i ++) {
            moyenne += donnees.get(i);
        }
        return moyenne/taille;
    }

    /** calcul la variance de l'échantillon
     * @return la variance
     */
    public double getVariance(){
        double var = 0;
        for(int i = 0; i<taille; i ++) {
            var += Math.pow((donnees.get(i)- getMoyenne()),2);
        }
        return var/(taille-1);
    }

    /** calcul l'ecart-type
     * @return l'ecart type
     */
    public double getEcarttype(){
        return Math.sqrt(getVariance());
    }

    /**
     * @return le maximum de l'echantillon
     */
    public double getMaximum(){
        return donneesTriees.get(taille-1);
    };

    /**
     * @return le minimum de l'echantillon
     */
    public double getMinimum() {
        return donneesTriees.get(0);
    };

    /**
     * @param d
     * cherche la frequence d'apparition de d dans l'échantillon
     * @return
     */
    public int getFrequence(double d){
        int c =0;
        for(int i = 0; i<taille; i++){
           if(d == (donnees.get(i))) c++;
        }
        return c;
    }

    /** calcul la médiane
     * @return médiane
     */
    public double getMediane() {
        if (taille % 2 ==1) return donneesTriees.get(taille/2);
        int m = taille/2 -1;
        if(m<0) return donneesTriees.get(0);
        if(m==0) return (donneesTriees.get(0)+donneesTriees.get(1))/2;
        return ((donneesTriees.get(m)+donneesTriees.get(m+1))/2);
    }

    /** calcul la somme des carrés totaux
     * @return sct
     */
    public double getSCT() {
        double SCT = 0;
        for (int i =0; i<taille; i++) {
            SCT += Math.pow((donnees.get(i) -getMoyenne()),2);
        }
        return SCT;
    }

    /**
     * @param quartile : le quartile que l'on veut recuperer
     * @return la valeur correspondant au quartile en question
     */
    public double getQuartiles(int quartile) {
        double result = 0;
        float b;
        float a = b = 0;
        if (quartile > 4) throw new IllegalArgumentException("really?");
        if (quartile == 2) return this.getMediane();
        if (quartile == 4) return donneesTriees.get(taille - 1);
        if (quartile == 0) return donneesTriees.get(0);
        if (quartile == 1) result = ((float)taille + 3) / 4 ;
        if (quartile == 3) result = (3 * (float)taille + 1) / 4 ;
        if (result != ((int) result)) {
            a = (float) (result - ((int) result));
            b = 1 - a;
            return (donneesTriees.get((int) result-1) * b + donneesTriees.get((int) result ) * a);
        }
        return  donneesTriees.get((int)result-1);
    }

    /**
     * @return l'échantillon dans une string
     */
    public String toString() {
        String s ="";
        for(int i=0; i<taille; i++) {
            if(i!= taille-1) s += donnees.get(i) + "; ";
            else s += donnees.get(i);
        }
        return s;
    }

    //double getQuantiles(int quantile) {};

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double [] i1 = {1.52, 1.56, 1.54};
        Echantillon v1 = new Echantillon(i1);
        String s = new String("1.2; 3.4567");
        String s2 = new String(("1.0; 2.0; 3.0; 5.0; 9.0"));
        try {
            Echantillon v2 = new Echantillon(s2);
            //System.out.println(v2);
            //System.out.println(v1.toString());
            //System.out.println(v2.toString());
           // System.out.println(v2.getSCT());
            for(int i = 0; i<5; i++) {
                System.out.println(v2.getQuartiles(i));
            }
        }
        catch(ExceptionDonneesEntree de){
            System.out.println(de.getMessage());
        }
    }
}
