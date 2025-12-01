package com.HS.Topcity.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordRequest;
import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {

    EditText phone_number;
    TextView getoptp_btn,restemail;
    LinearLayout backtologin;
    String getnum_and_email;
    ApiInterface apiInterface;
    VideoView simpleVideoView;
    ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forget_password );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // after text auto dash funtionality
        auto_dash();
        // background video funtionality
        background_video();
        // get otp action  funtionlity
        Get_otp_send();
        // back to login funtionality
        back();
         // forget password in email
        rest_email_Action();






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a  = new Intent( getApplicationContext(),Login.class);
        startActivity( a );
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

    private void ids()
    {
        phone_number = findViewById( R.id.send_otp_phone_number );
        getoptp_btn = findViewById( R.id.optp_verify_btn );
        backtologin = findViewById( R.id.back_to_login );
        restemail = findViewById( R.id.forgetpass_with_email );
        simpleVideoView = (VideoView) findViewById(R.id.bg_video6);
    }
    private void rest_email_Action(){
        restemail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent( getApplicationContext(),ForgetPasswordEmail.class);
                startActivity( a );
            }
        } );
    }
    private void back(){
        backtologin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent( getApplicationContext(),Login.class);
                startActivity( a );
            }
        } );
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
    private void Get_otp_send(){
        getoptp_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getnum_and_email = String.valueOf("0"+phone_number.getText().toString()  );

                // forget pass api intrgration
                ForgetPassNumber();
//                Intent a  = new Intent( getApplicationContext(),ForgetpassOtpVerify.class);
//                a.putExtra( "rest_phone",getnum_and_email );
//                startActivity( a );
            }
        } );
    }
    private void ForgetPassNumber(){



        String token = PreferenceData.getPrefUserToken( getApplicationContext() );
        ProgressDialog progress = new ProgressDialog( ForgetPassword.this);

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage( "Please wait while loading..." );
        progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
         progress.show();

        // validate register api intregration


       String num =  getnum_and_email.replaceAll("-", "");
        forgetPasswordRequest.setEmail( "" );
        forgetPasswordRequest.setMobileNumber(  num);


        Call<ForgetPasswordResponse> registerResponseCall = apiInterface.forgetPasswordResponse(  forgetPasswordRequest );
        registerResponseCall.enqueue( new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                ForgetPasswordResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
//
                        String  email = "";
                        Intent a  = new Intent( getApplicationContext(),ForgetpassOtpVerify.class);
                        a.putExtra( "email1", email );
                        a.putExtra( "number1" , num );
                        startActivity( a );
                    }
                    else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ForgetPassword.this);
                        builder1.setMessage( user.message);
                        builder1.setTitle( "Alert" );
                        builder1.setCancelable(false);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // complaint with draw api call
                                        dialog.cancel();
                                    }
                                });
                        builder1.show();
                    }


                    progress.dismiss();
                } else {
                    Toast.makeText( getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT ).show();
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
    private void auto_dash(){
        phone_number.addTextChangedListener(new TextWatcher() {
            int prevL = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevL = phone_number.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if ((prevL < length) && (length == 3)) {
                    String data = phone_number.getText().toString();
                    phone_number.setText(data + "-");
                    phone_number.setSelection(length + 1);


                }

            }
        });
    }
}