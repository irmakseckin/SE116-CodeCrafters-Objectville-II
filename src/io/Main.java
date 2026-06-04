package io;

import com.model.Cell;
import com.simulation.SimulationEngine;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Starting ObjectVille... ");

        try {

            Cell[][] cellGrid = MapLoader.load();

            SimulationEngine engine = new SimulationEngine(cellGrid);
            engine.run(10);

        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            System.out.println(e.getMessage());
        }
    }
}