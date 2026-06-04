package com.simulation;

import com.model.*;

public class RadiusService {
    private Cell[][] grid;

    public RadiusService(Cell[][] grid){
        this.grid = grid;
    }

    public void distributeServices(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char serviceSymbol = grid[row][col].getSymbol();

                if(serviceSymbol == 'F' || serviceSymbol == 'D' || serviceSymbol == 'S'){
                    ServiceBuilding service = (ServiceBuilding) grid[row][col];
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

                    if(service.getRow() > row){
                        rowDistance = service.getRow() - row;
                    }else{
                        rowDistance = row - service.getRow();
                    }

                    int colDistance;

                    if(service.getCol() > col){
                        colDistance = service.getCol() - col;
                    }else{
                        colDistance = col - service.getCol();
                    }

                    int distance = rowDistance + colDistance;

                    if(distance <= service.getRadius()){
                        Zone zone = (Zone) grid[row][col];

                        if(service.getSymbol() == 'F'){
                            zone.setSecurity(true);
                            System.out.println(SimulationEngine.getZoneName(zone) + " at (" + row + "," + col + ") received security service");
                        }

                        if(service.getSymbol() == 'D'){
                            zone.setHealth(true);
                            System.out.println(SimulationEngine.getZoneName(zone) + " at (" + row + "," + col + ") received health service");
                        }

                        if(service.getSymbol() == 'S'){
                            zone.setEducation(true);
                            System.out.println(SimulationEngine.getZoneName(zone) + " at (" + row + "," + col + ") received education service");
                        }
                    }
                }
            }
        }
    }
}