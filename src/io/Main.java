package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[]args){


        ArrayList<String> lines = new ArrayList<>();

        System.out.println("Starting ObjectVille... ");

        try(BufferedReader br = new BufferedReader(new FileReader("map00.txt"))){

            String line;

            while((line = br.readLine()) != null){

                lines.add(line);

            }

            int rows = lines.size();
            int cols = lines.get(0).length();


            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String currentLine = lines.get(i);

                for (int j = 0; j < cols; j++) {
                    grid[i][j] = currentLine.charAt(j);

                }
            }


        }catch(IOException e){
            System.out.println("Cannot read the file.");
            System.out.println(e.getMessage());
        }
    }
}
