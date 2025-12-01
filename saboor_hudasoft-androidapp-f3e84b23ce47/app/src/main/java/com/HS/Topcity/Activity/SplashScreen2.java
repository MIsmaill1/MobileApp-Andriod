package com.HS.Topcity.Activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;

public class SplashScreen2 extends AppCompatActivity {
    private int splash = 8210;
    VideoView   simpleVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen2 );
        getSupportActionBar().hide();
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        simpleVideoView = (VideoView) findViewById(R.id.bg_video_Splash);

        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video_for_small_screen));
        simpleVideoView.start();
        simpleVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );

//        simpleVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int i, int i1) {
//                Log.e(TAG,String.format("Error: What: %d, Extra: %d",i,i1));
//                return false;
//            }
//        });
//        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mp.start();
//            }
//        });
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                    Intent LoginIntent = new Intent( SplashScreen2.this, Login.class);
                    startActivity(LoginIntent);
                    finish();
//                if(PreferenceData.getUserLoggedInStatus(SplashScreen2.this))
//                {
//                    Intent LoginIntent1 = new Intent( SplashScreen2.this, Dashboard.class);
//                    startActivity(LoginIntent1);
//                    finish();
//                }
//                else{
//                    Intent LoginIntent1 = new Intent( SplashScreen2.this, Login.class);
//                    startActivity(LoginIntent1);
//                    finish();
//                }

            }
        }, splash);
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

    }
}