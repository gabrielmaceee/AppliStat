package Statistiques;

class échantillon {
private double[] donnees;
private double taille;

échantillon(double [] donnees) {
	this.donnees = donnees;
	taille = donnees.length;
}
double getMoyenne(){
	double moyenne = 0;
	for(int i = 0; i<taille; i ++) {
		moyenne += donnees[i];
	}
	return moyenne/taille;
}
double getVariance(){};
double getEcartype(){};
double getMaximum(){};
double getMinimum() {};
double getMediane() {};
double getSCE() {};
double getSCM() {};
double getSCT() {};
double getQuantiles(int quantile) {};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
