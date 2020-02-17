package com.wihrasa.dev.wihrasa;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class JsInterface {
    private Context mContext;

    /** Instantiate the interface and set the context */
    JsInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void toast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    /** Show a log from the web page */
    @JavascriptInterface
    public void log(String log) {
        Log.d("tes",log);
    }

    /** Show a setToken from the web page */
    @JavascriptInterface
    public void setToken(String token) {
        SharedPreferences sp=mContext.getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("token",token);
        Ed.apply();
    }

    /** Show a getToken from the web page */
    @JavascriptInterface
    public String getToken() {
        SharedPreferences sp1=mContext.getSharedPreferences("token", MODE_PRIVATE);
        return sp1.getString("token","ok");
    }

}
