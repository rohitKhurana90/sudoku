package com.android.developers.sudoku;

/**
 * Created by Rohit Khurana on 19-07-2015.
 */


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;


public class AdapterClass extends BaseAdapter {
    private Context context;
    private final int[] values;

    public AdapterClass(Context context, int[] values) {
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.textview_layout, null);

            // set value into textview
            EditText textView = (EditText) gridView
                    .findViewById(R.id.grid_item_label);


            if(values[position]!=0){
                textView.setText(values[position]+"");
                textView.setEnabled(false);
                textView.setBackgroundColor(Color.GRAY);
                textView.setTextColor(Color.BLUE);
            }else{
                textView.setText("");
            }


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}