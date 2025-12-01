package com.HS.Topcity.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;


public class BiometricLogin extends AppCompatActivity {

    LinearLayout backtologin;
    TextView standSignBtn,go_finger_verify;
    VideoView simpleVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_biometric_login );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // screen background video funtionality
        background_video();


        backtologin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),Login.class);
               startActivity( a );
            }
        } );
        go_finger_verify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        go_finger_verify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),FingerprintVerify.class);
                startActivity( a );
            }
        } );



    }


    private void ids(){
        backtologin = findViewById( R.id.back_to_login1 );
        standSignBtn = findViewById( R.id.standard_signin_btn );
        go_finger_verify = findViewById( R.id.go_fingerVerify_screen);
        simpleVideoView = (VideoView) findViewById(R.id.bg_video5);
    }
    private void background_video(){
        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topcitydroneview));;
        simpleVideoView.start();
        simpleVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );
    }

    @Override
    protected void onResume() {
        simpleVideoView.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        simpleVideoView.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        simpleVideoView.stopPlayback();
        super.onDestroy();
    }
}