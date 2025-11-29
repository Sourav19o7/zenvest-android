package com.zenvest.feature_consent.presentation.screens

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsentWebViewScreen(
    url: String,
    onConsentComplete: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onError: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var currentUrl by remember { mutableStateOf(url) }
    var webView by remember { mutableStateOf<WebView?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Link Bank Account") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        webView = this
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            setSupportZoom(true)
                            builtInZoomControls = false
                        }

                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                                currentUrl = url ?: currentUrl
                            }

                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                val requestUrl = request?.url?.toString() ?: return false

                                // Check if this is a callback URL
                                if (isConsentCallback(requestUrl)) {
                                    val consentId = extractConsentId(requestUrl)
                                    if (consentId != null) {
                                        onConsentComplete(consentId)
                                        return true
                                    }
                                }

                                return false
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                isLoading = false
                                onError("Failed to load page: ${error?.description ?: "Unknown error"}")
                            }
                        }

                        loadUrl(url)
                    }
                },
                update = { webView ->
                    if (currentUrl != webView.url) {
                        webView.loadUrl(url)
                    }
                }
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView?.destroy()
        }
    }
}

/**
 * Checks if the URL is a consent callback URL
 * Setu AA redirects to configured redirect URL with consent parameters
 * Expected formats:
 * - zenvest://consent/callback?ecres=...&resdate=...&fi=...
 * - Custom redirect URL with consent parameters
 * - Success/complete URLs
 */
private fun isConsentCallback(url: String): Boolean {
    return url.contains("zenvest://consent/callback") ||
            url.contains("zenvest://consent") ||
            url.contains("/consent/success") ||
            url.contains("/consent/complete") ||
            url.contains("/redirect/success") ||
            (url.contains("ecres=") && url.contains("resdate=")) || // Setu AA response format
            (url.contains("consentId") && url.contains("status")) ||
            url.contains("fi=") // FI parameter from Setu
}

/**
 * Extracts consent ID from callback URL
 * Setu AA returns ecres (encrypted consent response) which contains the consent details
 */
private fun extractConsentId(url: String): String? {
    return try {
        val decodedUrl = URLDecoder.decode(url, "UTF-8")

        // Try to extract consentId parameter first (standard format)
        val consentIdPattern = Regex("consentId=([^&]+)")
        val consentMatch = consentIdPattern.find(decodedUrl)
        if (consentMatch != null) {
            return consentMatch.groupValues[1]
        }

        // Try to extract ecres parameter (Setu AA encrypted response)
        val ecresPattern = Regex("ecres=([^&]+)")
        val ecresMatch = ecresPattern.find(decodedUrl)
        if (ecresMatch != null) {
            // Return the ecres value - this indicates successful consent flow
            return ecresMatch.groupValues[1]
        }

        // Try to extract fi parameter (FI identifier)
        val fiPattern = Regex("fi=([^&]+)")
        val fiMatch = fiPattern.find(decodedUrl)
        if (fiMatch != null) {
            return fiMatch.groupValues[1]
        }

        // If URL contains success indicators, return a placeholder
        if (url.contains("success") || url.contains("complete")) {
            return "consent-completed"
        }

        null
    } catch (e: Exception) {
        null
    }
}
