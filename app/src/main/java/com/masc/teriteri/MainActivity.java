package com.masc.teriteri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends Activity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        // 启用 JavaScript（如果需要）
        webView.getSettings().setJavaScriptEnabled(true);

        // 设置 WebViewClient 以在 WebView 中处理页面导航
        webView.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra("url");

        // 加载网页
//        webView.loadUrl("http://192.168.0.105:3000/");
        webView.loadUrl(url);

//        webView.loadUrl("https://www.pdai.tech/md/interview/x-interview.html");
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true); //是否可缩放
        webView.getSettings().setLoadWithOverviewMode(true);//加载自适应屏幕

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}