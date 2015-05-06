package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.About;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class AboutMobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] about_list_items_array;

    public AboutMobileArrayAdapter(Context context, String[] about_list_items_array) {
        super(context, R.layout.list_mobile_about, about_list_items_array);
        this.context = context;
        this.about_list_items_array = about_list_items_array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_mobile_about, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(about_list_items_array[position]);

        // Change icon based on name
        String items = about_list_items_array[position];

        if (items.equals(about_list_items_array[0])) {
            imageView.setImageResource(R.drawable.ic_action_about);
        } else if (items.equals(about_list_items_array[1])) {
            imageView.setImageResource(R.drawable.ic_action_about);
        } else if (items.equals(about_list_items_array[2])) {
            imageView.setImageResource(R.drawable.ic_action_email);
        } else {
            imageView.setImageResource(R.drawable.ic_action_about);
        }

        return rowView;
    }
}