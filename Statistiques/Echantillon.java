package Statistiques;

import java.util.Arrays;

class Echantillon {
	
double[] donnees;
int taille;
double[] donneesTriees;


    Echantillon(String s){
    	donnees = new double[0];
    	String d = new String();
    	for (int i=0; i<s.length(); i++) {
    		if(s.charAt(i)== ';')  {
    			donnees = Arrays.copyOf(donnees, taille+1);
        		donnees[taille]= Double.parseDouble(d);
        		taille += 1;
        		d = "";
        		i++;
    		}
    		else if (i == s.length()-1){
    			d += s.substring(i, i+1);
    			donnees = Arrays.copyOf(donnees, taille+1);
        		donnees[taille]= Double.parseDouble(d);
        		taille += 1;
    		}
    		else d += s.substring(i,i+1);
    	}
    	donneesTriees = Arrays.copyOf(donnees, taille);
    }
    
    
	Echantillon(double [] TabDonnees) {
		if(TabDonnees.length ==0) {
			throw new IllegalArgumentException("le tableau est vide");
		} else { 
			taille = TabDonnees.length;
			this.donnees = TabDonnees;
		}	
		donneesTriees = Arrays.copyOf(donnees,taille);
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
		return var/(taille-1);
	}
	double getEcarttype(){
		return Math.sqrt(getVariance());
	}
	double getMaximum(){
		return donneesTriees[taille-1];
	};
	
	double getMinimum() {
		return donneesTriees[0];
	};
	double getMediane() {
		if (taille % 2 ==1) return donneesTriees[((int)taille/2)];
		int m = taille/2;
		return (donneesTriees[m-1]+donneesTriees[m])/2;
	}
	double getSCT() {
		double SCT = 0;
		for (int i =0; i<taille; i++) {
			SCT += Math.pow((donnees[i] -getMoyenne()),2);
		}
		return SCT;
	}
	double getQuartiles(int quartile) {
		if(quartile > 4 ) throw new IllegalArgumentException("really?");
		if(quartile == 1) return donneesTriees[((int)(taille+3)/4-1)];
		if(quartile == 3) return donneesTriees[((int)(3*taille+1)/4-1)];
		if(quartile == 2) return this.getMediane();
		if(quartile == 4 ) return donneesTriees[taille-1];
		return donneesTriees[0];
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
		String s = new String("1.2; 3.4567");
		Echantillon v2 = new Echantillon(s);
		//System.out.println(v1.toString());
		System.out.println(v2.toString());
		System.out.println(v2.getSCT());
		System.out.println(v2.getQuartiles(3));

	}

}
