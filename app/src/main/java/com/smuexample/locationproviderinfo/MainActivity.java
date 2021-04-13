package com.smuexample.locationproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mtxtInfo;
    LocationManager locManager;
    //String s;
    List<String> locProviderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtxtInfo = findViewById(R.id.txtInfo);

        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locProviderList = locManager.getAllProviders();
        mtxtInfo.setText("");

        for(String locProvider : locProviderList){
            mtxtInfo.append("Loc.Provider : " + locProvider + "\n");
            mtxtInfo.append("Enabled : " +locManager.isProviderEnabled(locProvider) + "\n\n");
        }
    }
}