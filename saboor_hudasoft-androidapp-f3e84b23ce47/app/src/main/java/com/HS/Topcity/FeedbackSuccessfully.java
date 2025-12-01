package com.HS.Topcity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class FeedbackSuccessfully extends AppCompatActivity {
    LinearLayout done_btn;
    LottieAnimationView success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_feedback_successfully );
        getSupportActionBar().hide();
       // getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        ids();

        done_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        } );
        success.setProgress(0);
        success.pauseAnimation();
        success.playAnimation();

    }
    private void ids(){
        done_btn = findViewById( R.id.done_btn );
        success = findViewById( R.id.success_Shared_property );
    }
}