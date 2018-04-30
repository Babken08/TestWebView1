package com.example.armen.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private RelativeLayout relativeLayoutTop;
    private RelativeLayout relativeLayoutDown;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidget();
        setWebViewParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heightTop = displayMetrics.heightPixels;

        if(NetworkUtil.getConnectivityStatus(this) != 0) {
            relativeLayoutTop.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightTop/5));
            webView.loadUrl("https://www.16personalities.com/free-personality-test");
        }else {
            setContentView(R.layout.not_connect);
        }
    }

    private void setWebViewParams() {
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }

    private void findWidget() {
        webView = findViewById(R.id.webView);
        relativeLayoutTop = findViewById(R.id.relative);
        relativeLayoutDown = findViewById(R.id.relative_down);
        progressBar = findViewById(R.id.progress);
    }
}
