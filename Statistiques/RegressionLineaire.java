package Statistiques;

public class RegressionLineaire {
Echantillon vd;
Echantillon vi;
	
RegressionLineaire (Echantillon vi, Echantillon vd){
	if (vi.taille != vd.taille) throw new IllegalArgumentException("Les échantillons doivent être de même taille");
	this.vd = vd;
	this.vi = vi;
}
double getCov() {
	double cov =0;
	for(int i =0; i<vd.taille; i++) {
		cov += ((vi.donnees[i]-vi.getMoyenne())*(vd.donnees[i]-vd.getMoyenne()));
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
	return getR()*vd.getSCT();
}

double getSCE() {
	return vi.getSCT()-getSCM();
}

double getF() {
	return (getSCM()*(vd.taille-2))/getSCE();
}
double getQuantile() {
	return 0;
}
boolean decision() {
	if (getF()>getQuantile())
		return true;//vi explique vd
	return false;
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
 
}
}
