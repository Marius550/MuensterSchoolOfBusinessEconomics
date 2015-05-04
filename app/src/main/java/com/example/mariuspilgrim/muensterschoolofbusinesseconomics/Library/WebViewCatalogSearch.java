package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class WebViewCatalogSearch extends Activity {

    private WebView mWebView = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_catalog_search);

        if (isNetworkAvailable() && isOnline()) {
            String url = "http://katalogix.uni-muenster.de/Katalog/start.do";
            mWebView = (WebView) findViewById(R.id.library_catalog_search_web_view);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url);

            //shouldOverrideUrlLoading takes care of loading new web links inside the app
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });

            Log.d("Network Connection", "Network available: "+ isNetworkAvailable());
            Log.d("Internet Connection","Internet available: "+ isOnline());
            } else {
            Intent intent = new Intent(this, LibrarySelectionList.class);
            startActivity(intent);

            Log.d("Network Connection", "Network available: " + isNetworkAvailable());
            Log.d("Internet Connection", "Internet available: " + isOnline());
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