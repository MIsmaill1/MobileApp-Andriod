package com.HS.Topcity.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.HS.Topcity.ApiModels.VerifyOTP.VerifyOtpRequest;
import com.HS.Topcity.ApiModels.VerifyOTP.VerifyOtpResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetpassOtpVerify extends AppCompatActivity {
    EditText et_otpbox1, et_otpbox2, et_otpbox3, et_otpbox4;
    TextView verifybtn, number,time,resend_otp,resend_otp_time;
    String email , Number1;
    String otpCode = "";
    ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();
    Integer otp = 0;
    boolean check = false;
    LinearLayout backtoforget;
    ApiInterface apiInterface;
    VideoView simpleVideoView;
    VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgetpass_otp_verify );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // otp Resend api
        Resend_otp();

        // background video funtionality
         background_video();
         // auto next field focus funtionality
        auto_next_field();
        // verify button funtionality
        verify_btn_action();

        // data set funtionality
        data_set();
        // back to perivous screen
        back();

        TimeResend_otp();
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
        verifybtn = findViewById(R.id.forget_optp_verify_btn);
        et_otpbox1 = findViewById(R.id.otp1);
        et_otpbox2 = findViewById(R.id.otp2);
        et_otpbox3 = findViewById(R.id.otp3);
        et_otpbox4 = findViewById(R.id.otp4);
        backtoforget = findViewById( R.id.back_to_forgetpass );
   //     time =  findViewById( R.id.optp_verify_time );
        number = findViewById( R.id.forgetpass_number );
        resend_otp = findViewById( R.id.Resend_OTP );
        simpleVideoView = (VideoView) findViewById(R.id.bg_video8);
        resend_otp_time = findViewById( R.id.Resend_OTP_Time );
    }
    private void data_set(){
        if(getIntent().getStringExtra( "number1" ) != "")
        {
            number.setText( "Enter the OTP sent to" + " " + getIntent().getStringExtra( "number1" ) );
            Number1 = getIntent().getStringExtra( "number1" );
        }

       else if(getIntent().getStringExtra( "email1" ) != "")
        {
            number.setText( "Enter the OTP sent to" + " " +  getIntent().getStringExtra( "email1" ) );
            email = getIntent().getStringExtra( "email1" );
        }
        else {
            number.setText( null );
            email = "" ;
            Number1 = "" ;
        }
    }
    private void back(){
        backtoforget.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void verify_btn_action(){
        verifybtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                optpverify();
            }
        } );
    }
    private void auto_next_field(){
        EditText[] otpTextViews = {et_otpbox1, et_otpbox2, et_otpbox3,et_otpbox4 };

        for (EditText currTextView : otpTextViews) {
            currTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    nextTextView().requestFocus();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }

                public TextView nextTextView() {
                    int i;
                    for (i = 0; i < otpTextViews.length - 1; i++) {
                        if (otpTextViews[i] == currTextView)
                            return otpTextViews[i + 1];
                    }
                    return otpTextViews[i];
                }
            });
        }
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
    private void TimeResend_otp(){
        resend_otp.setAlpha( 0.4f );
        resend_otp.setEnabled( false );
        resend_otp_time.setVisibility( View.VISIBLE );
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                resend_otp_time.setText( millisUntilFinished / 1000 + "s");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                resend_otp.setEnabled( true );
                resend_otp.setAlpha( 1f );
                resend_otp_time.setVisibility( View.GONE );
                check = false;
            }

        }.start();
    }
    private void Resend_otp(){
        resend_otp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Resend_otp_api();
            }
        } );
    }
    private void Resend_otp_api(){



        String token = PreferenceData.getPrefUserToken( getApplicationContext() );
        ProgressDialog progress = new ProgressDialog( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage( "Please wait while loading..." );
        progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
        // progress.show();

        // validate register api intregration



        if(getIntent().getStringExtra( "check_count" ) != null){
            forgetPasswordRequest.setEmail(  getIntent().getStringExtra( "email1" ) );
            forgetPasswordRequest.setMobileNumber(  getIntent().getStringExtra( "number1" ) );
        }
        else {
            forgetPasswordRequest.setEmail(  getIntent().getStringExtra( "email1" ) );
            forgetPasswordRequest.setMobileNumber( getIntent().getStringExtra( "number1" ) );
        }



        Call<ForgetPasswordResponse> registerResponseCall = apiInterface.forgetPasswordResponse(  forgetPasswordRequest );
        registerResponseCall.enqueue( new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                ForgetPasswordResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
                        TimeResend_otp();
                       // Toast.makeText( getApplicationContext(), "Resend OTP send Successfully", Toast.LENGTH_SHORT ).show();

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
    private void optpverify(){

        String Otp1 =  et_otpbox1.getText().toString();
        String Otp2 =  et_otpbox2.getText().toString();
        String Otp3 =  et_otpbox3.getText().toString();
        String Otp4 =  et_otpbox4.getText().toString();

        String OTP = Otp1 + Otp2 + Otp3 + Otp4 ;
        String token = PreferenceData.getPrefUserToken( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // validate register api intregration


        verifyOtpRequest.setOTP( OTP );
        verifyOtpRequest.setEmail( getIntent().getStringExtra( "email1" ) );
        verifyOtpRequest.setMobileNumber( getIntent().getStringExtra( "number1" )  );


        Call<VerifyOtpResponse> registerResponseCall = apiInterface.verifyOtpResponse(  verifyOtpRequest );
        registerResponseCall.enqueue( new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                VerifyOtpResponse user = response.body();


                if (user != null) {

                    if (user.Status == true) {
//
                        Intent a  = new Intent( getApplicationContext(),NewPassword.class);
                        a.putExtra( "email", getIntent().getStringExtra( "email1" ) );
                        a.putExtra( "number" , getIntent().getStringExtra( "number1" ) );
                        startActivity( a );
                        finish();
                    }


                    progress.dismiss();
                } else {
                    Toast.makeText( getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT ).show();
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
}