package com.HS.Topcity.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.ApiModels.UserNewPassword.UpdateUserPasswordRequest;
import com.HS.Topcity.ApiModels.UserNewPassword.UpdateUserPasswordResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPassword extends AppCompatActivity {
    EditText new_pass, new_pass_confirm;
    TextView update_pass;
    LinearLayout backtotp;
    ImageView pass_eye,pass_eyehide,confirm_pass_eye,confirm_pass_eyehide;

    ApiInterface apiInterface;
    VideoView simpleVideoView;
    UpdateUserPasswordRequest updateUserPasswordRequest = new UpdateUserPasswordRequest();
//    private  static final Pattern password_pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
private  static final Pattern password_pattern = Pattern.compile("^.{8,}$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_password );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // background video funtionality
        background_video();
         // back to perivous screen
        back();
        // update password action funtionality
        update_pass_action();
        // password show and hide funtionality
        password_show_and_hide();

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
        update_pass = findViewById(R.id.update_pass);
        new_pass = findViewById(R.id.New_pass);
        new_pass_confirm = findViewById(R.id.new_confirmpassword);
        pass_eye = findViewById(R.id.newpassword_show_eye);
        pass_eyehide = findViewById(R.id.newpassword_hide_eye);
        confirm_pass_eye = findViewById( R.id.new_confirmpassword_show_eye );
        confirm_pass_eyehide =  findViewById( R.id.new_confirmpassword_hide_eye );
        backtotp = findViewById( R.id.back_to_otp );
        simpleVideoView = (VideoView) findViewById(R.id.bg_video10);
    }

    private void back(){
        backtotp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void password_show_and_hide(){
        pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );

                new_pass.setTransformationMethod( PasswordTransformationMethod.getInstance());
                pass_eyehide.setVisibility( View.GONE );
                pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                new_pass.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                pass_eye.setVisibility( View.GONE );
                pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
        confirm_pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );

                new_pass_confirm.setTransformationMethod( PasswordTransformationMethod.getInstance());
                confirm_pass_eyehide.setVisibility( View.GONE );
                confirm_pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        confirm_pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                new_pass_confirm.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                confirm_pass_eye.setVisibility( View.GONE );
                confirm_pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
    }
    private void update_pass_action(){
        update_pass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // password match and validation
                String    password = new_pass.getText().toString();
                String   confirm_password = new_pass_confirm.getText().toString();


                if( password_pattern.matcher(password ).matches()) {

                    if (password.equals( confirm_password )) {

                        // upate pass api
                        UpdatePassword();
                    }
                    else {
                        //  new_pass.setError( "Password Desn't match" );
                        new_pass_confirm.setError( "Password Desn't match" );
                    }
                }
                else{
//                    new_pass.setError( "At least 8 characters long;\n" +
//                            "One Upper, One Lower, One Number and One Special Character" );

                    new_pass.setError( "At least 8 characters long" );
                }

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
    private void UpdatePassword(){



        String token = PreferenceData.getPrefUserToken( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        // validate register api intregration



        updateUserPasswordRequest.setEmail(  getIntent().getStringExtra( "email" ) );
        updateUserPasswordRequest.setMobileNumber(   getIntent().getStringExtra( "number" ) );
        updateUserPasswordRequest.setPassword( new_pass.getText().toString() );


        Call<UpdateUserPasswordResponse> registerResponseCall = apiInterface.updateUserPasswordResponse(  updateUserPasswordRequest );
        registerResponseCall.enqueue( new Callback<UpdateUserPasswordResponse>() {
            @Override
            public void onResponse(Call<UpdateUserPasswordResponse> call, Response<UpdateUserPasswordResponse> response) {
                UpdateUserPasswordResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
//

                        Intent gosignup_form = new Intent(getApplicationContext(),Login.class);
                        startActivity( gosignup_form );
                    }


                    progress.dismiss();
                } else {
                    Toast.makeText( getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT ).show();
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<UpdateUserPasswordResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
}