package fr.statface.Statistiques;

import java.util.*;

public class EchantillonQuantitatif extends EchantillonAbstrait<Double> {

    private final ArrayList<Double> donnees;
    protected ArrayList<Double> donneesTriees;

    public EchantillonQuantitatif(String nomEchantillon,String... values) throws ExceptionDonneesEntree {
        super(nomEchantillon);

        //TODO : faire regex
        if (values == null) {throw new ExceptionDonneesEntree();}
        donnees = new ArrayList<Double>();
        for (String value : values) {
            double c = Double.parseDouble(value);
            donnees.add(c);
        }
        instanciationTrie();

    }

    public EchantillonQuantitatif(String... values) throws ExceptionDonneesEntree {
        this("Echantillon",values);
    }

    /**
     *
     * @param values sous format "val;_;val"
     * @throws ExceptionDonneesEntree entrer invalide si le modèle n'est pas respecté
     */
    public EchantillonQuantitatif(String values) throws ExceptionDonneesEntree {
        this("Echantillon",values.split(";"));
    }

    public EchantillonQuantitatif(String nomEchantillon,String values) throws ExceptionDonneesEntree {
        this(nomEchantillon,values.split(";"));
    }

    /**
     *
     * @param values
     * @throws ExceptionDonneesEntree
     * @deprecated Ne pas utiliser : le tableau -> perte de données
     */
    public  EchantillonQuantitatif(double[] values) throws ExceptionDonneesEntree {
        this("Echantillon", values);
    }

    /**
     *
     * @param nomEchantillon
     * @param values
     * @throws ExceptionDonneesEntree
     * @deprecated avec le tableau -> perte de données
     */
    public  EchantillonQuantitatif(String nomEchantillon,double[]  values) throws ExceptionDonneesEntree {
        this(nomEchantillon,
                Arrays.toString(values)
                        .substring(1,values.length - 1)
                        .split(","));
    }

    public  EchantillonQuantitatif(List<Double> values) throws ExceptionDonneesEntree {
        this("Echantillon",values);
    }
    // TODO :  faire l'init ici
    public  EchantillonQuantitatif(String nomEchantillon, List<Double>  values) throws ExceptionDonneesEntree {
        super(nomEchantillon);
        if (values == null || values.size() == 0) {throw new ExceptionDonneesEntree();}
        donnees = new ArrayList<Double>(values);
        instanciationTrie();
    }



    @Override
    protected void instanciationTrie(){
        donneesTriees = new ArrayList<>(donnees);
        Collections.sort(donneesTriees);
    }

    @Override
    public int getTaille() {
        return donnees.size();
    }



    @Override
    public double getMoyenne(){
        double moyenne = 0;
        for(Double i : donnees) {
            moyenne += i;
        }
        return moyenne/getTaille();
    }
    @Override
    public double getVariance(){
        double variance = 0,moyenne = this.getMoyenne();
        for(Double i : donnees) {
            variance += Math.pow((i - moyenne),2);
        }
        return variance/(getTaille()-1);
    }
    @Override
    public double getEcarttype(){
        return Math.sqrt(getVariance());
    }

    @Override
    public Double getMaximum() {
        return Collections.max(donnees);
    }

    @Override
    public Double getMinimum() {
        return Collections.min(donnees);
    }


    @Override
    public int getFrequence(int d){ // ce n'est pas un peu chaud
        int c =0;
        for(Double i : donnees)
            if(d == i.intValue())
                c++;
        return c;
    }


    @Override
    public double getMediane() {
        if (getTaille() % 2 == 1 )
            return donneesTriees.get(getTaille() / 2);
        int m = getTaille()/2 -1;
        if(m<0)
            return donneesTriees.get(0);
        else if(m==0)
            return (donneesTriees.get(0) +
                    donneesTriees.get(1))/2;
        else
            return ((donneesTriees.get(m) +
                    donneesTriees.get(m + 1))/2);
    }

    @Override
    public double getQuartiles(int quartile) {
        double result = 0;
        float a =0,b = 0;
        if (quartile > 4) throw new IllegalArgumentException("really?");
        else if (quartile == 2) return this.getMediane();
        else if (quartile == 4) return donneesTriees.get(getTaille() - 1);
        else if (quartile == 0) return donneesTriees.get(0);
        else if (quartile == 1) result = ((float)getTaille() + 3) / 4 ;
        else if (quartile == 3) result = (3 * (float)getTaille() + 1) / 4 ;

        if (result != ((int) result)) {
            a = (float) (result - ((int) result));
            b = 1 - a;
            return (donneesTriees.get((int) result - 1) * b +
                    donneesTriees.get((int) result) * a);
        }
        return donneesTriees.get((int) result - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        donnees.forEach(d -> sb.append(d).append(";"));
        return super.toString() + sb.toString().substring(0,sb.length() - 2);
    }

    @Override
    public ArrayList<Double> getDonnees() {
        return donnees;
    }

    @Override
    public double getSCT() {
        double SCT = 0, MOYENNE = getMoyenne();
        for (Double i : donnees) {
            SCT += Math.pow((i - MOYENNE),2);
        }
        return SCT;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GenerateurEchantillion echGenerator = new GenerateurEchantillion();

        double [] i1 = {1.52, 1.56, 1.54};
        ArrayList<Double> ls1 = new ArrayList<>();

        for (double i : i1) {
            ls1.add(i);
        }

        Echantillon v1 = new Echantillon(i1);
        String s2 = echGenerator.toString();
        try {
            EchantillonQuantitatif v2 = new EchantillonQuantitatif("echantillon S2 via String",s2);
            EchantillonQuantitatif tab1 = new EchantillonQuantitatif("echantillon t1 via tableau",i1);
            EchantillonQuantitatif listQuantity =
                    new EchantillonQuantitatif("ls1 via ArrayList",ls1);
            System.out.println("tableau i1 : " + Arrays.toString(i1));
            System.out.println(tab1.toString());
            System.out.println(v2.toString());
            System.out.println(v1.toString());
            System.out.println(listQuantity.toString());
            System.out.println(v2.getSCT());
            for(int i = 0; i<5; i++) {
                System.out.println(v2.getQuartiles(i));
            }
        }
        catch(Exception de){
            System.out.println(de.getMessage());
        }
    }
}
