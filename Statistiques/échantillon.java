package Statistiques;

import java.util.Arrays;

class echantillon {
private double[] donnees;
private int taille;
private double[] donneesTriees;

échantillon(double [] TabDonnees) {
	if(TabDonnees.length ==0) {
	this.donnees = new double[0];
	taille = 0;
	throw new IllegalArgumentException("le tableau est vide");
	}

	else { taille = TabDonnees.length;
	this.donnees = TabDonnees;
	}
	donneesTriees = donnees;
	Arrays.sort(donneesTriees);
}

double getMoyenne(){
	double moyenne = 0;
	for(int i = 0; i<taille; i ++) {
		moyenne += donnees[i];
	}
	return moyenne/taille;
}
double getVariance(){
	double var = 0;
	for(int i = 0; i<taille; i ++) {
		var += Math.pow((donnees[i]- getMoyenne()),2);
	}
	return var/taille;
}
double getEcartype(){
	return Math.sqrt(getVariance());
}
double getMaximum(){
	return donneesTriees[taille-1];
};
double getMinimum() {
	return donneesTriees[0];
};
double getMediane() {
	if (taille % 2 ==0) return donneesTriees[taille/2];
	int m = taille/2;
	return (donneesTriees[m]+donneesTriees[m+1])/2;
}
double getSCE() {};
double getSCM() {};
double getSCT() {};
double getQuantiles(int quantile) {};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
