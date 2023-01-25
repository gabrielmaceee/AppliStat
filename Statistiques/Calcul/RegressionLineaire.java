package com.example.statistiques.Calcul;

import com.example.statistiques.Exception.ExceptionTailleEchantillon;
import com.example.statistiques.Lecture.CSVFisherReader;

import java.nio.file.Path;
import java.util.List;

/**
 * class permettant d'effectuer un test de regression lineaire
 */
public class RegressionLineaire {
    /**
     * y,echantillon sensé être expliquée par x
     */
    private Echantillon vd;
    /**
     *  x, echantillon sensé avoir un effet sur y
     */
    private Echantillon vi;

    private Path p;
    /**
     * @param vi echantillon de la vairable x
     * @param vd echantillon de la variable y
     * @throws ExceptionTailleEchantillon
     */
    public RegressionLineaire(Echantillon vi, Echantillon vd, Path path) throws ExceptionTailleEchantillon {
        this(vi,vd);
        p = path;
    }
    public RegressionLineaire(Echantillon vi, Echantillon vd) throws ExceptionTailleEchantillon {
        if (vi.getTaille() != vd.getTaille()) throw new ExceptionTailleEchantillon();
        this.vd = vd;
        this.vi = vi;
    }

    /** calcul la covariance
     * @return la covariance
     */
    double getCov() {
        double cov =0;
        int k = vd.getTaille();
        for(int i = 0; i< k; i++) {
            cov += ((vi.getDonnees().get(i)-vi.getMoyenne())*(vd.getDonnees().get(i)-vd.getMoyenne()));
        }
        return cov/(k -1);
    }

    /** calcul Beta1
     * @return Beta1
     */
    double getBeta1(){
        return getCov()/vi.getVariance();
    }

    /** calcul Beta0
     * @return Beta0
     */
    double getBeta0() {
        return vd.getMoyenne()- (getBeta1()*vi.getMoyenne());
    }

    /** calcul r
     * @return r
     */
    double getr() {
        return (getCov()/(Math.sqrt(vi.getVariance()*vd.getVariance())));
    }

    /** calcul R (r²)
     * @return R
     */
    double getR() {
        return getr()*getr();
    }

    /** calcul la somme des carrées du modèle
     * @return SCM
     */
    double getSCM() {
        return getR()*vi.getSCT();
    }

    /**
     * @return
     */
    double getSCE() {
        return vi.getSCT()-getSCM();
    }

    /** calcul l'indice de Fisher empirique
     * @return F
     */
    double getF() {
        return (getSCM()*(vd.getTaille() -2))/getSCE();
    }

    /** compare F avec la zone de rejet de la table de Fisher
     * @return la décision de rejeter ou non H0, sous forme d'une string
     */
    public String decision() {
        String s = "H0 = x n'a pas d'effet sur y \nCovariance = " + getCov() +"\nBeta1 = "+getBeta1()+"\nBeta0 = "+ getBeta0()+"\nr = "+ getr() + "\nR² = "+getR()+"\n";
        CSVFisherReader csv;
        if (p == null) csv = new CSVFisherReader();
        else csv = new CSVFisherReader(p);
        if(getR()<0.8) {
            return (s+"Moins de 80 % de la variance est expliquée par le modèle, une régression lineaire n'est donc pas toleree");
           // throw new RuntimeException("Moins de 80 % de la variance est expliquée par le modèle, une régression lineaire n'est donc pas toleree")
        }
        s += "SCM = "+getSCM()+"\n"+ "SCE = "+getSCE()+"\nindice F = "+getF() + "\nQuantile théorique = "+ csv.getQuantile(1, vd.getTaille() -2)+"\n";
        if (getF()>csv.getQuantile(1, vd.getTaille() -2)) {
            return(s+ "Au seuil 5%, on rejete H0 : x a un effet sur y");
           // return true;
        }
        return(s+"Au seuil 5%, on ne peut pas rejeter H0 : x n'a pas d'effet sur y");
        //return false;
    }

    /** calcul les points de la droite de régression linéaire
     * @return les points de cette droite
     */
    public double[] getReg(){
        int i = vi.getTaille();
        double[] reg = new double[i];
        double beta0=getBeta0(),beta1=getBeta1();
        for(int x=0;x<i;x++)reg[x]=beta1*x+beta0;
        return reg;
    }

    /** calcul les valeurs ajustées de y
     * @return un tableau contenant les valeurs ajustées de y
     */
    public double[] getYjust(){
        double[] yjust = new double[vi.getTaille()];
        int k=0;
        double beta0=getBeta0(),beta1=getBeta1();
        List<Double> vi2 = vi.getDonnees();
        for (double i:vi2 ){
            yjust[k] = beta1*i+beta0;
            k++;
        }
        return yjust;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double [] i = {1.70,2,1.65,1.35,1.76,1.90,1.74,1.76};
        double [] d = {64,90,63,42,70,85,66,68};
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
