package com.HS.Topcity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.R;

public class ComplaintCreateSuccess extends AppCompatActivity {
    TextView complaint_num;
    LinearLayout done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_complaint_create_success );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // id
        complaint_num =  findViewById( R.id.complain_number );
        done = findViewById( R.id.done_btn );

        // done button funtionality
        done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        // set text complaint number
        complaint_num.setText( "Your Complaint Number is" +" "+getIntent().getStringExtra( "comp_number" ) );

    }

}