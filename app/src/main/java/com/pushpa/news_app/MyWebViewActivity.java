package com.pushpa.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;
    boolean loadingFinished = true;
    boolean redirect = false;
    ProgressDialog dialog;
    ProgressDialog mProgressDialog;
    ProgressBar mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        webView = findViewById(R.id.webView);
        toolbar = findViewById(R.id.toolbar1);
        dialog = new ProgressDialog(MyWebViewActivity.this);
        mprogress = findViewById(R.id.progress_load);
        dialog.setTitle("Please wait...");



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.ic_launcher_background);
        getSupportActionBar().setIcon(R.drawable.left_arrow);


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMax(100);
        mProgressDialog.show();

        toolbar.setTitle("Mero News");
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        Log.e("TAG", "onCreate: "+url );
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mprogress.setProgress(newProgress);

                mProgressDialog.setProgress(newProgress );
                if(newProgress == 100)
                {
                    mProgressDialog.dismiss();
                }
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyWebViewActivity.this,MainActivity.class));
            }
        });
    }
}