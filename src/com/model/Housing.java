package com.model;
//Residental zone that produces population based on resources and services.

public class Housing extends Zone{
    public Housing(int row, int col){
        super(row,col,'H');
    }
    @Override
    public void update(){

    }
    @Override
    public int calculateOutput(){
        return 0;
    }
}
