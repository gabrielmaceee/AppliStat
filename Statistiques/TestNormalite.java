package com.example.statistiques;

public class TestNormalite {
    Echantillon e;
    int taille;

    //H0 : les donn�es suivent une loi normale
    TestNormalite(Echantillon ech) {
        e = ech;
        this.taille = ech.getTaille();
    }

    double getS() {
        double S1 = 0;
        double S2 = 0;
        for (int i = 0; i < taille; i++) {
            S1 += Math.pow((e.donnees.get(i) - e.getMoyenne()), 3);
            S2 += Math.pow((e.donnees.get(i) - e.getMoyenne()), 2);
        }

        S1 = S1 / taille;
        S2 = Math.pow((S2 / taille), 1.5);
        return (S1 / S2);
    }

    double getK() {
        double S1 = 0;
        double S2 = 0;
        for (int i = 0; i < taille; i++) {
            S1 += Math.pow((e.donnees.get(i) - e.getMoyenne()), 4);
            S2 += Math.pow((e.donnees.get(i) - e.getMoyenne()), 2);
        }

        S1 = S1 / taille;
        S2 = Math.pow((S2 / taille), 2);
        return (S1 / S2);
    }

    double getJB() {
        double JB = (Math.pow(getS(), 2));
        JB += (Math.pow(getK() - 3, 2)) / 4;
        return (JB / 6) * taille;
    }

    boolean decision() {
        double pvalue = 0.1461;
        if (taille < 20) pvalue = 0.109;
        else if (taille < 30) pvalue = 0.079;
        else if (taille < 50) pvalue = 0.067;
        else if (taille < 70) pvalue = 0.062;

        return getJB() < pvalue; //on ne rejette pas H0 : les donn�es suivent bien une loi normale
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[] t = new double[]{4, 5, 5, 6, 5, 5, 5, 5, 5, 5, 5};
        //double[] t = new double[] {9789,9867,9858,10015,2045,2063,1973,1935};
        Echantillon test = new Echantillon(t);
        TestNormalite a = new TestNormalite(test);
        System.out.println((a.getS() + "\n" + a.getK()) + "\n" + a.getJB());
        System.out.println(a.decision());

    }

}
