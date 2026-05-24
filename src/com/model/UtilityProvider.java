package com.model;
//Base class for all utility providers.
//Utility providers generate resources for the city.

public abstract class UtilityProvider extends Cell{
    protected int capacity;
    public UtilityProvider(int row, int col, char symbol,int capacity){
        super(row, col, symbol);
        this.capacity= capacity;
    }
    public int getCapacity(){
        return capacity;
    }
}
