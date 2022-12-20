package fr.statface.Statistiques;

import java.util.*;


public class Echantillon {

    public List<Double> donnees;
    int taille;
    List<Double> donneesTriees;


    public Echantillon(String s) throws ExceptionDonneesEntree{

        if (s == null || s.length() == 0) {throw new ExceptionDonneesEntree();}

                String[] sprim = s.split(";");
                donnees = new ArrayList<>();
                for (String value : sprim) {
                     //if((sprim[i] instanceof String) == false) throw new ExceptionDonneesEntree();
                    try {
                        double c = Double.parseDouble(value);
                        donnees.add(c);
                    }catch (Exception e) {
                        //System.out.println(e.getMessage());
                        throw new ExceptionDonneesEntree();
                }
        }
                instanciationTrie(donnees);
    }


    public Echantillon(double[] TabDonnees) {
        if(TabDonnees.length ==0) throw new IllegalArgumentException("le tableau est vide");
        donnees = new ArrayList<>();
        for (double tabDonnee : TabDonnees) this.donnees.add(tabDonnee);
        instanciationTrie(donnees);
    }

    void instanciationTrie(List<Double> tab){
        donneesTriees = new ArrayList<>();
        taille = tab.size();
        donneesTriees.addAll(donnees);
        Collections.sort(donneesTriees);
    }

    public int getTaille(){
        return taille;
    }

    public double getMoyenne(){
        double moyenne = 0;
        for(int i = 0; i<taille; i ++) {
            moyenne += donnees.get(i);
        }
        return moyenne/taille;
    }
    public double getVariance(){
        double var = 0;
        for(int i = 0; i<taille; i ++) {
            var += Math.pow((donnees.get(i)- getMoyenne()),2);
        }
        return var/(taille-1);
    }
    public double getEcarttype(){
        return Math.sqrt(getVariance());
    }
    public double getMaximum(){
        return donneesTriees.get(taille-1);
    };

    public double getMinimum() {
        return donneesTriees.get(0);
    };
    public int getFrequence(double d){
        int c =0;
        for(int i = 0; i<taille; i++){
           if(d == (donnees.get(i))) c++;
        }
        return c;
    }
    public double getMediane() {
        if (taille % 2 ==1) return donneesTriees.get(taille/2);
        int m = taille/2 -1;
        if(m<0) return donneesTriees.get(0);
        if(m==0) return (donneesTriees.get(0)+donneesTriees.get(1))/2;
        return ((donneesTriees.get(m)+donneesTriees.get(m+1))/2);
    }
    public double getSCT() {
        double SCT = 0;
        for (int i =0; i<taille; i++) {
            SCT += Math.pow((donnees.get(i) -getMoyenne()),2);
        }
        return SCT;
    }
    public double getQuartiles(int quartile) {
        double result = 0;
        float a =0,b = 0;
        if (quartile > 4) throw new IllegalArgumentException("really?");
        else if (quartile == 2) return this.getMediane();
        else if (quartile == 4) return donneesTriees.get(taille - 1);
        else if (quartile == 0) return donneesTriees.get(0);
        else if (quartile == 1) result = ((float)taille + 3) / 4 ;
        else if (quartile == 3) result = (3 * (float)taille + 1) / 4 ;

        if (result != ((int) result)) {
            a = (float) (result - ((int) result));
            b = 1 - a;
            return (donneesTriees.get((int) result-1) * b + donneesTriees.get((int) result ) * a);
        }
        return  donneesTriees.get((int)result-1);
    }
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
