package com.simulation;
import com.model.Cell;
import com.model.Zone;
import com.model.ServiceBuilding;


public class SimulationEngine {
    private Cell[][] grid;

    public SimulationEngine(Cell[][] grid) {
        this.grid = grid;
    }

    public void run(int tickCount){
        for(int tick=1; tick<=tickCount; tick++ ){
            System.out.println("Tick " + tick);
            resetZones();
            distributeServices();
        }
    }

    public void resetZones(){

        for (int row=0; row<grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char symbol = grid[row][col].getSymbol();

                if (symbol == 'H' || symbol == 'I' || symbol == 'C'){
                    Zone zone=(Zone) grid[row][col];
                    zone.resetResources();
                }
            }
        }
    }

    public void distributeServices(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char serviceSymbol = grid[row][col].getSymbol();

                if(serviceSymbol == 'F' || serviceSymbol == 'D' || serviceSymbol == 'S'){
                    ServiceBuilding service =(ServiceBuilding) grid[row][col];
                    giveServiceToZones(service);
                }
            }
        }

    }

    public void giveServiceToZones(ServiceBuilding service){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char zoneSymbol = grid[row][col].getSymbol();

                if(zoneSymbol == 'H' || zoneSymbol == 'I' || zoneSymbol == 'C'){
                    int rowDistance;

                    if (service.getRow() > row) {
                        rowDistance = service.getRow()-row;
                    }else{
                        rowDistance = row- service.getRow();
                    }

                    int colDistance;

                    if(service.getCol()>col){
                        colDistance =  service.getCol()-col;
                    }else{
                        colDistance = col-service.getCol();
                    }

                    int distance = rowDistance+colDistance;

                    if(distance<= service.getRadius()){
                        Zone zone =(Zone) grid[row][col];

                        if(service.getSymbol()== 'F'){
                            zone.setSecurity(true);
                        }
                        if (service.getSymbol()== 'D'){
                            zone.setHealth(true);
                        }
                        if(service.getSymbol()=='S'){
                            zone.setEducation(true);
                        }
                    }

                }
            }
        }

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
