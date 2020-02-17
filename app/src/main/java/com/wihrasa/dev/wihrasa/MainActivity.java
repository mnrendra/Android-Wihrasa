package com.wihrasa.dev.wihrasa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected()){

            WebView myWebView = findViewById(R.id.webview);

            myWebView.loadUrl("http://178.128.88.151/index.html");
            //myWebView.loadUrl("http://192.168.43.212/index.html");
            //myWebView.loadUrl("http://192.168.43.73/index.html");
            //myWebView.loadUrl("https://localhost/");

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            myWebView.addJavascriptInterface(new JsInterface(this), "Android");

            myWebView.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    Uri myUri = Uri.parse(url);
                    Intent superIntent = new Intent(Intent.ACTION_VIEW);
                    superIntent.setData(myUri);
                    startActivity(superIntent);
                }
            });

        }else{
            Toast.makeText(this, "no internet connection", Toast.LENGTH_LONG).show();
        }

    }
}
