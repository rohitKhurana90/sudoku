package com.android.developers.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private GridView gridView;
    private Button button;
    static final int[][] sudokuValues = {{0,0,0,3,4,7,0,0,0},
            {9,5,0,0,0,1,3,0,4},
            {0,0,4,0,0,0,8,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,2,0,8,5,6,0,4,0},
            {0,0,0,0,3,0,0,0,0},
            {0,0,8,0,0,0,5,0,0},
            {4,0,5,1,0,0,0,7,2},
            {7,0,0,5,9,2,0,0,0}

    };


    static final int[] numbers = new int[81] ;
    private SudokuField[][] fields = new SudokuField[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.buttonSolve);
        gridView = (GridView) findViewById(R.id.gridView1);

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){

                fields[i][j] = new SudokuField();
                fields[i][j].currentValue = sudokuValues[i][j];
                if(sudokuValues[i][j]==0){
                    fields[i][j].isFixedValue = false;
                }else{
                    fields[i][j].isFixedValue = true;
                }
            }

        int i=0;
        for(int[] temp:sudokuValues ){
            for(int val:temp){

                numbers[i++] = val;
            }

        }

        AdapterClass adapter = new AdapterClass(MainActivity.this,numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, j = 0;
                while (true) {
                    boolean validationFailed=false;
                    if (fields[i][j].isFixedValue == false) {

                        for (int k = 1; k <= 9; k++) {
                            if ( !fields[i][j].valuesChecked.contains(k)) {
                                if (validate(k, i, j, fields)) {

                                    fields[i][j].currentValue = k;

                                    fields[i][j].valuesChecked.add(k);


                                    break;
                                } else {
                                    if (k == 9) {
                                        validationFailed = true;

                                        for(int l=0;l<81;++l) {

                                            if (j > 0) {
                                                j--;
                                            } else if (i > 0) {
                                                i--;
                                                j = 8;
                                            } else {
                                                Log.i("MainActivity", "No Soln");
                                            }

                                            if(fields[i][j].isFixedValue){
                                                continue;
                                            }else{
                                                break;
                                            }
                                        }

                                    }

                                }
                            }else {
                                if (k == 9) {
                                    validationFailed = true;

                                    for (int l = 0; l < 81; ++l) {

                                        if (j > 0) {
                                            j--;
                                        } else if (i > 0) {
                                            i--;
                                            j = 8;
                                        } else {
                                            Log.i("MainActivity", "No Soln");
                                        }

                                        if (fields[i][j].isFixedValue) {
                                            continue;
                                        } else {
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                    }
                    if(!validationFailed) {
                        if (j < 8) {
                            j++;
                        } else if (i < 8) {
                            j = 0;
                            i++;
                        } else {

                            Log.i("MainActivity", "Solved");
                            int count = 0;
                            for (int row = 0; row < 9; row++)
                                for (int column = 0; column < 9; ++column) {
                                    Log.i("MainActivity", numbers[count] + "");
                                    numbers[count++] = fields[i][j].currentValue;


                                }

                            ((AdapterClass) gridView.getAdapter()).notifyDataSetChanged();
                            break;
                        }
                    }

                }

            }
        });
    }



    private boolean validate(int value, int row, int column, SudokuField[][] sudokuFields){

        for(int i=0;i<9;i++){
            if(i != row)
            if(value == sudokuFields[i][column].currentValue)
                return false;
        }

        for(int i=0;i<9;i++) {
            if (i != column)
                if (value == sudokuFields[row][i].currentValue)
                    return false;
        }

        for (int i=0; i< 3;i++)
            for(int j=0; j< 3;j++) {

                if(i==row%3 && j == column%3)
                    continue;

                if(value == sudokuFields[row-row%3+i][column-column%3 +j].currentValue)
                    return false;



            }

        return true;
    }


    private void solve(){


    }

}
