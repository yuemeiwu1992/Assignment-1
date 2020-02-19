/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topstreamingartist;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.util.Arrays;

/**
 *
 * @author Ana
 */
public class TopStreamingArtist {

    public static void main(String[] args) throws FileNotFoundException {
        
        //input file
        File file = new File("C:\\Users\\Ana\\Documents\\NetBeansProjects\\TopStreamingArtist\\src\\regional-global-weekly-2020-01-17--2020-01-24 (1).csv");
        Scanner sc = new Scanner(file);
        
        //output file
        File file1 = new File("C:\\Users\\Ana\\Documents\\NetBeansProjects\\TopStreamingArtist\\src\\output.txt");
        PrintWriter writer1 = new PrintWriter(file1);
        /**
         * @param args the command line arguments
         */
        int rowCount = 200;
        int col = 5;
       
        // create each string for different elements 
        String[] position = new String[200];
        String[] trackName = new String[200];
        String[] artist = new String[200];
        String[] streams = new String[200];
        String[] URL = new String[200];
        
        //loop through the file and store each column with approriate data
        for (int i = 0; i < rowCount; i++) {
            String row = sc.nextLine();
            String[] data = row.split(",");
            position[i] = data[0];
            trackName[i] = data[1];
            artist[i] = data[2];
            streams[i] = data[3];
            URL[i] = data[4];
        }
        
        //sort the file in alphabet order
        for (int h = 0; h < rowCount; h++) {
            for (int j = h + 1; j < rowCount; j++) {
                if (artist[h].replace("\"", "").compareToIgnoreCase(artist[j].replace("\"", "")) > 0) {
                    String temp = artist[h];
                    artist[h] = artist[j];
                    artist[j] = temp;

                    String temp2 = position[h];
                    position[h] = position[j];
                    position[j] = temp2;

                    String temp3 = trackName[h];
                    trackName[h] = trackName[j];
                    trackName[j] = temp3;

                    String temp4 = streams[h];
                    streams[h] = streams[j];
                    streams[j] = temp4;

                    String temp5 = URL[h];
                    URL[h] = URL[j];
                    URL[j] = temp5;
                }
            }
        }
        
        //print the file in alphabet order according to the artist and its elements
        for (int k = 0; k < rowCount; k++) {
            writer1.println(position[k] + "" + trackName[k] + "  " + artist[k] + "   " + streams[k] + "  " + URL[k]);

        }
        writer1.println("\n" + "Here are the artist's name and Frequency");
        
        //foud out each artist occurs how many times in the file
        Map<String, Long> freq = Stream.of(artist).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<String, Long> entry : freq.entrySet()) {
            writer1.println(entry.getKey() + " ----  " + entry.getValue() + " times ");
        }

        writer1.close();
    }

}
