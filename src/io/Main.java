package io;

import com.model.Cell;
import com.simulation.SimulationEngine;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Starting ObjectVille...");

        try {


            if (args.length < 2) {
                System.out.println("Invalid or missing arguments!");
                return;
            }

            String mapFile = args[0];
            int tickCount = Integer.parseInt(args[1]);

            Cell[][] cellGrid = MapLoader.load(mapFile);

            SimulationEngine engine = new SimulationEngine(cellGrid);
            engine.run(tickCount);

        }

        catch (NumberFormatException e) {
            System.out.println("Tick count must be an integer.");

        }
        catch (IOException e) {
            System.out.println("Cannot read the map file.");
            System.out.println(e.getMessage());
        }
    }
}