package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library;

/**
 * Created by mariuspilgrim on 19/03/15.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

import java.util.Arrays;
import java.util.List;

public class LibrarySelectionList extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    List<String> about_list_items_array = Arrays.asList(getResources().getStringArray(R.array.library_list_items_array));

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, about_list_items_array);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();

    switch (position) {
      case 0:
        System.out.println("Hours");
        break;
      case 1:
        Intent intent = new Intent(this, WebViewCatalogSearch.class);
        startActivity(intent);
        break;
      case 2:
        System.out.println("Ask");
        break;
      case 4:
        System.out.println("Web Site");
        break;
      default:
        System.out.println("Search");
        break;
    }
  }

}