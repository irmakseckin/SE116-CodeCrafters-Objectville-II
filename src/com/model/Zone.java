package com.model;
//Base class for all zone types in the city.
//Zones receive resources and services each tick and change their level based on these inputs.
//They produce outputs depending on their level.

public abstract class Zone extends Cell {
    protected int level;
    //resources
    protected int water;
    protected int electricity;
    protected int internet;
    //services
    protected boolean security;
    protected boolean health;
    protected boolean education;

    protected int utilityDemand;

    public Zone(int row, int col,char symbol){
        super(row,col,symbol);
        this.level=0;
    }
    public abstract void update();

    public abstract int calculateOutput();
    public void addElectricity(int amount){
        electricity += amount;
    }
    public void addWater(int amount){
        water+= amount;
    }
    public void addInternet(int amount){
        internet += amount;
    }
    public void setSecurity(boolean security){
        this.security= security;
    }
    public void setHealth(boolean health){
        this.health= health;
    }
    public void setEducation(boolean education){
        this.education= education;
    }
    public int getUtilityDemand(){
        return utilityDemand;
    }
    protected void updateUtilityDemand(){
        utilityDemand = calculateOutput();

        if(utilityDemand < 1){
            utilityDemand = 1;
        }
    }

    public void resetResources(){
        water=0;
        electricity =0;
        internet =0;

        security = false;
        health = false;
        education = false;
    }
}
