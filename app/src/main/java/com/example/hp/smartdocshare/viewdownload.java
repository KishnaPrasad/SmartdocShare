package com.example.hp.smartdocshare;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class viewdownload extends AppCompatActivity {
    Button b1,b2;
    WebView browser,wv;
    String url;
    boolean doubleBackToExitPressedOnce=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdownload);
        Intent data = getIntent();
        url=data.getStringExtra("nm");

        b1=(Button)findViewById(R.id.button8);
        b2=(Button)findViewById(R.id.button9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv=(WebView)findViewById(R.id.webkit);
                wv.setVisibility(View.VISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
        wv.getSettings().setJavaScriptEnabled(true);
        //browser.loadUrl("https://acc-smartdocshare.000webhostapp.com/uploads/OOP using JAVA.pdf");

        wv.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv = (WebView) findViewById(R.id.webkit);
                wv.setVisibility(View.VISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);

                wv.loadUrl(url);
                wv.setWebViewClient(new MyClient());
                wv.setWebChromeClient(new GoogleClient());
                WebSettings webSettings = wv.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                wv.clearCache(true);
                wv.clearHistory();
                wv.setDownloadListener(new DownloadListener() {

                    @Override


                    public void onDownloadStart(String url, String userAgent,
                                                String contentDisposition, String mimeType,
                                                long contentLength) {

                        DownloadManager.Request request = new DownloadManager.Request(
                                Uri.parse(url));


                        request.setMimeType(mimeType);


                        String cookies = CookieManager.getInstance().getCookie(url);


                        request.addRequestHeader("cookie", cookies);


                        request.addRequestHeader("User-Agent", userAgent);


                        request.setDescription("Downloading file...");


                        request.setTitle(URLUtil.guessFileName(url, contentDisposition,
                                mimeType));


                        request.allowScanningByMediaScanner();


                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalFilesDir(viewdownload.this,
                                Environment.DIRECTORY_DOWNLOADS, ".pdf");
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                        Toast.makeText(getApplicationContext(), "Downloading File",
                                Toast.LENGTH_LONG).show();
                    }
                });


                wv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wv.loadUrl("url");
                    }
                });


            }

            class MyClient extends WebViewClient {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String Url) {
                    view.loadUrl(Url);
                    return true;

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                }
            }

            class GoogleClient extends WebChromeClient {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);

                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        wv.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
