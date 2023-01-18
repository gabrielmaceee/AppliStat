package com.example.statistiques;

/**
 * classe permettant de realiser un test d'anova
 */
public class Anova{
    /**
     * tableau des échantillons de l'utilisateurs
     */
    Echantillon [] tabEchantillons;

    /**
     * nombre total d'échantillons
     */
    int a;
    /**
     * taille des échantillons
     */
    int t;
    /**
     * nombre total de données
     */
    int n;
    /**
     * tableau des moyennes
     */
    double [] tabMoyennes;
    /**
     * tableau des variances
     */
    double [] tabVariances;

    /**
     * @param tab : un tableau des échantillons de l'utilisateurs
     * @throws ExceptionTailleEchantillon : si les echantillons sont de differentes tailles
     * @throws ExceptionNombreEchantillons : si il y a moins de 2 echantillons
     */
    Anova (Echantillon[] tab) throws ExceptionTailleEchantillon, ExceptionNombreEchantillons{
            if (tab.length < 2) {
                throw new ExceptionNombreEchantillons();
            }
                tabEchantillons = tab;
                a = tab.length;
                t = tabEchantillons[0].taille;
                tabMoyennes = new double[a];
                tabVariances = new double[a];
                for (int i = 0; i < a; i++) {
                    if (tabEchantillons[i].taille != t)
                        throw new ExceptionTailleEchantillon();
                    tabMoyennes[i] = tabEchantillons[i].getMoyenne();
                    tabVariances[i] = tabEchantillons[i].getVariance() * (t - 1);
                    n = a * t;
                }
    }

    /**
     * calcul la somme du carré des erreurs
     * @return sce
     */
    double getSCE() {
        double SCE = 0;
        for(int i = 0; i<a; i++) {
            SCE += tabVariances[i];
        }
        return SCE;
    }

    /**
     * calcul la somme du carré du modèle
     * @return scm
     */
    double getSCM() {
        double SCM = 0;
        double moyenneTotale = 0;
        for(int i = 0; i<a; i ++) {
            moyenneTotale += tabMoyennes[i];
        }
        moyenneTotale /= a;
        for(int i = 0; i<a; i ++) {
            SCM += t*Math.pow((tabMoyennes[i]-moyenneTotale),2);
        }
        return SCM;
    }

    /**
     * calcul l'indice de Fischer empirique
     * @return F
     */
    double getF() {
        return (getSCM()/ (a-1))/(getSCE()/(n-a));
    }

    /** compare l'indice de Fisher à la zone de rejet issue des tables
     * @return la décision de rejetter H0 ou non, dans une string
     */
    String decision() {
        CSVFisherReader csv  = new CSVFisherReader();
        String s = "H0 = égalité des moyennes \nSCM = "+getSCM()+"\n"+ "SCE = "+getSCE()+"\nindice F = "+getF() + "\nQuantile théorique = "+ csv.getQuantile(a-1,n-a)+"\n";
        if (getF()<csv.getQuantile(a-1,n-a)) {
            return (s +"Au seuil 5% on ne rejette pas H0");
            //return true;//on rejette pas l'égalité des moyennes
        }
        return ( s+"Au seuil 5% on rejette H0");//vi explique significativement vd
        //return false;
    }

    /**
     * @return les échantillons dans une string
     */
    public String toString() {
        String s = "";
        for( int i = 0; i<a; i++) {
            s += tabEchantillons[i] +"\n";
        }
        return s;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[] i1 = {1.52, 1.56, 1.54};
        double[] i2 = {1.63, 1.57, 1.54};
        double[] i3 = {1.44, 1.52, 1.63};
        Echantillon v1 = new Echantillon(i1);
        Echantillon v2 = new Echantillon(i2);
        Echantillon v3 = new Echantillon(i3);

        try {
            Anova test = new Anova(new Echantillon[]{v1, v2, v3});
           /* System.out.println(test.getSCE());
            System.out.println(test.getSCM());
            System.out.println(test.getF());*/
            System.out.println(test.decision());
        } catch (ExceptionNombreEchantillons en){
            System.out.println(en.getMessage());

        } catch(ExceptionTailleEchantillon et) {
            System.out.println(et.getMessage());
        }
        //System.out.println(test.n);
        //System.out.println(test.t);
        //System.out.println(test.a);

 /*for( int i = 0; i<test.n; i++) {
 System.out.println(test.tabMoyennes[i]);
 System.out.println(test.tabVariances[i]);
 }*/
    }
}