package com.b3lon9.doit;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private final String TAG_NAME = this.getLocalClassName().toUpperCase().toString();
    private final String TAG_NAME = "MainActivty";
    private Button btn_location = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_NAME, "[onCreate]");
        setContentView(R.layout.activity_main);

        btn_location = findViewById(R.id.startBtn);
        btn_location.setOnClickListener(v -> startLocationService());
    }

    private void startLocationService() {
        // 위치관리자 객체 참조
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        try {
            // 최근 확인했던 위치정보 확인
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastLocation != null) {
                Double latitude = lastLocation.getLatitude();       // 위도 : 남/북
                Double longitude = lastLocation.getLongitude();     // 경도 : 동/서

                Toast.makeText(this, "Last Known Location : " + "Latitude : " + latitude +" \nLongitude : " + longitude, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.getMessage();
        }

        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, gpsListener
        );

        Toast.makeText(this, "위치 확인이 시작되었습니다. 로그를 확인하세요 .", Toast.LENGTH_SHORT).show();
    }

    // 위치정보 받기
    private class GPSListener implements LocationListener {
        // 위치정보가 전달될 때 호출되는 메서드
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude : " + latitude + "\nLongitude : " + longitude;
            Log.i(TAG_NAME, "[GPSLister] : " + msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
