package com.model;
//Factory area. Produces goods consume population.

public class Industrial extends Zone {
    private int availablePopulation;

    public Industrial(int row, int col){
        super(row,col,'I');
        this.availablePopulation=0;
    }
    @Override
    public void update(){
        if(electricity == 0 || water == 0 ||availablePopulation == 0){
            level=0;
        }
        else if(security && availablePopulation > 0){
            if(level < 3){
                level ++;
            }
        }
        else if(security){
            if(level < 2){
                level++;
            }
        }else {
            if (level<1){
                level++;
            }
        }
        updateUtilityDemand();

    }
    @Override
    public int calculateOutput(){
        int m = electricity;

        if(water < m){
            m = water;
        }
        if(level == 0){
            return 0;

        } else if (level == 1) {
            return m;

        }
        else if( level == 2){
            return m * 2;
        }else {
            return 2* m + availablePopulation;
        }
    }
    public void setAvailablePopulation( int availablePopulation){
        this.availablePopulation= availablePopulation;
    }
}
