package com.example.statistiques;


public class Anova{
    Echantillon [] tabEchantillons;

    int a;//nombre total d'échantillons
    int t; //taille des échantillons
    int n; //nombre total de données
    double [] tabMoyennes;
    double [] tabVariances;

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

    double getSCE() {
        double SCE = 0;
        for(int i = 0; i<a; i++) {
            SCE += tabVariances[i];
        }
        return SCE;
    }

    double getSCM() {
        double SCM = 0;
        double moyenneTotale = 0;
        for(int i = 0; i<a; i ++) {
            moyenneTotale += tabMoyennes[i];
        }
        moyenneTotale /= a;
        for(int i = 0; i<a; i ++) {
            SCM += t*Math.pow((tabEchantillons[i].getMoyenne()-moyenneTotale),2);
        }

        return SCM;
    }

    double getF() {
        return (getSCM()/ (a-1))/(getSCE()/(n-a));
    }

    String decision() {
        if (getF()<CSVReader.getQuantile(a-1,n-a)) {
            return ("Au seuil 5% on ne rejette pas l'egalite des moyennes");
            //return true;//on rejette pas l'égalité des moyennes
        }
        return ("Au seuil 5% on rejette l'egalite des moyennes");//vi explique significativement vd
        //return false;
    }

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
            System.out.println(test.getSCE());
            System.out.println(test.getSCM());
            System.out.println(test.getF());
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