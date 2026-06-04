package com.simulation;

import com.model.*;
import java.util.ArrayList;

public class BFSSystem {
    private Cell[][] grid;

    public BFSSystem(Cell[][] grid){
        this.grid = grid;
    }

    public void distributeUtilities(){
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'P' || symbol == 'W' || symbol == 'T'){
                    UtilityProvider utility = (UtilityProvider) grid[row][col];
                    giveUtility(utility);
                }
            }
        }
    }

    public void giveUtility(UtilityProvider utility){
        int utilityAmount = utility.getCapacity();
        ArrayList<Cell> list = new ArrayList<>();

        boolean[][] checked = new boolean[grid.length][grid[0].length];

        list.add(utility);
        checked[utility.getRow()][utility.getCol()] = true;

        for(int currentCell=0; currentCell<list.size() && utilityAmount>0; currentCell++){
            Cell current = list.get(currentCell);
            char symbol = current.getSymbol();

            if(symbol == 'H' || symbol == 'I' || symbol == 'C'){
                Zone currentZone = (Zone) current;
                int utilityDemand = currentZone.getUtilityDemand();
                int givenAmount;

                if(utilityAmount >= utilityDemand){
                    givenAmount = utilityDemand;
                }else{
                    givenAmount = utilityAmount;
                }

                if(utility.getSymbol() == 'P'){
                    currentZone.addElectricity(givenAmount);
                    System.out.println(SimulationEngine.getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " electricity");
                }

                if(utility.getSymbol() == 'T'){
                    currentZone.addInternet(givenAmount);
                    System.out.println(SimulationEngine.getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " internet");
                }

                if(utility.getSymbol() == 'W'){
                    currentZone.addWater(givenAmount);
                    System.out.println(SimulationEngine.getZoneName(currentZone) + " at (" + currentZone.getRow() + "," + currentZone.getCol() + ") received " + givenAmount + " water");
                }

                utilityAmount = utilityAmount - givenAmount;
            }

            int row = current.getRow();
            int col = current.getCol();

            if(row > 0){
                Cell up = grid[row-1][col];
                char upSymbol = up.getSymbol();

                if(checked[row-1][col] == false && (upSymbol == 'R' || upSymbol == 'H' || upSymbol == 'C' || upSymbol == 'I')){
                    list.add(up);
                    checked[row-1][col] = true;
                }
            }

            if(row < grid.length-1){
                Cell down = grid[row+1][col];
                char downSymbol = down.getSymbol();

                if(checked[row+1][col] == false && (downSymbol == 'R' || downSymbol == 'H' || downSymbol == 'C' || downSymbol == 'I')){
                    list.add(down);
                    checked[row+1][col] = true;
                }
            }

            if(col < grid[row].length-1){
                Cell right = grid[row][col+1];
                char rightSymbol = right.getSymbol();

                if(checked[row][col+1] == false && (rightSymbol == 'R' || rightSymbol == 'H' || rightSymbol == 'C' || rightSymbol == 'I')){
                    list.add(right);
                    checked[row][col+1] = true;
                }
            }

            if(col > 0){
                Cell left = grid[row][col-1];
                char leftSymbol = left.getSymbol();

                if(checked[row][col-1] == false && (leftSymbol == 'R' || leftSymbol == 'H' || leftSymbol == 'C' || leftSymbol == 'I')){
                    list.add(left);
                    checked[row][col-1] = true;
                }
            }
        }
    }
}