package com.HS.Topcity.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class ForgetPasswordEmail extends AppCompatActivity {
    EditText email;
    TextView getoptp_btn,restphonenumber;
    LinearLayout backtologin;
    String getnum_and_email;
    ApiInterface apiInterface;
    VideoView simpleVideoView;
    ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forget_password_email );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // background video funtionality
        background_video();
        // get otp verify action functionality
        Get_otp_verify_action();
        // back to login functionlity
        back();
        // forget
        forget_pass_to_number();










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
        email = findViewById( R.id.send_otp_email );
        getoptp_btn = findViewById( R.id.optp_verify_btn );
        backtologin = findViewById( R.id.back_to_restEmail );
        restphonenumber = findViewById( R.id.forgetpass_with_phonenumber );
        simpleVideoView = (VideoView) findViewById(R.id.bg_video7);
    }
    private void forget_pass_to_number(){
        restphonenumber.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent( getApplicationContext(),ForgetPassword.class);
                startActivity( a );
                finish();
            }
        } );
    }
    private void back(){
        backtologin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void Get_otp_verify_action(){
        getoptp_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnum_and_email = email.getText().toString();
                // forget pass email api
                ForgetPassEmail();
//                Intent a  = new Intent( getApplicationContext(),ForgetpassOtpVerify.class);
//                a.putExtra( "rest_email",getnum_and_email );
//                startActivity( a );
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
    private void ForgetPassEmail(){



        String token = PreferenceData.getPrefUserToken( getApplicationContext() );
        ProgressDialog progress = new ProgressDialog( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage( "Please wait while loading..." );
        progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
        // progress.show();

        // validate register api intregration



        forgetPasswordRequest.setEmail(  getnum_and_email );
        forgetPasswordRequest.setMobileNumber(   "" );


        Call<ForgetPasswordResponse> registerResponseCall = apiInterface.forgetPasswordResponse(  forgetPasswordRequest );
        registerResponseCall.enqueue( new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                ForgetPasswordResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
//
                        String  num = "";
                        Intent a  = new Intent( getApplicationContext(),ForgetpassOtpVerify.class);
                        a.putExtra( "email1", getnum_and_email  );
                        a.putExtra( "number1" , num );
                        a.putExtra( "check_count" ,"1" );
                        startActivity( a );
                    }
                    else{
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ForgetPasswordEmail.this);
                        builder1.setMessage(user.message);
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
                 //   Toast.makeText( getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT ).show();
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
}