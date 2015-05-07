package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Athletics;

/**
 * Created by mariuspilgrim on 19/03/15.
 */

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class AthleticsSelectionList extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] athletics_list_items_array = getResources().getStringArray(R.array.athletics_list_items_array);
        setListAdapter(new AthleticsMobileArrayAdapter(this, athletics_list_items_array));
    }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();

    switch (position) {
      case 0:
          WebViewAthleticsSelectionUniversal.EXTRA_TARGET_URL = "http://muenster.hochschulsport-nrw.de/angebote/aktueller_zeitraum/m.html";
          WebViewAthleticsSelectionUniversal.EXTRA_TITLE = "Sports Offer A-Z";
          Intent intentSportsOffer = new Intent(this, WebViewAthleticsSelectionUniversal.class);
          startActivity(intentSportsOffer);
        break;
      case 1:
          WebViewAthleticsSelectionUniversal.EXTRA_TARGET_URL = "https://www.uni-muenster.de/Hochschulsport/en/";
          WebViewAthleticsSelectionUniversal.EXTRA_TITLE = "Athletics Web Site";
          Intent intentWebSite = new Intent(this, WebViewAthleticsSelectionUniversal.class);
          startActivity(intentWebSite);
        break;
      case 2:
          Intent intentContact = new Intent(this, AthleticsContactActivity.class);
          startActivity(intentContact);
        break;
      default:
          WebViewAthleticsSelectionUniversal.EXTRA_TARGET_URL = "http://muenster.hochschulsport-nrw.de/angebote/aktueller_zeitraum/m_az.html";
          WebViewAthleticsSelectionUniversal.EXTRA_TITLE = "Sports Offer A-Z";
          Intent intentSportsOfferDefault = new Intent(this, WebViewAthleticsSelectionUniversal.class);
          startActivity(intentSportsOfferDefault);
          break;
    }
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
            = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  public Boolean isOnline() {
    try {
      Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
      int returnVal = p1.waitFor();
      boolean reachable = (returnVal==0);
      return reachable;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Creating exception handling box
   * @param method
   * @param message
   */
  public void messageBox(String method, String message) {
    AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
    messageBox.setTitle(method);
    messageBox.setMessage(message);
    messageBox.setCancelable(false);
    messageBox.setNeutralButton("OK", null);
    messageBox.show();
  }

}