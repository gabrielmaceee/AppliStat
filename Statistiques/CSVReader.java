package Statistiques;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static double getQuantile(int a1, int na) {

        String csvFile = "C:/Users/8ni8n/OneDrive/Bureau/language de programmation/AppliStat/data/TableauFisher(1).csv";
        String line;
        String cvsSplitBy = ",";
        if(0>=a1 || 0>=na)throw new IllegalArgumentException("valeurs négatives");
        if(a1>1000)a1=1000;
        if(na>2000)na=2000;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fisher = line.split(cvsSplitBy);
                String value = fisher[2].substring(0, fisher[2].length()-1);
                if(na==Integer.parseInt(fisher[0])&& a1==Integer.parseInt(fisher[1]))
                    return Double.parseDouble(value);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
