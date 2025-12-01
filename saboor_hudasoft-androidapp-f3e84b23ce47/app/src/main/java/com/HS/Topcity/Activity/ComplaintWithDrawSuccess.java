package com.HS.Topcity.Activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;

public class ComplaintWithDrawSuccess extends AppCompatActivity {
    private int splash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_complaint_with_draw_success );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        // handle  screen time
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, splash);
    }
}