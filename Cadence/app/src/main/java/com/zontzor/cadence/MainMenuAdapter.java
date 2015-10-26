package com.zontzor.cadence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Zontzor on 2015-10-26.
 */
public class MainMenuAdapter extends BaseAdapter {
    private Context context;
    private final String[] menuOptions;

    public MainMenuAdapter(Context context, String[] menuOptions) {
        this.context = context;
        this.menuOptions = menuOptions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from menu_gridview.xml
            gridView = inflater.inflate(R.layout.item_menu_option, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(menuOptions[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            String option = menuOptions[position];

            if (option.equals("Profile")) {
                imageView.setImageResource(R.mipmap.ic_menu_user_profile);
            } else if (option.equals("Rides")) {
                imageView.setImageResource(R.mipmap.ic_menu_rides);
            } else if (option.equals("Goals")) {
                imageView.setImageResource(R.mipmap.ic_menu_goals);
            } else if (option.equals("Cadence")){
                imageView.setImageResource(R.mipmap.ic_menu_cadence);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return menuOptions.length;
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