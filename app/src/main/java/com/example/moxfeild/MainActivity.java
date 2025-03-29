package com.example.moxfeild;  // Make sure this matches your actual package name

import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create WebView
        webView = new WebView(this);
        setContentView(webView);

        // Configure WebView with a custom WebViewClient that suppresses errors
        webView.setWebViewClient(new WebViewClient() {
            // This method prevents error pages from being displayed
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Do nothing - this suppresses the error display

                // Optional: Silently retry loading after a brief delay
                if (request.isForMainFrame()) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            webView.reload();
                        }
                    }, 5000); // Try again after 5 seconds
                }
            }
        });

        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript for full functionality
        webSettings.setDomStorageEnabled(true); // Enable DOM storage for modern web apps
        webSettings.setLoadWithOverviewMode(true); // Load pages to fit in the screen
        webSettings.setUseWideViewPort(true); // Use wide viewport for better rendering
        webSettings.setBuiltInZoomControls(true); // Enable zoom controls
        webSettings.setDisplayZoomControls(false); // Hide zoom controls after a moment

        // Optional: Cache the website for offline use
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // Load the Moxfield website
        webView.loadUrl("https://www.moxfield.com/decks/personal");
    }

    // Override the back button to navigate within the WebView
    @Override
    @Deprecated
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack(); // Go back in WebView if possible
        } else {
            super.onBackPressed(); // Otherwise, exit the app
        }
    }
}