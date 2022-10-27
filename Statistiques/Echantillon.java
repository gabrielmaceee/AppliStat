package Statiqtiques;

import java.util.*;


class Echantillon {
	
List<Double> donnees;
int taille;
List<Double> donneesTriees;


    Echantillon(String s){
    	if(s == null || s.length()==0) throw new IllegalArgumentException("L'echantillon ne doit pas etre vide");
		String[] sprim = s.split(";");
		donnees = new ArrayList<>();
		for(int i=0;i<sprim.length;i++) donnees.add(Double.valueOf(sprim[i]));
		instanciationTrie(donnees);
    }
    
    
	Echantillon(double[] TabDonnees) {
		if(TabDonnees.length ==0) throw new IllegalArgumentException("le tableau est vide");
		donnees = new ArrayList<>();
		for(int i=0;i<TabDonnees.length;i++)this.donnees.add(TabDonnees[i]);
		instanciationTrie(donnees);
	}
	
	void instanciationTrie(List<Double> tab){
		donneesTriees = new ArrayList<>(); 
		taille = tab.size();	
		donneesTriees.addAll(donnees);
		Collections.sort(donneesTriees);
	}

	int getTaille(){
		return taille;
	}

	double getMoyenne(){
		double moyenne = 0;
		for(int i = 0; i<taille; i ++) {
		moyenne += donnees.get(i);
		}
		return moyenne/taille;
	}
	double getVariance(){
		double var = 0;
		for(int i = 0; i<taille; i ++) {
			var += Math.pow((donnees.get(i)- getMoyenne()),2);
		}
		return var/(taille-1);
	}
	double getEcarttype(){
		return Math.sqrt(getVariance());
	}
	double getMaximum(){
		return donneesTriees.get(taille-1);
	};
	
	double getMinimum() {
		return donneesTriees.get(0);
	};
	double getMediane() {
		if (taille % 2 ==1) return donneesTriees.get((int)taille/2);
		int m = taille/2;
		return (donneesTriees.get(m-1)+donneesTriees.get(m/2));
	}
	double getSCT() {
		double SCT = 0;
		for (int i =0; i<taille; i++) {
			SCT += Math.pow((donnees.get(i) -getMoyenne()),2);
		}
		return SCT;
	}
	double getQuartiles(int quartile) {
		if(quartile > 4 ) throw new IllegalArgumentException("really?");
		if(quartile == 1) return donneesTriees.get((int)(taille+3)/4-1);
		if(quartile == 3) return donneesTriees.get((int)(3*taille+1)/4-1);
		if(quartile == 2) return this.getMediane();
		if(quartile == 4 ) return donneesTriees.get(taille-1);
		return donneesTriees.get(0);
	}
	public String toString() {
		String s ="";
		for(int i=0; i<taille; i++) {
			if(i!= taille-1) s += donnees.get(i) + ", ";
			else s += donnees.get(i);
		}
		return s;
	}

	//double getQuantiles(int quantile) {};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] i1 = {1.52, 1.56, 1.54};
		Echantillon v1 = new Echantillon(i1);
		String s = new String("1.2; 3.4567");
		Echantillon v2 = new Echantillon(s);
		//System.out.println(v1.toString());
		System.out.println(v2.toString());
		System.out.println(v2.getSCT());
		System.out.println(v2.getQuartiles(3));

	}

}
