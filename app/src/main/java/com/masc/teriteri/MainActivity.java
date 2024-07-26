package com.masc.teriteri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
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
//        webView.getSettings().setAllowFullscreen(true);//加载自适应屏幕

//        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            Log.d("WebView", "Fullscreen requested");

//            int originalUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            // 设置为横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            // 处理全屏显示的逻辑，比如隐藏状态栏、导航栏等
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }

        @Override
        public void onHideCustomView() {
            Log.d("WebView", "Fullscreen exited");
            // 恢复为之前的屏幕方向
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            // 处理退出全屏的逻辑，恢复状态栏、导航栏等
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }
}