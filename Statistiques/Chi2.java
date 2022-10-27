package Statistiques;
public class Chi2 {
	final double[] tabCHI2 =  {2.706,3.841,5.991,7.815,9.488,11.07,12.592,14.067,15.507,16.919,18.307,19.675,21.026,22.362,23.685,24.996,26.296,27.587,28.869,30.144,31.41,32.671,33.924,35.172,36.415,37.652,38.885,40.113,41.337,42.557,43.773,44.985,46.194,47.4,48.602,49.802,50.998,52.192,53.384,54.572,55.758,56.942,58.124,59.304,60.481,61.656,62.83,64.001,65.171,66.339,67.505,68.669,69.832,70.993,72.153,73.311,74.468,75.624,76.778,77.931,79.082,80.232,81.381,82.529,83.675,84.821,85.965,87.108,88.25,89.391,90.531,91.67,92.808,93.945,95.081,96.217,97.351,98.484,99.617,100.749,101.879,103.01,104.139,105.267,106.395,107.522,108.648,109.773,110.898,112.022,113.145,114.268,115.39,116.511,117.632,118.752,119.871,120.99,122.108,123.225,124.342,125.458,126.574,127.689,128.804,129.918,131.031,132.144,133.257,134.369,135.48,136.591,137.701,138.811,139.921,141.03,142.138,143.246,144.354,145.461,146.567,147.674,148.779,149.885,150.989,152.094,153.198,154.302,155.405,156.508,157.61,158.712,159.814,160.915,162.016,163.116,164.216,165.316,166.415,167.514,168.613,169.711,170.809,171.907,173.004,174.101,175.198,176.294,177.39,178.485,179.581,180.676,181.77,182.865,183.959,185.052,186.146,187.239,188.332,189.424,190.516,191.608,192.7,193.791,194.883,195.973,197.064,198.154,199.244,200.334,201.423,202.513,203.602,204.69,205.779,206.867,207.955,209.042,210.13,211.217,212.304,213.391,214.477,215.563,216.649,217.735,218.82,219.906,220.991,222.076,223.16,225.329,226.413,227.496,228.58,229.663,230.746,231.829,232.912,233.994};
	Echantillon[] tab;

	int nbEchantillon;
	int tailleEchantillons;
	int degreLiberte;
	
	Chi2 (Echantillon[] a){
		if (a.length <2) throw new IllegalArgumentException("Un test du chi 2 se fait au moins sur deux echantillons");
		tailleEchantillons = a[0].taille;
		for(int i = 1; i<nbEchantillon; i++) {
			if(tailleEchantillons!=a[i].taille )
				throw new IllegalArgumentException("Les echantillons doivent etre de meme taille");
		}
		tab = a;
		nbEchantillon = tab.length;
		degreLiberte = (nbEchantillon-1)*(tailleEchantillons-1);
	}
	
	double getDifference() {
        Echantillon[] tabAttendues = new Echantillon[nbEchantillon];
		double sommeTotale=0;
		double[] sommeColonne = new double[nbEchantillon];
		double[] sommeLigne = new double[tailleEchantillons];
		
		for(int j=0; j<tailleEchantillons; j++) {
		   for(int i = 0; i<nbEchantillon; i++) {
			sommeLigne[j] += tab[i].donnees.get(j);
		   }
		}
		
		for(int j=0; j<nbEchantillon; j++) {
			   for(int i = 0; i<tailleEchantillons; i++) {
				  sommeColonne[j] += tab[j].donnees.get(i);
			   }
			   sommeTotale +=sommeColonne[j];
			}
		
		
		
		for(int i = 0; i<nbEchantillon; i++) {//colonnes
			double tabD [] = new double[tailleEchantillons];
			for(int k = 0; k<tailleEchantillons; k++) {//recherche d'une valeur attendue dans la colonne i et la ligne j
				tabD[k]= ((sommeColonne[i]*sommeLigne[k])/sommeTotale);
				if(tabD[k]<5) {throw new IllegalArgumentException("Au moins une valeur theorique est egale à 5");}
				//System.out.println(tabD[k]);
				}
			tabAttendues[i]= new Echantillon(tabD);
		}			

		

		double diff =0;
		for(int i = 0; i<nbEchantillon; i++) {
			for(int k = 0; k<tailleEchantillons; k++) {
				diff+=Math.pow((tab[i].donnees.get(k)-tabAttendues[i].donnees.get(k)),2)/tabAttendues[i].donnees.get(k);
			}
		}
		return diff;
	}
	
	boolean decision() {	
		if(getDifference() <= tabCHI2[degreLiberte-1]) {
			System.out.println("Au seuil 5% nous rejetons l'independance des variables");
		return false;
		}
	System.out.println("Au seuil 5%, nous ne pouvons pas rejeter l'independance des variables");
	return true; 
}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] i1 = {10,20,20};
		double [] i2 = {15,85,100};
		//double [] i3 = {14, 15, 19, 19, 67};
	 Echantillon v1 = new Echantillon(i1);
	 Echantillon v2 = new Echantillon(i2);
	 //Echantillon v3 = new Echantillon(i3);
		Chi2 i =new Chi2(new Echantillon[] {v1, v2/*,v3*/});
		System.out.println(i.getDifference());
		i.decision();		 

	}

}
