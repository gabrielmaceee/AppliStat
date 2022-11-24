package com.example.statistiques;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    static String csvFile= "C:/Users/33750/eclipse-workspace/AppliStat/data/TableauFisher(1).csv";
    static String csvSave = "C:/Users/33750/eclipse-workspace/AppliStat/data/Save.txt/";
    static List<String[]> data=new ArrayList<>();
    static String cvsSplitBy = ",";

    public static double getQuantile(int a1, int na) {
        File csvSafe = new File(csvSave);
        String line;
        String[] fisher;
        String value;
        if(0>=a1 || 0>=na)throw new IllegalArgumentException("valeurs négatives");
        if(a1>1000)a1=1000;
        if(na>2000)na=2000;
        for (String[] strings : data) {
            fisher = strings;
            value = fisher[2].substring(0, fisher[2].length() - 1);
            if (na == Integer.parseInt(fisher[0]) && a1 == Integer.parseInt(fisher[1])) {
                return Double.parseDouble(value);
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(csvSave))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                fisher = line.split(cvsSplitBy);
                value = fisher[2].substring(0, fisher[2].length() - 1);
                if (na == Integer.parseInt(fisher[0]) && a1 == Integer.parseInt(fisher[1])) {
                    data.add(0,fisher);
                    return Double.parseDouble(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             FileWriter output = new FileWriter(csvSafe)) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                fisher = line.split(cvsSplitBy);
                value = fisher[2].substring(0, fisher[2].length()-1);
                if(na==Integer.parseInt(fisher[0])&& a1==Integer.parseInt(fisher[1])) {
                    data.add(0,fisher);
                    for (String[] datum : data) {
                        fisher = datum;
                        output.write((fisher[0] + "," + fisher[1] + "," + fisher[2])+"\n");
                    }
                    return Double.parseDouble(value);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}