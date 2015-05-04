package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library;

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
import android.util.Log;
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
          WebViewLibrarySelectionUniversal.EXTRA_TARGET_URL = "http://www.ulb.uni-muenster.de/bibliothek/standorte/alphabet.html";
          WebViewLibrarySelectionUniversal.EXTRA_TITLE = "Opening Hours";
          Intent intentHours = new Intent(this, WebViewLibrarySelectionUniversal.class);
          startActivity(intentHours);
        break;
      case 1:
        if (isNetworkAvailable() && isOnline())
        {
            WebViewCatalogSearch.EXTRA_TARGET_URL = "http://katalogix.uni-muenster.de/Katalog/start.do";
            Intent intent = new Intent(this, WebViewCatalogSearch.class);
            startActivity(intent);
        } else {
            Log.d("Connection", "There is no Internet or Network Connection");
            Intent intent = new Intent(this, WebViewCatalogSearch.class);
            startActivity(intent);
        }
        break;
      case 2:
          WebViewLibraryLogin.EXTRA_TARGET_URL = "https://katalogix.uni-muenster.de/Katalog/loginpage.do?methodToCall=showLogin";
          Intent intent = new Intent(this, WebViewLibraryLogin.class);
          startActivity(intent);
        break;
      case 3:
        System.out.println("Ask");
        break;
      case 4:
          WebViewLibrarySelectionUniversal.EXTRA_TARGET_URL = "https://www.ulb.uni-muenster.de/";
          Intent intentWebsite = new Intent(this, WebViewLibrarySelectionUniversal.class);
          startActivity(intentWebsite);
        break;
      default:
          WebViewCatalogSearch.EXTRA_TARGET_URL = "http://katalogix.uni-muenster.de/Katalog/start.do";
          Intent intentCatalog = new Intent(this, WebViewCatalogSearch.class);
          startActivity(intentCatalog);
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
      Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
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

/*
    public boolean hasActiveInternetConnection() {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("Internet Connection", "Error checking internet connection", e);
            }
        } else {
            Log.d("Internet Connection", "No network available!");
        }
        return false;
    }

    public static boolean isOnline(Context mContext) {
        ConnectivityManager conMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conMgr.getAllNetworkInfo();
        boolean state = false;
        for (NetworkInfo nInfo : networkInfo) {
            if (nInfo.getType() == ConnectivityManager.TYPE_WIFI
                    || nInfo.getType() == ConnectivityManager.TYPE_ETHERNET
                    || nInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (nInfo.getState() == NetworkInfo.State.CONNECTED
                        || nInfo.getState() == NetworkInfo.State.CONNECTING) {
                    state = true;
                    break;
                }
            }
        }
        return state;
    }
 */