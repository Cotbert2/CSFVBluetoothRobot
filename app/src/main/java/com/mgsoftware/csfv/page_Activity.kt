package com.mgsoftware.csfv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class page_Activity : AppCompatActivity() {
    val URL = "https://donboscolatola.edu.ec/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_page)

        var page = findViewById<WebView>(R.id.page)
        page.webChromeClient = object: WebChromeClient(){

        }
        page.webViewClient = object: WebViewClient(){

        }
        val opciones : WebSettings = page.settings
        opciones.javaScriptEnabled = true

        page.loadUrl(URL)
    }
}