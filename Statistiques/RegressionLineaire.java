package Statistiques;
public class RegressionLineaire {
Echantillon vd;
Echantillon vi;
	
RegressionLineaire (Echantillon vi, Echantillon vd){
	if (vi.taille != vd.taille) throw new IllegalArgumentException("Les echantillons doivent etre de meme taille");
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
	return vd.getMoyenne()- (getCov()*vi.getMoyenne());
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

boolean decision() {
	if(getR()<0.8) {
		throw new RuntimeException("Moins de 80 % de la variance est expliquée par le modèle, une régression lineaire n'est donc pas toleree");
	}
	if (getF()>CSVReader.getQuantile(1,vd.taille-2)) {
		System.out.println("Au seuil 5%, on rejette H0 : x a un effet sur y");
		return true;
	}
	System.out.println("Au seuil 5%, on ne peut pas rejeter H0 : x n'a pas d'effet sur y");
	return false;
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
	double [] d = {2,4,6,8};
 Echantillon vd = new Echantillon(d);
 Echantillon vi = new Echantillon(i);

 RegressionLineaire test = new RegressionLineaire(vi,vd);
 System.out.println("cov="+test.getCov());
 System.out.println(test.getBeta1());
 System.out.println(test.getBeta0());
 System.out.println(test.getR());
 System.out.println(test.getr());
 System.out.println(test.decision());
 
}
}
