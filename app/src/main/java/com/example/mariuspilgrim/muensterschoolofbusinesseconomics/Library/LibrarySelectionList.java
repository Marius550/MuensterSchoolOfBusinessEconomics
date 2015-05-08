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
import android.widget.ListView;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class LibrarySelectionList extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] library_list_items_array = getResources().getStringArray(R.array.library_list_items_array);
        setListAdapter(new LibraryMobileArrayAdapter(this, library_list_items_array));
    }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + getResources().getString(R.string.list_item_selected), Toast.LENGTH_LONG).show();

    switch (position) {
      case 0:
          WebViewLibrarySelectionUniversal.EXTRA_TARGET_URL = getResources().getString(R.string.library_opening_hours_url);
          WebViewLibrarySelectionUniversal.EXTRA_TITLE = getResources().getString(R.string.library_opening_hours);
          Intent intentHours = new Intent(this, WebViewLibrarySelectionUniversal.class);
          startActivity(intentHours);
        break;
      case 1:
        if (isNetworkAvailable() && isOnline())
        {
            WebViewCatalogSearch.EXTRA_TARGET_URL = getResources().getString(R.string.library_catalog_url);
            Intent intent = new Intent(this, WebViewCatalogSearch.class);
            startActivity(intent);
        } else {
            Log.d("Connection", "There is no Internet or Network Connection");
            Intent intent = new Intent(this, WebViewCatalogSearch.class);
            startActivity(intent);
        }
        break;
      case 2:
          WebViewLibraryLogin.EXTRA_TARGET_URL = getResources().getString(R.string.library_login_url);
          Intent intentCatalog = new Intent(this, WebViewLibraryLogin.class);
          startActivity(intentCatalog);
        break;
      case 3:
          WebViewLibrarySelectionUniversal.EXTRA_TARGET_URL = getResources().getString(R.string.library_contact_url);
          WebViewLibrarySelectionUniversal.EXTRA_TITLE = getResources().getString(R.string.library_contact);
          Intent intentContact = new Intent(this, WebViewLibrarySelectionUniversal.class);
          startActivity(intentContact);
        break;
      case 4:
          WebViewLibrarySelectionUniversal.EXTRA_TARGET_URL = getResources().getString(R.string.library_web_site_url);
          WebViewLibrarySelectionUniversal.EXTRA_TITLE = getResources().getString(R.string.library_web_site);
          Intent intentWebsite = new Intent(this, WebViewLibrarySelectionUniversal.class);
          startActivity(intentWebsite);
        break;
      default:
          WebViewCatalogSearch.EXTRA_TARGET_URL = getResources().getString(R.string.library_catalog_url);
          Intent intentCatalogDefault = new Intent(this, WebViewCatalogSearch.class);
          startActivity(intentCatalogDefault);
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
      Process p1 = java.lang.Runtime.getRuntime().exec(getResources().getString(R.string.test_is_online_url));
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
    messageBox.setNeutralButton(getResources().getString(R.string.ok), null);
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