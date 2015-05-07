package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Athletics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class AthleticsMobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] athletics_list_items_array;

    public AthleticsMobileArrayAdapter(Context context, String[] athletics_list_items_array) {
        super(context, R.layout.list_mobile_athletics, athletics_list_items_array);
        this.context = context;
        this.athletics_list_items_array = athletics_list_items_array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_mobile_athletics, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(athletics_list_items_array[position]);

        // Change icon based on name
        String items = athletics_list_items_array[position];

        if (items.equals(athletics_list_items_array[0])) {
            imageView.setImageResource(R.drawable.ic_action_search);
        } else if (items.equals(athletics_list_items_array[1])) {
            imageView.setImageResource(R.drawable.ic_action_web_site);
        } else if (items.equals(athletics_list_items_array[2])) {
            imageView.setImageResource(R.drawable.ic_action_email);
        } else {
            imageView.setImageResource(R.drawable.ic_action_search);
        }

        return rowView;
    }
}