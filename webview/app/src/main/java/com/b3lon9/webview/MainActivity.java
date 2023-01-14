package com.b3lon9.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private Handler mHandler = new Handler();

    Button loadButton;
    EditText urlInput;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView)findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebChromeClient(new WebBrowserClient());
        webview.addJavascriptInterface(new JavaScriptMethods(), "sample");
        webview.loadUrl("file:///android_asset/www/sample.html");



        loadButton = findViewById(R.id.loadButton);
        urlInput = findViewById(R.id.urlInput);

        loadButton.setOnClickListener(v -> {
            webview.loadUrl(urlInput.getText().toString());
        });
    }

    final class JavaScriptMethods {
        JavaScriptMethods() {

        }
    }

    @android.webkit.JavascriptInterface
    public void clickOnFace() {
        mHandler.post(new Runnable() {
            public void run() {
                loadButton.setText("클릭후열기");
                webview.loadUrl("javascript:changeFocus()");
            }
        });
    }

    final class WebBrowserClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//            return super.onJsAlert(view, url, message, result);
            result.confirm();
            return true;
        }
    }
}
