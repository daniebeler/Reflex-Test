package com.daniebeler.reflextest;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private boolean bInBackground = false;
    private String strActiveFragment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new HomeFragment());
        ft.commit();
        strActiveFragment = "Home";

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.SMART_BANNER);

        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");       //Test        // Auch im xml ändern
        adView.setAdUnitId("ca-app-pub-9891259559985223/8270327981");       //Production


        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void loadPlay(){
        if (!bInBackground) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new PlayFragment());
            ft.commit();
            strActiveFragment = "Play";
        }
    }

    public void loadResult(){
        if (!bInBackground) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new ResultFragment());
            ft.commit();
            strActiveFragment = "Result";
        }
    }

    public void loadHome(){
        if (!bInBackground) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new HomeFragment());
            ft.commit();
            strActiveFragment = "Home";
        }
    }

    public void loadVersus(){
        if (!bInBackground) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new VersusFragment());
            ft.commit();
            strActiveFragment = "Versus";
        }
    }

    public void loadVersusResult(){
        if (!bInBackground) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new VersusResultFragment());
            ft.commit();
            strActiveFragment = "VersusResult";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        bInBackground = false;

        if(strActiveFragment.equals("Play")){
            loadHome();
        } else if (strActiveFragment.equals("Versus")) {
            loadHome();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bInBackground = true;
    }

    @Override
    public void onBackPressed() {

    }
}