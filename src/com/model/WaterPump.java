package com.model;
//Produces water for the city.

public class WaterPump extends UtilityProvider{
    public WaterPump(int row,int col){
        super(row,col,'W',100);
    }
}
