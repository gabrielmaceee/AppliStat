package Statistiques;

import java.util.Arrays;

class Echantillon {
	
double[] donnees;
int taille;
double[] donneesTriees;

	Echantillon(double [] TabDonnees) {
		if(TabDonnees.length ==0) {
			throw new IllegalArgumentException("le tableau est vide");
		} else { 
			taille = TabDonnees.length;
			this.donnees = TabDonnees;
		}	
		donneesTriees = donnees;
		//Arrays.sort(donneesTriees);
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
		return var/(taille-1);
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
	double getSCT() {
		double SCT = 0;
		for (int i =0; i<taille; i++) {
			SCT += Math.pow((donnees[i] -getMoyenne()),2);
		}
		return SCT;
	}
	public String toString() {
		String s ="";
		for(int i=0; i<taille; i++) {
			if(i!= taille-1) s += donnees[i] + ", ";
			else s += donnees[i];
		}
		return s;
	}

	//double getQuantiles(int quantile) {};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] i1 = {1.52, 1.56, 1.54};
		Echantillon v1 = new Echantillon(i1);
		System.out.println(v1.toString());

	}

}
