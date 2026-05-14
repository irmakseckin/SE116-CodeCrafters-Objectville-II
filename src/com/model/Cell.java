package com.model;
//Base class for all objects in the map.
//It stores row,column and symbol information.
//Position does not change after creation.

public abstract class Cell {
    protected int row;
    protected int col;
    protected char symbol;

    public Cell(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }
}
