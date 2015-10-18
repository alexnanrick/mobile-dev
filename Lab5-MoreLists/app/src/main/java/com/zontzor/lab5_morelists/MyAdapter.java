package com.zontzor.lab5_morelists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter{

    public MyAdapter(Context context, ArrayList countries) {
        super(context, R.layout.mylist, countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.mylist, parent, false);

        String country = (String) getItem(position);

        TextView theTextView = (TextView) theView.findViewById(R.id.itemName);

        theTextView.setText(country);
        return theView;
    }
}
