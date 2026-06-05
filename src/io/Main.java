package io;

import com.model.Cell;
import com.simulation.SimulationEngine;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Starting ObjectVille... ");

        try {

            int tickCount = 10;

            if(args.length > 0){
                tickCount = Integer.parseInt(args[0]);
            }

            Cell[][] cellGrid = MapLoader.load();

            SimulationEngine engine = new SimulationEngine(cellGrid);
            engine.run(tickCount);

        } catch (NumberFormatException e) {
            System.out.println("Tick count must be an integer.");


        } catch (IOException e) {
            System.out.println("Cannot read the file.");
            System.out.println(e.getMessage());
        }
    }
}