package com.simulation;

import com.model.*;

public class ResourceSystem {
    private Cell[][] grid;
    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;

    public ResourceSystem(Cell[][] grid){
        this.grid = grid;
        this.totalPopulation = 0;
        this.totalGoods = 0;
        this.totalLifestyle = 0;
    }

    public void distributeResources(){
        int workZoneCount = 0;
        int commercialCount = 0;
        int housingCount = 0;

        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

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

        int populationAmount = 0;
        int goodsAmount = 0;
        int lifestyleAmount = 0;

        if(workZoneCount > 0){
            populationAmount = totalPopulation / workZoneCount;
        }

        if(commercialCount > 0){
            goodsAmount = totalGoods / commercialCount;
        }

        if(housingCount > 0){
            lifestyleAmount = totalLifestyle / housingCount;
        }

        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'I'){
                    Industrial industrial = (Industrial) grid[row][col];
                    industrial.setAvailablePopulation(populationAmount);

                    if(populationAmount > 0){
                        System.out.println("Industrial at (" + row + "," + col + ") received " + populationAmount + " population");
                    }
                }

                if(symbol == 'C'){
                    Commercial commercial = (Commercial) grid[row][col];
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
                    Housing housing = (Housing) grid[row][col];
                    housing.setLifestyleReceived(lifestyleAmount);

                    if(lifestyleAmount > 0){
                        System.out.println("House at (" + row + "," + col + ") received " + lifestyleAmount + " lifestyle");
                    }
                }
            }
        }
    }

    public void collectProduction(){
        totalPopulation = 0;
        totalGoods = 0;
        totalLifestyle = 0;

        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                char symbol = grid[row][col].getSymbol();

                if(symbol == 'H'){
                    Housing housing = (Housing) grid[row][col];
                    int output = housing.calculateOutput();
                    totalPopulation += output;

                    if(output > 0){
                        System.out.println("House at (" + row + "," + col + ") generated " + output + " population");
                    }
                }

                if(symbol == 'I'){
                    Industrial industrial = (Industrial) grid[row][col];
                    int output = industrial.calculateOutput();
                    totalGoods += output;

                    if(output > 0){
                        System.out.println("Industrial at (" + row + "," + col + ") generated " + output + " goods");
                    }
                }

                if(symbol == 'C'){
                    Commercial commercial = (Commercial) grid[row][col];
                    int output = commercial.calculateOutput();
                    totalLifestyle += output;

                    if(output > 0){
                        System.out.println("Commercial at (" + row + "," + col + ") generated " + output + " lifestyle");
                    }
                }
            }
        }
    }
}