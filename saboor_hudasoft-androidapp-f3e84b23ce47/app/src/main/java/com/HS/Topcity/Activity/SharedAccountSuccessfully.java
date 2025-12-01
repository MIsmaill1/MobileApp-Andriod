package com.HS.Topcity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;
import com.airbnb.lottie.LottieAnimationView;

public class SharedAccountSuccessfully extends AppCompatActivity {

    LinearLayout done_btn;
    LottieAnimationView success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shared_account_successfully );
        getSupportActionBar().hide();
  //      getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        ids();

        done_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), SharedAccountUserList.class);
                a.putExtra( "id_property",getIntent().getStringExtra( "id" ) );
                startActivity( a );
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