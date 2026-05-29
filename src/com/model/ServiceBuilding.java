package com.model;
//Base class for all service buildings.
//Service buildings provide service to zones within a radius.

public abstract class ServiceBuilding extends Cell{
    protected int radius;
    public ServiceBuilding(int row, int col,char symbol, int radius){
        super(row,col,symbol);
        this.radius = radius;
    }
    public int getRadius(){
        return radius;
    }
}


