package com.model;
//Shop area. Produces lifestyle using goods and population.

public class Commercial extends Zone{
    private int availablePopulation;
    private int availableGoods;

    public Commercial(int row,int col){
        super(row,col,'C');
        this.availablePopulation=0;
        this.availableGoods = 0;
    }
    @Override
    public void update(){
        int oldLevel = level;

        if(electricity == 0 || water == 0 || internet == 0|| availablePopulation == 0 || availableGoods ==0){
            level =0;
        } else if (security && availablePopulation > 0  && availableGoods >0) {
            if(level < 3){
                level++;
            }
        } else if (security) {
            if (level <2){
                level++;
            }

        }else {
            if (level < 1){
                level++;
            }
        }
        if(level > oldLevel){
            System.out.println("Commercial at (" + row + "," + col + ") levels up from " + oldLevel + "to " + level);
        }
        else if (level < oldLevel){
            System.out.println("Commercial at (" + row + "," + col + ") levels down from " + oldLevel + "to " + level);

        }
        updateUtilityDemand();

    }
    @Override
    public int calculateOutput(){
        int m = electricity;

        if(water < m){
            m = water;
        }
        if (internet < m){
            m = internet;
        }
        int smallestResources = availablePopulation;
        if( availableGoods < smallestResources){
            smallestResources = availableGoods;
        }
        if (level == 0){
            return 0;
        }
        else if( level == 1){
            return m;
        } else if (level == 2) {
            return m *2;
        }
        else {
            return 2 * m + smallestResources;
        }
    }
    public void setAvailablePopulation(int availablePopulation){
        this.availablePopulation = availablePopulation;
    }
    public void setAvailableGoods(int availableGoods){
        this.availableGoods = availableGoods;
    }
}
