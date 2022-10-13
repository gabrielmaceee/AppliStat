package Statistiques;

public class Anova {
Echantillon [] tabEchantillons;
int a;//nombre total d'échantillons
int t; //taille des échantillons
int n; //nombre total de données
double [] tabMoyennes;
double [] tabVariances;

Anova (Echantillon[] tab){
	tabEchantillons =tab;
	a = tab.length;
	t = tabEchantillons[0].taille;
	tabMoyennes = new double[a];
	tabVariances = new double [a];
	for(int i = 0; i<a; i++) {
		if (tabEchantillons[i].taille != t) throw new IllegalArgumentException("Les échantillons doivent tous être de la même taille");
		tabMoyennes[i] = tabEchantillons[i].getMoyenne();
		tabVariances[i] = tabEchantillons[i].getVariance()*(t-1);
		n = a*t;
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
	double F = 0;
	F = (getSCM()/ (a-1))/(getSCE()/(n-a));
	return F;
}
double getQuantile() {
	return 0;
}
boolean decision() {
	if (getF()<getQuantile())
		return true;//on rejette pas l'égalité des moyennes
	return false;
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
	double [] i1 = {1.52, 1.56, 1.54};
	double [] i2 = {1.63, 1.57, 1.54};
	double [] i3 = {1.44, 1.52, 1.63};
 Echantillon v1 = new Echantillon(i1);
 Echantillon v2 = new Echantillon(i2);
 Echantillon v3 = new Echantillon(i3);
 Anova test = new Anova(new Echantillon[] {v1, v2, v3});
 //System.out.println(test.n);
 //System.out.println(test.t);
 //System.out.println(test.a);
 System.out.println(test.getSCE());
 System.out.println(test.getSCM());
 System.out.println(test.getF());
 /*for( int i = 0; i<test.n; i++) {
 System.out.println(test.tabMoyennes[i]);
 System.out.println(test.tabVariances[i]);
 }*/
}

}
