package com.HS.Topcity.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;

public class FingerprintVerify extends AppCompatActivity {
    LinearLayout un_fingerverify_screen,fingerverify_screen,backtologin,box_frame;
    TextView go_access_app,finger_error;
    ImageView finger;
    VideoView simpleVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fingerprint_verify );
        getSupportActionBar().hide();
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        ids();
         simpleVideoView = (VideoView) findViewById(R.id.bg_video9);

        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topcitydroneview));;
        simpleVideoView.start();
        simpleVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );
        finger.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un_fingerverify_screen.setVisibility( View.GONE );
                fingerverify_screen.setVisibility( View.VISIBLE );
//                box_frame.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,400));
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 1000);
                box_frame.setLayoutParams(params);
            }
        } );
        backtologin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),Login.class);
                startActivity( a );
            }
        } );
        go_access_app.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),Login.class);
                startActivity( a );
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
    private void ids(){
        backtologin = findViewById( R.id.back_to_login1 );
        un_fingerverify_screen = findViewById( R.id.fingerVerify_empty_screen );
        fingerverify_screen = findViewById(R.id.fingerVerify_success_screen);
        finger = findViewById( R.id.fingerVerify );
        go_access_app = findViewById( R.id.login_Acces_app );
        finger_error = findViewById( R.id.fingerVerify_error );
        box_frame = findViewById( R.id.box_frame_biometric);


    }
}