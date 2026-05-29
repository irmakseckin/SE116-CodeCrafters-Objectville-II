package com.model;
//Produces electricity for the city.

public class PowerPlant extends UtilityProvider{
    public PowerPlant(int row,int col){
        super(row,col,'P',100);
    }
}
