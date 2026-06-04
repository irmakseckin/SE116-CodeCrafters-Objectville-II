package io;

import com.model.*;
import com.exception.BadMapException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {

    public static Cell[][] load() throws IOException {

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("map00.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            int rows = lines.size();
            int cols = lines.get(0).length();

            char[][] grid = new char[rows][cols];
            Cell[][] cellGrid = new Cell[rows][cols];

            for (int i = 0; i < rows; i++) {
                String currentLine = lines.get(i);

                for (int j = 0; j < cols; j++) {
                    grid[i][j] = currentLine.charAt(j);
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    char c = grid[i][j];

                    switch (c) {

                        case 'E':
                            cellGrid[i][j] = new Empty(i, j);
                            break;

                        case 'H':
                            cellGrid[i][j] = new Housing(i, j);
                            break;

                        case 'I':
                            cellGrid[i][j] = new Industrial(i, j);
                            break;

                        case 'C':
                            cellGrid[i][j] = new Commercial(i, j);
                            break;

                        case 'P':
                            cellGrid[i][j] = new PowerPlant(i, j);
                            break;

                        case 'W':
                            cellGrid[i][j] = new WaterPump(i, j);
                            break;

                        case 'T':
                            cellGrid[i][j] = new InternetHub(i, j);
                            break;

                        case 'F':
                            cellGrid[i][j] = new PoliceStation(i, j);
                            break;

                        case 'D':
                            cellGrid[i][j] = new Hospital(i, j);
                            break;

                        case 'S':
                            cellGrid[i][j] = new School(i, j);
                            break;

                        case 'R':
                            cellGrid[i][j] = new Road(i, j);
                            break;

                        default:
                            throw new BadMapException(
                                    "Invalid map character: " + c + " at (" + i + "," + j + ")"
                            );
                    }
                }
            }

            return cellGrid;
        }
    }
}