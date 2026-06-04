package com.simulation;

import com.model.*;

public class SimulationEngine {
    private Cell[][] grid;
    private BFSSystem bfsSystem;
    private ResourceSystem resourceSystem;
    private RadiusService radiusService;

    public SimulationEngine(Cell[][] grid) {
        this.grid = grid;
        this.bfsSystem = new BFSSystem(grid);
        this.resourceSystem = new ResourceSystem(grid);
        this.radiusService = new RadiusService(grid);
    }

    public void run(int tickCount){
        if(tickCount < 0){
            System.out.println("Error: Tick count cannot be negative.");
            return;
        }
        if(tickCount == 0){
            System.out.println("Error: Tick count must be greater than zero.");
            return;
        }
        for(int tick=1; tick<=tickCount; tick++ ){
            System.out.println("Tick " + tick);

            resetZones();
            radiusService.distributeServices();
            bfsSystem.distributeUtilities();
            resourceSystem.distributeResources();
            updateZones();
            resourceSystem.collectProduction();

            System.out.println();
        }
    }

    public void resetZones(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'H' || symbol == 'I' || symbol == 'C'){
                    Zone zone = (Zone) grid[row][col];
                    zone.resetResources();
                }
            }
        }
    }

    public void updateZones(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'H' || symbol == 'I' || symbol == 'C'){
                    Zone zone = (Zone) grid[row][col];
                    zone.update();
                }
            }
        }
    }

    public static String getZoneName(Zone zone){
        if(zone.getSymbol() == 'H'){
            return "House";
        }
        if(zone.getSymbol() == 'I'){
            return "Industrial";
        }
        if(zone.getSymbol() == 'C'){
            return "Commercial";
        }
        return "Zone";
    }
}