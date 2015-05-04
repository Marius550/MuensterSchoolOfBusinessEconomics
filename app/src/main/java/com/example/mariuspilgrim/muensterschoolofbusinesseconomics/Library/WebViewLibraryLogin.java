package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class WebViewLibraryLogin extends Activity {

    private WebView mWebView = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_library_login);

            String url = "https://katalogix.uni-muenster.de/Katalog/loginpage.do?methodToCall=showLogin";
            mWebView = (WebView) findViewById(R.id.library_login_web_view);
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
    }

}
