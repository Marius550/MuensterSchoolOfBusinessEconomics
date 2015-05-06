package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class LibraryMobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] library_list_items_array;

    public LibraryMobileArrayAdapter(Context context, String[] library_list_items_array) {
        super(context, R.layout.list_mobile_library, library_list_items_array);
        this.context = context;
        this.library_list_items_array = library_list_items_array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_mobile_library, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(library_list_items_array[position]);

        // Change icon based on name
        String items = library_list_items_array[position];

        if (items.equals(library_list_items_array[0])) {
            imageView.setImageResource(R.drawable.ic_action_about);
        } else if (items.equals(library_list_items_array[1])) {
            imageView.setImageResource(R.drawable.ic_action_search);
        } else if (items.equals(library_list_items_array[2])) {
            imageView.setImageResource(R.drawable.ic_action_accounts);
        } else if (items.equals(library_list_items_array[3])) {
            imageView.setImageResource(R.drawable.ic_action_help_dark);
        } else if (items.equals(library_list_items_array[4])) {
            imageView.setImageResource(R.drawable.ic_action_web_site);
        } else {
            imageView.setImageResource(R.drawable.ic_action_search);
        }

        return rowView;
    }
}