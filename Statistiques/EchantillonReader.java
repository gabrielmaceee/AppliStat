package com.example.statistiques;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * class permettant de creer un echantillon à partir d'un fichier txt ou csv
 */
public class EchantillonReader {
    /**
     * chemin vers le fichier
     */
    private final String File;
    /**
     * compteur du nombre d'échantillons'
     */
    private int compteur;

    /**
     * @param chemin : chemin vers le fichier à lire
     */
    public EchantillonReader(String chemin) {
        this.File =chemin;
        System.out.println(File);
    }

    /**
     * lit le fichier et creer un tableau de string contenant les donnees
     * @return un tableau de string contenant chacune une donnée
     * @throws ExceptionDonneesEntree : si le fichier n'est ni un csv, ni un txt, ni rempli
     */
    public String[] EchantillonToString() throws ExceptionDonneesEntree {
        String line;
        int p = getCompteur();
        int k=0;
        String type = File.substring(File.indexOf('.'));
        if(type.equals(".txt")) {
            try (BufferedReader br = new BufferedReader(new FileReader(File))) {
                String[] finale = new String[p];
                while ((line = br.readLine()) != null || k < p) {
                    finale[k] = line;
                    k++;
                }
                return finale;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if (type.equals(".csv")){
            try (BufferedReader br = new BufferedReader(new FileReader(File))) {
                StringBuilder[] finale = new StringBuilder[p];
                for (int o=0;o<finale.length;o++)finale[o]=new StringBuilder("");
                br.readLine();
                while ((line = br.readLine()) != null ) {
                    for(int u=0;u<p;u++){
                        try{
                            if(line.split(",")[u].equals(""))finale[u].append("0");
                            finale[u].append(line.split(",")[u]+";");
                        }catch(Exception e){
                            finale[u].append("0;");
                        }
                    }
                }
                String[] finale2 = new String[finale.length];
                for(int j=0;j<finale.length;j++){
                    finale2[j]=finale[j].toString();
                }
                return finale2;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       throw new ExceptionDonneesEntree();
    }

    /**
     * @return compteur
     */
    public int getCompteur(){
        String type = File.substring(File.indexOf('.'));
        try (BufferedReader br = new BufferedReader(new FileReader(File))) {
            if(type.equals(".csv"))return br.readLine().split(",").length;
            while ((br.readLine()) != null){
                compteur++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compteur;
    }

}
