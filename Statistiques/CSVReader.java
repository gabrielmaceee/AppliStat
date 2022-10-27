package StatTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {

        String csvFile = "C:/Users/8ni8n/OneDrive/Bureau/language de programmation/AppliStat/data/TableauFisher(1).csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fisher = line.split(cvsSplitBy);
                String value = fisher[2].substring(1, fisher[2].length()-1);  
                System.out.println("ligne = " + fisher[0] + " , colonne=" + fisher[1] + ", valeur="+ value);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}