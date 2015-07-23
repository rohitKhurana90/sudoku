package com.android.developers.sudoku;

import java.util.ArrayList;

/**
 * Created by Rohit Khurana on 20-07-2015.
 */
public class SudokuField {

    public int row;
    public int column;
    public int currentValue;
    public ArrayList<Integer> valuesChecked=new ArrayList<Integer>();
    public boolean isFixedValue;

}
