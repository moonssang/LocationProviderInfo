package com.smuexample.locationproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mtxtInfo;
    LocationManager locManager;
    LocationProvider locProvider;
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

        for(String locProviderName : locProviderList){
            mtxtInfo.append("Loc.Provider : " + locProviderName + "\n");
            mtxtInfo.append("Enabled : " +locManager.isProviderEnabled(locProviderName) + "\n\n");
            switch (locProviderName){
                    case "gps" :
                    locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);
                    break;
                    case "network" :
                        locProvider = locManager.getProvider(LocationManager.NETWORK_PROVIDER);
                    break;
                    case "passive" :
                    locProvider = locManager.getProvider(LocationManager.PASSIVE_PROVIDER);
                    break;
            }
            printInfo(locProvider);
        }
    }
    private void printInfo(LocationProvider locationProvider) {
        mtxtInfo.append("이름: " + locationProvider.getName() + "\n");
        mtxtInfo.append("현재이용가능여부: " +
                locManager.isProviderEnabled(locationProvider.getName()) + "\n");
        mtxtInfo.append("위성필요여부: " + locationProvider.requiresSatellite() + "\n");
        mtxtInfo.append("인터넷접속필요여부: " + locationProvider.requiresNetwork() + "\n");
        mtxtInfo.append("기지국필요여부: " + locationProvider.requiresCell() + "\n");

        mtxtInfo.append("고도정보지원여부: " + locationProvider.supportsAltitude() + "\n");
        mtxtInfo.append("방향정보지원여부: " + locationProvider.supportsBearing() + "\n");
        mtxtInfo.append("속도정보지원여부: " + locationProvider.supportsSpeed() + "\n");


        String accuracy = "";
        switch(locationProvider.getAccuracy()) {
            //2.3부터 사용
            case Criteria.ACCURACY_LOW:    accuracy="500m 이상 오차"; break;
            case Criteria.ACCURACY_MEDIUM: accuracy="100~500m 오차"; break;
            case Criteria.ACCURACY_HIGH:   accuracy="0~100m 오차"; break;
        }
        mtxtInfo.append("정확성정도: " + accuracy + "\n");

        String power = "";
        switch(locationProvider.getPowerRequirement()) {
            case Criteria.POWER_LOW:    power="적게 사용"; break;
            case Criteria.POWER_MEDIUM: power="중간 사용"; break;
            case Criteria.POWER_HIGH:   power="많이 사용"; break;
        }
        mtxtInfo.append("전원사용정도: " + power + "\n");

        mtxtInfo.append("요금지불여부: " + locationProvider.hasMonetaryCost() + "\n");
        mtxtInfo.append("\n");
    }
}