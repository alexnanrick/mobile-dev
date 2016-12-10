package com.zontzor.lab11_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.content.Intent;
import android.content.res.Configuration;
import android.widget.Toast;

public class Fragment1Controller extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Context c = getActivity().getApplicationContext();
        View vw = inflater.inflate(R.layout.fragment1, container, false);
        final String[] fruits={"Apple", "Mango", "Orange", "Grapes", "Banana"};
        ListView fruitsList = (ListView) vw.findViewById(R.id.fruits_list);
        ArrayAdapter<String> arrayAdpt= new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, fruits);
        fruitsList.setAdapter(arrayAdpt);
        fruitsList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    String selection = (String)parent.getItemAtPosition(position);
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), selection,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(),  ShowItemActivity.class);
                    intent.putExtra("item", ((TextView) v).getText().toString());
                    startActivity(intent);
                }
            }
        });
        return vw;
    }
}