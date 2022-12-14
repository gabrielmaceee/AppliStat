package com.example.statistiques;

public class RegressionLineaire {
    Echantillon vd;
    Echantillon vi;

    RegressionLineaire (Echantillon vi, Echantillon vd) throws ExceptionTailleEchantillon{
        if (vi.taille != vd.taille) throw new ExceptionTailleEchantillon();
        this.vd = vd;
        this.vi = vi;
    }
    double getCov() {
        double cov =0;
        for(int i =0; i<vd.taille; i++) {
            cov += ((vi.donnees.get(i)-vi.getMoyenne())*(vd.donnees.get(i)-vd.getMoyenne()));
        }
        return cov/(vd.taille-1);
    }
    double getBeta1(){
        return getCov()/vi.getVariance();
    }
    double getBeta0() {
        return vd.getMoyenne()- (getBeta1()*vi.getMoyenne());
    }
    double getr() {
        return (getCov()/(Math.sqrt(vi.getVariance()*vd.getVariance())));
    }
    double getR() {
        return getr()*getr();
    }
    double getSCM() {
        return getR()*vi.getSCT();
    }

    double getSCE() {
        return vi.getSCT()-getSCM();
    }

    double getF() {
        return (getSCM()*(vd.taille-2))/getSCE();
    }

    String decision() {
        String s = "H0 = x n'a pas d'effet sur y \nCovariance = " + getCov() +"\nBeta1 = "+getBeta1()+"\nBeta0 = "+ getBeta0()+"\nr = "+ getr() + "\nR² = "+getR()+"\n";

        if(getR()<0.8) {
            return (s+"Moins de 80 % de la variance est expliquée par le modèle, une régression lineaire n'est donc pas toleree");
           // throw new RuntimeException("Moins de 80 % de la variance est expliquée par le modèle, une régression lineaire n'est donc pas toleree");
        }
        s += "SCM = "+getSCM()+"\n"+ "SCE = "+getSCE()+"\nindice F = "+getF() + "\nQuantile théorique = "+ CSVReader.getQuantile(1,vd.taille-2)+"\n";
        if (getF()>CSVReader.getQuantile(1,vd.taille-2)) {
            return(s+ "Au seuil 5%, on rejette H0 : x a un effet sur y");
           // return true;
        }
        return(s+"Au seuil 5%, on ne peut pas rejeter H0 : x n'a pas d'effet sur y");
        //return false;
    }
    double[] getYjust(){
        double[] yjust = new double[vi.taille];
        int k=0;
        double beta0=getBeta0(),beta1=getBeta1();
        for (double i: vi.donnees){
            yjust[k] = beta1*i+beta0;
            k++;
        }
        return yjust;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double [] i = {1,2,3,4};
        double [] d = {3,5,7,8};
        Echantillon vd = new Echantillon(d);
        Echantillon vi = new Echantillon(i);


        try {
            RegressionLineaire test = new RegressionLineaire(vi,vd);
           /* System.out.println("cov="+test.getCov());
            System.out.println(test.getBeta1());
            System.out.println(test.getBeta0());
            System.out.println(test.getR());
            System.out.println(test.getr());*/
            System.out.println(test.decision());
        }
        catch (ExceptionTailleEchantillon et){
            System.out.println(et.getMessage());
        }


    }
}