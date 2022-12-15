package com.example.statistiques;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFisherReader {
    private final String csvFile;
    private  List<String[]> data=new ArrayList<>();
    Path path = Paths.get("src","main","Data","TableauFisher(1).csv");
    public CSVFisherReader() {
        this.csvFile =path.toAbsolutePath().toString();
        System.out.println(csvFile);
    }

    public  double getQuantile(int a1, int na) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String cvsSplitBy = ",";
                fisher = line.split(cvsSplitBy);
                value = fisher[2].substring(0, fisher[2].length()-1);
                if(na==Integer.parseInt(fisher[0])&& a1==Integer.parseInt(fisher[1])) {
                    data.add(0,fisher);
                    return Double.parseDouble(value);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}