package com.simulation;
import com.model.*;

import java.util.ArrayList;


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
            distributeServices();
            distributeUtilities();
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
                            System.out.println(getZoneName(zone) + " at (" + row + "," + col + ") received security service");
                        }

                        if (service.getSymbol()== 'D'){
                            zone.setHealth(true);
                            System.out.println(getZoneName(zone) + " at (" + row + "," + col + ") received health service");
                        }

                        if(service.getSymbol()=='S'){
                            zone.setEducation(true);
                            System.out.println(getZoneName(zone) + " at (" + row + "," + col + ") received education service");
                        }
                    }
                }

            }
        }
    }



    public void distributeUtilities(){
        for(int row=0; row< grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol=grid[row][col].getSymbol();

                if(symbol == 'P' || symbol == 'W' || symbol == 'T'){
                    UtilityProvider utility = (UtilityProvider) grid[row][col];

                    giveUtility(utility);
                }
            }
        }

    }

    public void giveUtility(UtilityProvider utility){

        int utilityAmount = utility.getCapacity();
        ArrayList<Cell>list = new ArrayList<>();

        boolean[][] checked=new boolean[grid.length][grid[0].length];
        list.add(utility);
        checked[utility.getRow()][utility.getCol()]=true;

        for(int currentCell=0; currentCell<list.size() && utilityAmount>0; currentCell++){
            Cell current=list.get(currentCell);

            char symbol= current.getSymbol();

            if(symbol == 'H' || symbol == 'I' || symbol == 'C'){
                Zone currentZone =(Zone) current;
                int utilityDemand=currentZone.getUtilityDemand();
                int givenAmount;

                if(utilityAmount>=utilityDemand){
                    givenAmount=utilityDemand;
                }else{
                    givenAmount=utilityAmount;
                }
                if(utility.getSymbol()=='P'){
                    currentZone.addElectricity(givenAmount);
                    System.out.println(getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " electricity");
                }

                if(utility.getSymbol()=='T'){
                    currentZone.addInternet(givenAmount);
                    System.out.println(getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " internet");
                }

                if(utility.getSymbol()=='W'){
                    currentZone.addWater(givenAmount);
                    System.out.println(getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " water");
                }
                utilityAmount=utilityAmount-givenAmount;
            }
            int row=current.getRow();
            int col=current.getCol();

            if(row>0){
                Cell up=grid[row-1][col];
                char upSymbol= up.getSymbol();

                if (checked[row-1][col]==false && (upSymbol =='R' || upSymbol == 'H' || upSymbol == 'C' || upSymbol == 'I')) {
                    list.add(up);
                    checked[row-1][col]=true;
                }
            }

            if(row< grid.length-1){
                Cell down = grid[row+1][col];
                char downSymbol= down.getSymbol();

                if (checked[row+1][col]==false && (downSymbol =='R' || downSymbol == 'H' || downSymbol == 'C' || downSymbol == 'I')) {
                    list.add(down);
                    checked[row+1][col]=true;
                }
            }

            if(col< grid[row].length-1){
                Cell right = grid[row][col+1];
                char rightSymbol= right.getSymbol();

                if (checked[row][col+1]==false && (rightSymbol =='R' || rightSymbol == 'H' || rightSymbol == 'C' || rightSymbol == 'I')) {
                    list.add(right);
                    checked[row][col+1]=true;
                }
            }

            if(col>0){
                Cell left=grid[row][col-1];
                char leftSymbol= left.getSymbol();

                if (checked[row][col-1]==false && (leftSymbol =='R' || leftSymbol == 'H' || leftSymbol == 'C' || leftSymbol == 'I')) {
                    list.add(left);
                    checked[row][col-1]=true;
                }
            }
        }
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

                    if(populationAmount > 0){
                        System.out.println("Industrial at (" + row + "," + col + ") received " + populationAmount + " population");
                    }
                }
                if(symbol == 'C'){
                    Commercial commercial= (Commercial) grid[row][col];
                    commercial.setAvailableGoods(goodsAmount);
                    commercial.setAvailablePopulation(populationAmount);

                    if(populationAmount > 0){
                        System.out.println("Commercial at (" + row + "," + col + ") received " + populationAmount + " population");
                    }

                    if(goodsAmount > 0){
                        System.out.println("Commercial at (" + row + "," + col + ") received " + goodsAmount + " goods");
                    }
                }
                if(symbol == 'H'){
                    Housing housing=(Housing) grid[row][col];
                    housing.setLifestyleReceived(lifestyleAmount);

                    if(lifestyleAmount > 0){
                        System.out.println("House at (" + row + "," + col + ") received " + lifestyleAmount + " lifestyle");
                    }
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
                    int output = housing.calculateOutput();
                    totalPopulation += output;

                    if(output > 0){
                        System.out.println("House at (" + row + "," + col + ") generated " + output + " population");
                    }
                }

                if(symbol == 'I'){
                    Industrial industrial=(Industrial) grid[row][col];
                    int output = industrial.calculateOutput();
                    totalGoods += output;

                    if(output > 0){
                        System.out.println("Industrial at (" + row + "," + col + ") generated " + output + " goods");
                    }
                }

                if(symbol == 'C'){
                    Commercial commercial=(Commercial) grid[row][col];
                    int output = commercial.calculateOutput();
                    totalLifestyle += output;

                    if(output > 0){
                        System.out.println("Commercial at (" + row + "," + col + ") generated " + output + " lifestyle");
                    }
                }
            }
        }
    }
    public String getZoneName(Zone zone){

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