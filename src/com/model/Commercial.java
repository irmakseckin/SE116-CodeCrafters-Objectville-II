package com.model;
//Shop area. Produces lifestyle using goods and population.

public class Commercial extends Zone{
    public Commercial(int row,int col){
        super(row,col,'C');
    }
    @Override
    public void update(){

    }
    @Override
    public int calculateOutput(){
        return 0;
    }
}
