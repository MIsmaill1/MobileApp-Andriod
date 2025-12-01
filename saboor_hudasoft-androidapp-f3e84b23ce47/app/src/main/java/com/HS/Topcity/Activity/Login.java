package com.HS.Topcity.Activity;

import static android.content.ContentValues.TAG;

import static com.HS.Topcity.Activity.UserProfile.name_get;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.HS.Topcity.Activity.Guest.ui.GuestDashboard;
import com.HS.Topcity.ApiModels.FingerprintLogin.FingerprintLoginRequest;
import com.HS.Topcity.ApiModels.FingerprintLogin.FingerprintLoginResponse;
import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordRequest;
import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordResponse;
import com.HS.Topcity.ApiModels.Login.LoginRequest;
import com.HS.Topcity.ApiModels.Login.LoginResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
TextView signup_btn,login_btn,forget_pass;
EditText emaail_login,password_login;
LinearLayout biometric_screen,guests;
String username;
LinearLayout pass_eye,pass_eyehide;
    private static final int PERMISSION_REQUEST_CODE = 200;
LoginRequest loginRequestModel = new LoginRequest();
    ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();
FingerprintLoginRequest fingerprintLoginRequest = new FingerprintLoginRequest();
    VideoView simpleVideoView;
    ApiInterface apiInterface;
    GifImageView logo_gif;
    String num1;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\*\\&\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    String t;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();

        // password show and hide funcationality
        pass_show_functionality();

        // background video
        background_video();
        // signup btton funtionality
        signup_btn_action();
        // forget password btton funtionality
        forget_pass_btn_action();

        // login button action funtionality
        Login_btn_Action();
        // internet permission



        // guest user
        Guest();
        //biometric screen funtionality
        Biometric_screen_funtionality();
        //biometric screen open
        Biometric_screen();

        PreferenceData.setIsMangement(getApplicationContext(), false);
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.INTERNET))
            permissionsNeeded.add("android.permission.INTERNET");
        if (!addPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
            permissionsNeeded.add("android.permission.READ_PHONE_STATE");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("android.permission.READ_EXTERNAL_STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("android.permission.CAMERA");
        if (!addPermission(permissionsList, Manifest.permission.CALL_PHONE))
            permissionsNeeded.add("android.permission.CALL_PHONE");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("android.permission.ACCESS_COARSE_LOCATION");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("android.permission.ACCESS_FINE_LOCATION");

        ActivityCompat.requestPermissions(Login.this,
                permissionsList.toArray(new String[permissionsList.size()]),
                300);


        name_get = null;
        PreferenceData.setPrefUserName( getApplicationContext(),"" );
        PreferenceData.setIsTenat(getApplicationContext(),false );
        PreferenceData.setIsFamily(getApplicationContext(),false  );

    }
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(Login.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(Login.this, permission))
                return false;
        }
        return true;
    }
    private void Biometric_screen_funtionality(){

    }

    private void Biometric_screen(){
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                fingerprint();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric For TopCity-1")
                .setSubtitle("Identify Yorself!")
                .setNegativeButtonText("cancel")
                .build();
        // open dialog fingerprint
        biometric_screen.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
//                Intent a = new Intent(getApplicationContext(),BiometricLogin.class);
//                startActivity( a );


                // Prompt appears when user clicks "Log in".
                // Consider integrating with the keystore to unlock cryptographic operations,
                // if needed by your app.



                if (PreferenceData.getPrefUserFigerEnableStatus(getApplicationContext()) == true)
                {
                    biometricPrompt.authenticate(promptInfo);
                }
                else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                    builder1.setMessage("You need to enable finger lock from your profile settings");
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


//                BiometricManager biometricManager = BiometricManager.from(getApplicationContext());
//                switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
//                    case BiometricManager.BIOMETRIC_SUCCESS:
//                        Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
//                        break;
//                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//                        Log.e("MY_APP_TAG", "No biometric features available on this device.");
//                        break;
//                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//                        Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
//                        break;
//                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//                        // Prompts the user to create credentials that your app accepts.
//                        final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
//                        enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//                                BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
//                        startActivityForResult(enrollIntent,PERMISSION_REQUEST_CODE);
//                        break;
//                }

            }
        } );

    }
    private void Login_btn_Action(){
        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);

                    }

                    else {
                        loginButtonClick( v );
                    }
                }
                else {
                    Toast.makeText( getApplicationContext(), "please Internet Permission Granted", Toast.LENGTH_SHORT ).show();
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
    private void forget_pass_btn_action(){
        forget_pass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity( a );
            }
        } );
    }
    private void signup_btn_action(){
        signup_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosignup = new Intent(getApplicationContext(),Signup.class);
                startActivity( gosignup );
            }
        } );
    }

    private void pass_show_functionality(){
        pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                password_login.setTransformationMethod( HideReturnsTransformationMethod.getInstance());

                pass_eyehide.setVisibility( View.GONE );
                pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                password_login.setTransformationMethod( PasswordTransformationMethod.getInstance());
                pass_eye.setVisibility( View.GONE );
                pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
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

    private void Guest(){
        guests.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), GuestDashboard.class);
                a.putExtra( "Guest","Guest" );
                startActivity( a );
            }
        } );

    }
    private void loginButtonClick(View view){

        String email  = emaail_login.getText().toString();
        String pass = password_login.getText().toString();
        String uniqueID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        apiInterface = ApiUtils.postSignUpService();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String   refreshedToken = task.getResult();

                         t =  refreshedToken;
                        loginRequestModel.setDeviceFCMToken(t  );
                        loginRequestModel.setEmail( email );
                        loginRequestModel.setPassword( pass );

                        loginRequestModel.setDeviceId(  uniqueID);
                        loginRequestModel.setIOSOrAndroid(  false);


                        ProgressDialog progress = new ProgressDialog(Login.this);
                        progress.setMessage("Please wait while loading...");
                        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                        progress.show();
                        Call<LoginResponse> signup = apiInterface.LOGIN_RESPONSE_CALL(loginRequestModel);
                        signup.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                LoginResponse user = response.body();
                                if(user != null && user.status != false)
                                {
                                    PreferenceData.setUserLoggedInStatus(getApplicationContext(),true);
                                    PreferenceData.setPrefUserName(getApplicationContext(), user.userInfoModel.FullName);
                                    PreferenceData.setPrefUserToken(getApplicationContext(), user.token);
                                    PreferenceData.setIsMangement(getApplicationContext(), user.getUserInfoModel().isManagement() );



                                    if(user.userInfoModel.isOTPVerifyed() == false){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder( Login.this);
                                        builder1.setMessage("OTP is not verified");
                                        builder1.setTitle( "Alert" );
                                        builder1.setCancelable(false);
                                        builder1.setPositiveButton(
                                                "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // complaint with draw api call
                                                        dialog.cancel();
                                                        num1 =  user.userInfoModel.ContactNumber.replaceAll("-", "");
//                                   forgetPasswordRequest.setEmail( user.userInfoModel.getEmail() );
//                                   forgetPasswordRequest.setMobileNumber(  num1);
                                                        Intent a  = new Intent( getApplicationContext(),Otp_verification.class);
                                                        a.putExtra( "email", emaail_login.getText().toString() );
                                                        a.putExtra( "number" , num1 );
                                                        startActivity( a );


                                                    }
                                                });
                                        builder1.show();
                                    }
                                    else {
                                        signInFunction(view, user);
                                    }
                                    progress.dismiss();
                                }
                                else{
                                    progress.dismiss();

                                    if(password_login.getText().length() < 8){
                                        if(emaail_login.getText().toString().equals( "" )){
                                            emaail_login.setError( "Please enter the email" );
                                        }
                                       else if(!emaail_login.getText().toString().trim().matches(emailPattern  ) &&  emaail_login.getText().toString() != "" ){
                                            emaail_login.setError( "Please enter the Correct email" );
                                        }


                                        if(password_login.getText().toString().equals( "" )){
                                            password_login.setError( "Please enter the password" );
                                        }
                                        else {
                                            password_login.setError( "Please enter the correct password" );
                                        }



                                    }else {
                                        if(emaail_login.getText().toString().equals( "" )){
                                            emaail_login.setError( "Please enter the email" );
                                        }
                                        else {
                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);


                                            if(user.getMessage() == null || user == null){
                                                builder1.setMessage("email and password not correct");

                                            }
                                            else {
                                                builder1.setMessage(user.Message);
                                            }



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

                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                progress.dismiss();
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                                builder1.setMessage("Some error occurred. Please contact your system administrator.");
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

                                System.out.println("Failed : " + t.getMessage());
                                call.cancel();
                            }
                        });
                    }
                });






    }

        private void otp_send(){




            String token = PreferenceData.getPrefUserToken( getApplicationContext() );
            ProgressDialog progress = new ProgressDialog( Login.this);

            apiInterface = ApiUtils.postSignUpService();

            progress.setMessage( "Please wait while loading..." );
            progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
            progress.show();

            // validate register api intregration





            Call<ForgetPasswordResponse> registerResponseCall = apiInterface.forgetPasswordResponse(  forgetPasswordRequest );
            registerResponseCall.enqueue( new Callback<ForgetPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                    ForgetPasswordResponse user = response.body();


                    if (user != null) {

                        if (user.status == true) {
//
                            Intent a  = new Intent( getApplicationContext(),Otp_verification.class);
                            a.putExtra( "email", emaail_login.getText().toString() );
                            a.putExtra( "number" , num1 );
                            startActivity( a );
                        }
                        else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
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


    private void fingerprint(){


        String uniqueID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        apiInterface = ApiUtils.postSignUpService();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String   refreshedToken = task.getResult();

                        String t =  refreshedToken;
                        fingerprintLoginRequest.setDeviceFCMToken(t  );
                        fingerprintLoginRequest.setEmail( PreferenceData.getLoggedInEmailUser(getApplicationContext()) );
                        fingerprintLoginRequest.setcNIC( PreferenceData.getPrefLoggedinUserCnic(getApplicationContext()) );

                        fingerprintLoginRequest.setDeviceId(  uniqueID);
                        fingerprintLoginRequest.setIOSOrAndroid(  false);

                        ProgressDialog progress = new ProgressDialog(Login.this);
                        progress.setMessage("Please wait while loading...");
                        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                        progress.show();
                        Call<FingerprintLoginResponse> signup = apiInterface.fingerprintLoginResponse(fingerprintLoginRequest);
                        signup.enqueue(new Callback<FingerprintLoginResponse>() {
                            @Override
                            public void onResponse(Call<FingerprintLoginResponse> call, Response<FingerprintLoginResponse> response) {
                                FingerprintLoginResponse user = response.body();
                                if(user != null && user.status == true)
                                {
                                    PreferenceData.setUserLoggedInStatus(getApplicationContext(),true);
                                    PreferenceData.setPrefUserName(getApplicationContext(), user.userInfo.fullName);
                                    PreferenceData.setPrefUserToken(getApplicationContext(), user.token);
                                    PreferenceData.setIsTenat(getApplicationContext(),user.userInfo.isTenant()  );
                                    PreferenceData.setIsFamily(getApplicationContext(),user.userInfo.isFamilyMember()  );
                                    PreferenceData.setIsMangement(getApplicationContext(), user.getUserInfo().isManagement );
                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                    intent.putExtra( "username", user.userInfo.fullName );
                                    startActivity( intent );
                                    finish();
                                    progress.dismiss();
                                }
                                else{
                                    progress.dismiss();
                                    emaail_login.setError( "invalid email" );
                                    password_login.setError( "invalid password" );
                                }
                            }
                            @Override
                            public void onFailure(Call<FingerprintLoginResponse> call, Throwable t) {
                                System.out.println("Failed : " + t.getMessage());
                                call.cancel();
                            }
                        });
                    }
                });



    }

    public void signInFunction(View view, LoginResponse user) {
        PreferenceData.setIsTenat(getApplicationContext(),user.userInfoModel.isTenant()  );
        PreferenceData.setIsFamily(getApplicationContext(),user.userInfoModel.isFamilyMember()  );
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        intent.putExtra( "username", user.userInfoModel.FullName );
        startActivity( intent );
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        simpleVideoView.setVideoURI( Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.topcitydroneview));;
        simpleVideoView.start();
    }

    private void ids()
    {
        signup_btn = findViewById( R.id.login_signup_btn);
        login_btn = findViewById( R.id.login_loginbtn );
        forget_pass = findViewById( R.id.login_forget_pass );
        emaail_login = findViewById( R.id.login_email );
        password_login = findViewById( R.id.login_password );
        biometric_screen = findViewById( R.id.biometric_screen );
        logo_gif = findViewById( R.id.gif_logo_login );
        guests = findViewById( R.id.Guest);
        pass_eye = findViewById( R.id.password_show_eye );
        pass_eyehide = findViewById( R.id.password_hide_eye );
        simpleVideoView = (VideoView) findViewById(R.id.bg_video);
    }
}