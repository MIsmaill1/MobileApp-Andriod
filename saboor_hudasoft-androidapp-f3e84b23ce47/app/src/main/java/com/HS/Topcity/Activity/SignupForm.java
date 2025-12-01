package com.HS.Topcity.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.ApiModels.UserRegister.RegisterRequest;
import com.HS.Topcity.ApiModels.UserRegister.RegisterResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupForm extends AppCompatActivity {
    LinearLayout backtosignup;
    EditText name,contact,pass,confirm_pass,email,cnic;
    TextView signup_btn,password_validation,error;
    Spinner number_dropDown;
    ImageView pass_eye,pass_eyehide,confirm_pass_eye,confirm_pass_eyehide;
    ApiInterface apiInterface;
    VideoView simpleVideoView;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\*\\&\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

    RegisterRequest registerRequest = new RegisterRequest();

  //  private  static final Pattern password_pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private  static final Pattern password_pattern = Pattern.compile("^.{8,}$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup_form );
        getSupportActionBar().hide();
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        ids();
         simpleVideoView = (VideoView) findViewById(R.id.bg_video3);

        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topcitydroneview));;
        simpleVideoView.start();
        simpleVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );

        name.setText( getIntent().getStringExtra( "name" ) );
       // contact.setText( getIntent().getStringExtra( "number" ) );

        ArrayAdapter<String> vehiclebybodyadapter=new ArrayAdapter<String>(SignupForm.this,android.R.layout.simple_list_item_1,getIntent().getStringArrayListExtra( "number" ));
        vehiclebybodyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        number_dropDown.setAdapter(vehiclebybodyadapter);
        number_dropDown.setSelection(0);
      //  number_dropDown.setBackground( getDrawable( R.drawable.spinner_style ) );
        number_dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ((TextView) number_dropDown.getSelectedView()).setTextColor(getResources().getColor(R.color.white));
                ((TextView) number_dropDown.getSelectedView()).setTextSize( 12f );
                if(position > 0){
                    // get by default spinner value
                }else{
                    // show selected spinner value
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        email.setText( getIntent().getStringExtra( "email" ) );

        cnic.setText( getIntent().getStringExtra( "cnic" ) );
        backtosignup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        signup_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String    password = pass.getText().toString();
                String   confirm_password = confirm_pass.getText().toString();
                if(email.getText().toString().equals( "" )){
                    email.setError( "Please Fill this field" );
                }
                else {
                    if(!email.getText().toString().trim().matches(emailPattern)){
                        email.setError( "Invalid Email" );
                    }

                }
                if(name.getText().toString().equals( "" )){
                    name.setError( "Please Fill this field" );
                }
                if(pass.getText().toString().equals( "" )){
                    pass.setError( "Please Fill this field" );
                }
                else {


                    if( password_pattern.matcher(password ).matches()) {

                        if (!password.equals( confirm_password ) && confirm_password != "" ) {
                            confirm_pass.setError( "Password Desn't match" );
                        }


                    }
                    else{
//                    pass.setError( "At least 8 characters long;\n" +
//                            "One Upper, One Lower, One Number and One Special Character" );
                        pass.setError( "At least 8 characters long" );

                    }
                }
                if(confirm_pass.getText().toString().equals( "" )){
                    confirm_pass.setError( "Please Fill this field" );
                }
                // password match and validation

                if(email.getText().toString() != "" && email.getText().toString().trim().matches(emailPattern) &&  name.getText().toString() != "" &&
                        pass.getText().toString() != "" && confirm_pass.getText().toString() != "" && password.equals(confirm_password  ) && password_pattern.matcher(password ).matches() ) {
                    signUpSumbit();
                }
//                    if(email.getText().toString().equals( "" ) || contact.getText().toString().equals( "" ) || name.getText().toString().equals( "" )
//                        || pass.getText().toString().equals( "" ) || confirm_pass.getText().toString().equals( "" )){
//
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder( SignupForm.this);
//                    builder1.setMessage("Missig Field");
//                    builder1.setTitle( "Alert" );
//                    builder1.setCancelable(false);
//                    builder1.setPositiveButton(
//                            "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // complaint with draw api call
//                                    dialog.cancel();
//                                }
//                            });
//                    builder1.show();
//                }
//                else {
//
//                }



            }
        } );

        pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                pass.setTransformationMethod( HideReturnsTransformationMethod.getInstance());

                pass_eyehide.setVisibility( View.GONE );
                pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                pass.setTransformationMethod( PasswordTransformationMethod.getInstance());
                pass_eye.setVisibility( View.GONE );
                pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
        confirm_pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );

                confirm_pass.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                confirm_pass_eyehide.setVisibility( View.GONE );
                confirm_pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        confirm_pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );

                confirm_pass.setTransformationMethod( PasswordTransformationMethod.getInstance());
                confirm_pass_eye.setVisibility( View.GONE );
                confirm_pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
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
    public void ids()
    {
        name = findViewById( R.id.user_name_signup);
        contact = findViewById( R.id.user_contact_signup);
        pass = findViewById( R.id.user_pass_signup);
        confirm_pass = findViewById( R.id.confirmpassword_signup);
        email = findViewById( R.id.user_email_signup);
        cnic = findViewById( R.id.user_cnic_signup);
        backtosignup = findViewById( R.id.back_tosignup);
        signup_btn = findViewById( R.id.signup_btn );
        error = findViewById( R.id.validate_message );
        password_validation = findViewById( R.id.password_validation );
        confirm_pass_eye = findViewById( R.id.confirmpassword_show_eye );
        confirm_pass_eyehide = findViewById( R.id.confirmpassword_hide_eye );
        pass_eye = findViewById( R.id.password_show_eye );
        pass_eyehide = findViewById( R.id.password_hide_eye );
        number_dropDown = findViewById( R.id.phone_dropDown );




    }
    private void signUpSumbit(){
        String token = PreferenceData.getPrefUserToken( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // validate register api intregration

         registerRequest.setFullName( name.getText().toString() );
        registerRequest.setCNIC( cnic.getText().toString() );
        registerRequest.setEmail( email.getText().toString() );
        registerRequest.setMobileNumber( number_dropDown.getSelectedItem().toString() );
        registerRequest.setPassword( pass.getText().toString() );

        Call<RegisterResponse> registerResponseCall = apiInterface.registerResponse(  registerRequest );
        registerResponseCall.enqueue( new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse user = response.body();


                if (user != null) {

                    if (user.Status == true) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        Intent gosignup_form = new Intent(getApplicationContext(),Otp_verification.class);
                        gosignup_form.putExtra( "number" ,number_dropDown.getSelectedItem().toString());
                        gosignup_form.putExtra( "email" , email.getText().toString() );
                        startActivity( gosignup_form );
                    }

                    progress.dismiss();
                } else {
                    Toast.makeText( getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT ).show();
                    System.out.println( "Failed" );
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
}