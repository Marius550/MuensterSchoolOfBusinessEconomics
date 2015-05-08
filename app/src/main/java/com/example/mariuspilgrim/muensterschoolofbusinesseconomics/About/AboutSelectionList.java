package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.About;

/**
 * Created by mariuspilgrim on 19/03/15.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Contact.ContactActivity;
import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class AboutSelectionList extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final String[] about_list_items_array = getResources().getStringArray(R.array.about_list_items_array);
    setListAdapter(new AboutMobileArrayAdapter(this, about_list_items_array));
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + getResources().getString(R.string.list_item_selected), Toast.LENGTH_LONG).show();

    if (id == 0) {
      Intent intent = new Intent(this, AboutApp.class);
      startActivity(intent);
    } else if (id == 1) {
      Intent intent = new Intent(this, AboutWWU.class);
      startActivity(intent);
    } else {
      Intent intent = new Intent(this, ContactActivity.class);
      startActivity(intent);
    }
  }

}

  /*
  public void onCreate(Bundle icicle) {
    super.onCreate(savedInstanceState);

    List<String> about_list_items_array = Arrays.asList(getResources().getStringArray(R.array.about_list_items_array));

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, about_list_items_array);
    setListAdapter(adapter);
  }
  */