package com.model;
//Residental zone that produces population based on resources and services.

public class Housing extends Zone{
    private int lifestyleReceived;

    public Housing(int row, int col) {
        super(row, col, 'H');
        this.lifestyleReceived = 0;
    }

    @Override
    public void update(){
        if(electricity == 0 || water == 0 || internet == 0){
            level=0;
        } else if (security && health&& education && lifestyleReceived > 0 ){
            if(level < 3){
                level++;
            }

        }else if(security && health && education){
            if(level < 2){
                level++;
            }
        }
        else {
            if(level < 1){
                level++;
            }
        }

    }
    @Override
    public int calculateOutput(){
        int m = electricity;

        if(water <m ){
            m = water;
        }
        if(internet < m){
            m = internet;
        }
        if(level == 0){
            return 0;
        } else if (level ==1) {
            return m;

        } else if (level == 2) {
            return 2*m;

        }else{
            return 2*m +lifestyleReceived;
        }
    }
    public void setLifestyleReceived(int lifestyleReceived){
        this.lifestyleReceived = lifestyleReceived;
    }
}
