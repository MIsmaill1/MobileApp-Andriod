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
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.ApiModels.ValidateRegister.ValidateRegisterRequest;
import com.HS.Topcity.ApiModels.ValidateRegister.ValidateRegisterResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    LinearLayout box_frame,signupform,cnic_filds,backtologin,back;
    EditText cnic;
    TextView continue_btn,login_btn;
    ApiInterface apiInterface;
    VideoView simpleVideoView;
    ValidateRegisterRequest validateRegisterRequest = new ValidateRegisterRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );
        getSupportActionBar().hide();
      ///  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        ids();
         simpleVideoView = (VideoView) findViewById(R.id.bg_video2);

        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topcitydroneview));;
        simpleVideoView.start();
        simpleVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );

        backtologin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosignup_form = new Intent( getApplicationContext(), Login.class );
                startActivity( gosignup_form );
                finish();
            }
        } );
        continue_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnic.getText().length() > 15 || cnic.getText().length() < 15){
                    cnic.setError( "This Cnic Not Valid" );
                }
                else {
                    valide_register_api();
                }
            }
            });

        cnic.addTextChangedListener(new TextWatcher() {
            int prevL = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevL = cnic.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if ((prevL < length) && (length == 5 || length == 13)) {
                    String data = cnic.getText().toString();
                    cnic.setText(data + "-");
                    cnic.setSelection(length + 1);


                }

            }
        });



    }
    private void valide_register_api(){
        String token = PreferenceData.getPrefUserToken( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(Signup.this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // validate register api intregration

        validateRegisterRequest.setCNIC( cnic.getText().toString() );
        Call<ValidateRegisterResponse> validateRegisterResponseCall = apiInterface.validateRegisterResponse( validateRegisterRequest );
        validateRegisterResponseCall.enqueue( new Callback<ValidateRegisterResponse>() {
            @Override
            public void onResponse(Call<ValidateRegisterResponse> call, Response<ValidateRegisterResponse> response) {
                ValidateRegisterResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        String strMain = user.phoneNumber.toString();
                        String[] arrSplit = strMain.split(" , ");
                        ArrayList<String> num ;
                        num = new ArrayList<>();

                        for (int i=0; i < arrSplit.length; i++)
                        {
                            num.add( arrSplit[i] );
                            System.out.println(arrSplit[i]);
                        }
                        Intent gosignup_form = new Intent( getApplicationContext(), SignupForm.class );
                        gosignup_form.putExtra( "cnic" ,user.cNIC );
                        gosignup_form.putExtra( "name" ,user.name );
                        gosignup_form.putStringArrayListExtra( "number" ,num );


                        gosignup_form.putExtra( "email" ,user.email );
                        startActivity( gosignup_form );
                    }
                    else {
                        if(user.message != null){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Signup.this);
                            builder1.setMessage(user.message.toString());
                            builder1.setTitle( "Alert" );
                            builder1.setCancelable(false);

                            builder1.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // complaint with draw api call
                                            dialog.cancel();
                                            cnic.setText( "" );
                                        }
                                    });
                            builder1.show();
                        }
//                        else {
//                            cnic.setError( "CNIC Not In TopCity1 Record" );
//                        }

                    }


                    progress.dismiss();
                } else {
                    cnic.setError( "This Cnic Not Valid" );
                    progress.dismiss();
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<ValidateRegisterResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
    @Override
    protected void onResume() {
        simpleVideoView.resume();
        cnic.setText( "" );
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void ids()
    {
        box_frame = findViewById( R.id.signup_box_frame );
        cnic = findViewById( R.id.signup_cnic );
        continue_btn = findViewById( R.id.signup_continue_btn );
        backtologin = findViewById( R.id.back_tologin );
        login_btn = findViewById( R.id.login );
        back = findViewById( R.id.back );
    }
}