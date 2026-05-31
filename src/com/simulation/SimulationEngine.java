package com.simulation;
import com.model.Cell;
import com.model.Zone;
import com.model.ServiceBuilding;
import com.model.Housing;
import com.model.Industrial;
import com.model.Commercial;


public class SimulationEngine {
    private Cell[][] grid;
    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;


    public SimulationEngine(Cell[][] grid) {
        this.grid = grid;
        this.totalPopulation = 0;
        this.totalGoods = 0;
        this.totalLifestyle = 0;
    }

    public void run(int tickCount){
        for(int tick=1; tick<=tickCount; tick++ ){
            System.out.println("Tick " + tick);
            resetZones();
            distributeServices();
            distributeResources();
            updateZones();
            collectProduction();
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
        int workZoneCount=0;
        int commercialCount=0;
        int housingCount=0;

        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol=grid[row][col].getSymbol();

                if(symbol == 'I' || symbol == 'C'){
                    workZoneCount++;
                }
                if(symbol == 'C'){
                    commercialCount++;
                }
                if(symbol == 'H'){
                    housingCount++;
                }
            }
        }
        int populationAmount=0;
        int goodsAmount=0;
        int lifestyleAmount=0;

        if(workZoneCount>0){
            populationAmount=totalPopulation/workZoneCount;
        }

        if(commercialCount>0){
            goodsAmount=totalGoods/commercialCount;
        }

        if(housingCount>0){
            lifestyleAmount=totalLifestyle/housingCount;
        }
        for(int row=0;row<grid.length;row++){
            for(int col=0; col<grid[row].length;col++){
                char symbol= grid[row][col].getSymbol();

                if (symbol == 'I'){
                    Industrial industrial=(Industrial) grid[row][col];
                    industrial.setAvailablePopulation(populationAmount);
                    }
                if(symbol == 'C'){
                    Commercial commercial= (Commercial) grid[row][col];
                    commercial.setAvailableGoods(goodsAmount);
                    commercial.setAvailablePopulation(populationAmount);
                }
                if(symbol == 'H'){
                    Housing housing=(Housing) grid[row][col];
                    housing.setLifestyleReceived(lifestyleAmount);
                }

            }
        }



    }

    public void updateZones(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length;col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'H' || symbol == 'I' || symbol == 'C'){
                    Zone zone = (Zone)grid[row][col];
                    zone.update();
                }
            }
        }

    }

    public void collectProduction(){
        totalPopulation=0;
        totalGoods=0;
        totalLifestyle=0;

        for(int row=0; row< grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if (symbol == 'H'){
                    Housing housing=(Housing) grid[row][col];
                    totalPopulation+=housing.calculateOutput();
                }

                if(symbol == 'I'){
                    Industrial industrial=(Industrial) grid[row][col];
                    totalGoods+=industrial.calculateOutput();
                }

                if(symbol == 'C'){
                    Commercial commercial=(Commercial) grid[row][col];
                    totalLifestyle+=commercial.calculateOutput();
                }
            }
        }

    }

}
