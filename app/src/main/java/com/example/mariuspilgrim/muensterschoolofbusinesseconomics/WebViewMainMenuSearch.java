package com.example.mariuspilgrim.muensterschoolofbusinesseconomics;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewMainMenuSearch extends Activity {

    public static String EXTRA_TARGET_URL;
    public static String EXTRA_SEARCH_QUERY_INPUT;

    private WebView mWebView = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_main_menu_search);

        setTitle(EXTRA_SEARCH_QUERY_INPUT);

            mWebView = (WebView) findViewById(R.id.main_menu_search_web_view);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(EXTRA_TARGET_URL);
            //shouldOverrideUrlLoading takes care of loading new web links inside the app
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_refresh:
                String webUrl = mWebView.getUrl();
                WebViewMainMenuSearch.this.mWebView.loadUrl(webUrl);
                return true;
            case R.id.action_back:
                onBackPressed();
                return true;
            case R.id.action_forward:
                onForwardPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onForwardPressed() {
        if (mWebView.canGoForward()) {
            mWebView.goForward();
        }
    }

}