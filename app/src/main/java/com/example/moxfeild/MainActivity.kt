package com.example.moxfeild

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import java.io.ByteArrayInputStream

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView

    // Ad-related domains to block
    private val adDomains = listOf(
        "doubleclick.net",
        "googleadservices.com",
        "googlesyndication.com",
        "adservice.google.com",
        "google-analytics.com",
        "googletagmanager.com",
        "adform.net",
        "adnxs.com",
        "criteo.com",
        "amazon-adsystem.com",
        "taboola.com",
        "outbrain.com"
        // Add more ad domains as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create WebView
        webView = WebView(this)
        setContentView(webView)

        // Configure WebView with ad blocking capability
        webView.webViewClient = object : WebViewClient() {
            // Block ads by intercepting requests
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                val url = request?.url?.toString() ?: ""

                // Check if the URL contains any ad domain
                if (adDomains.any { url.contains(it) }) {
                    // Return empty response to block the ad
                    return WebResourceResponse(
                        "text/plain",
                        "utf-8",
                        ByteArrayInputStream("".toByteArray())
                    )
                }

                // For non-ad content, let the request proceed normally
                return super.shouldInterceptRequest(view, request)
            }

            // This method prevents error pages from being displayed
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                // Do nothing - this suppresses the error display

                // Optional: Silently retry loading after a brief delay
                if (request?.isForMainFrame == true) {
                    view?.postDelayed({
                        webView.reload()
                    }, 5000) // Try again after 5 seconds
                }
            }
        }

        // Configure WebView settings
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true // Enable JavaScript for full functionality
        webSettings.domStorageEnabled = true // Enable DOM storage for modern web apps
        webSettings.loadWithOverviewMode = true // Load pages to fit in the screen
        webSettings.useWideViewPort = true // Use wide viewport for better rendering
        webSettings.builtInZoomControls = true // Enable zoom controls
        webSettings.displayZoomControls = false // Hide zoom controls after a moment

        // Optional: Cache the website for offline use
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        // Load the Moxfield website
        webView.loadUrl("https://moxfield.com/decks/personal")
    }

    // Override the back button to navigate within the WebView
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack() // Go back in WebView if possible
        } else {
            super.onBackPressed() // Otherwise, exit the app
        }
    }
}