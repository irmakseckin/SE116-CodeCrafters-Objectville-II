package com.model;
//Factory area. Produces goods consume population.

public class Industrial extends Zone {
    public Industrial(int row, int col){
        super(row,col,'I');
    }
    @Override
    public void update(){

    }
    @Override
    public int calculateOutput(){
        return 0;
    }
}
