package com.b3lon9.uri;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_naver = null;
    private Button btn_phone = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btn_naver = findViewById(R.id.btn_naver);
        btn_phone = findViewById(R.id.btn_phone);

        // listener
        btn_naver.setOnClickListener(clickListener);
        btn_phone.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = view -> {
        Intent myIntent = null;
        switch (view.getId()) {
            case R.id.btn_naver:
                myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(myIntent);
                break;

            case R.id.btn_phone:
                myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
                startActivity(myIntent);
        }
    };
}
