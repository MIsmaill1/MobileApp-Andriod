package com.HS.Topcity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.Activity.SplashScreen2;

public class MainActivity extends AppCompatActivity {
    int splash = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen1 );
        getSupportActionBar().hide();
        //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

                Intent a = new Intent( getApplicationContext(),SplashScreen2.class);
                startActivity( a );
//                Intent a = new Intent( getApplicationContext(),Complaint.class);
//                startActivity( a );
            }
        }, splash);
    }
}