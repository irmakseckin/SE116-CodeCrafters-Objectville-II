package com.simulation;
import com.model.Cell;
import com.model.Zone;

public class SimulationEngine {
    private Cell[][] grid;

    public SimulationEngine(Cell[][] grid) {
        this.grid = grid;
    }

    public void run(int tickCount){
        for(int tick=1; tick<=tickCount; tick++ ){
            System.out.println("Tick " + tick);
            resetZones();
            distributeService();
        }
    }

    public void resetZones(){

        for (int row=0; row<grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char symbol = grid[row][col].getSymbol();

                if (symbol == 'H' || symbol== 'I' || symbol=='C'){
                    Zone zone=(Zone) grid[row][col];
                    zone.resetResources();
                }
            }
        }
    }

    public void distributeService(){

    }

    public void distributeUtilities(){

    }

    public void distributeResources(){

    }

    public void updateZones(){

    }

    public void collectProduction(){

    }
    
}
