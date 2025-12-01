package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.HS.Topcity.ApiModels.Logout.LogoutRequest;
import com.HS.Topcity.ApiModels.Logout.LogoutResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.ProfileSetting.UserInfoDetailsResponse;
import com.HS.Topcity.ApiModels.UpdatePass.UpdatePassRequest;
import com.HS.Topcity.ApiModels.UpdatePass.UpdatePassResponse;
import com.HS.Topcity.ApiModels.UpdateUserProfile.UpdateUserInfoRequest;
import com.HS.Topcity.ApiModels.UpdateUserProfile.UpdateUserInfoResponse;
import com.HS.Topcity.ApiModels.UploadProfileImage.UploadProfileImageResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    String Email_get,Cnic_get;
    EditText names,email,cnic,phone,password;
    LinearLayout back,SharedAccount_Btn,editDetails_btn,update_btn;
    ImageView profileimage,notification,add_image,pass_eye,pass_eyehide;
    TextView logout,update_Pass,user_name,owner_status;
    Bundle extras;
    Bitmap a;
    Uri pic;
    String Image_profile;
    ApiInterface apiInterface;
    ArrayList<String> images;
    private  static final Pattern password_pattern = Pattern.compile("^.{8,}$");
    public  static String name_get;
    TextView Noti_count;
    LinearLayout Noti_bg,name_bg,pass_bg;
    UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
    UpdatePassRequest updateUserPasswordRequest = new UpdatePassRequest();
    LogoutRequest logoutRequest = new LogoutRequest();
    Switch finger_on;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    String name ;
    // Uri indicates, where the image will be picked from
    private Uri filePath;
    String firebase_image_link;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_profile );
        getSupportActionBar().hide();
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // ids
        ids();
        // password show and hide funcationality
        pass_show_functionality();

        // notification work
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        // notification count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        // text filed fill data
            fillUp_txtFields();

        // fingerprint switch functionality
            Switch_check();



        SharedAccount_Btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(UserProfile.this, SharedAccount.class);
                startActivity(a);
            }
        } );
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(UserProfile.this, Notification.class);
                startActivity(a);
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        update_btn.setEnabled( false);
        editDetails_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_btn.setEnabled( true);
                password.setEnabled( true );
                update_Pass.setEnabled( true );
                pass_eyehide.setVisibility( View.VISIBLE );
              //  name_bg.setBackgroundResource(  R.drawable.button_outline_blue  );
                pass_bg.setBackgroundResource(  R.drawable.button_outline_blue  );


//                email.setEnabled( true );
//                phone.setEnabled( true );
            }
        } );
        profileimage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                images = new ArrayList<>();
                if( Image_profile !=null){
                    images =  new ArrayList<>();
                    images.add( Image_profile  );
                    Intent a  = new Intent(getApplicationContext(), FullScreenImagesActivity.class);
                    a.putStringArrayListExtra( "Array",images);
                    a.putExtra( "position",0 );
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a);
                }
            }
        } );
        add_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camera_and_gallery_dialog();
            }
        } );
        update_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals( "" )){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(UserProfile.this);
                    builder1.setMessage("Please enter password to update.");
                    builder1.setTitle( "Alert" );
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder1.show();
                }
                else {
                    String passwords = password.getText().toString();
                    if( password_pattern.matcher(passwords ).matches()) {
                        UpdatePassword();
                    }
                    else{
                        password.setError( "At least 8 characters long" );
                    }
                }


              //  update_data();
            }
        } );

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserProfile.this);
                builder1.setMessage("Are you sure Logout this app?");
                builder1.setTitle( "Alert" );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logout();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.show();

            }
        } );

        update_Pass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserProfile.this);
                builder1.setMessage("Are you sure Upate your password");
                builder1.setTitle( "Alert" );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String passwords = password.getText().toString();
                                if( password_pattern.matcher(passwords ).matches()) {
                                        UpdatePassword();
                                }
                                else{
//                    new_pass.setError( "At least 8 characters long;\n" +
//                            "One Upper, One Lower, One Number and One Special Character" );

                                    password.setError( "At least 8 characters long" );
                                }

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.show();
            }
        } );

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

                finger_on.setChecked( false );
            }


            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                PreferenceData.setPrefUserFigerEnableStatus(UserProfile.this,true);
                PreferenceData.setLoggedInUserEmail(UserProfile.this,Email_get);
                PreferenceData.setPrefLoggedinUserCnic(UserProfile.this,Cnic_get);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
                finger_on.setChecked( false );
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric For TopCity-1")
                .setSubtitle("Identify Yorself!")
                .setNegativeButtonText("cancel")
                .build();


        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



    }
    private void ids(){
        names = findViewById( R.id.MyAccount_name );
        email = findViewById( R.id.MyAccount_email );
        cnic = findViewById( R.id.MyAccount_Cnic );
        phone = findViewById( R.id.MyAccount_phone );
        password = findViewById( R.id.MyAccount_pass );
        SharedAccount_Btn = findViewById( R.id.CreateSharedAccounts_Btn );
        editDetails_btn = findViewById( R.id.MyAccount_editdetailsBtn );
        update_btn = findViewById( R.id.MyAccount_UpdateBtn );
        profileimage = findViewById( R.id.user_image );
        back = findViewById( R.id.back_tousersetting );
        notification = findViewById( R.id.notification_usersetting );
        logout = findViewById( R.id.MyAccount_logout );
        update_Pass = findViewById( R.id.MyAccount_update_ChangePass );
        user_name = findViewById( R.id.MyAccount_user_name);
        finger_on = findViewById( R.id.fingerprint_on );
        name_bg = findViewById( R.id.name_bg );
        pass_bg = findViewById( R.id.pass_bg );
        add_image = findViewById( R.id.profileSetting_cameraicon );
        owner_status = findViewById( R.id.property_Owner );
        pass_eye = findViewById( R.id.password_show_eye );
        pass_eyehide = findViewById( R.id.password_hide_eye );
    }

    private void logout_api(){
        String token = PreferenceData.getPrefUserToken( getApplicationContext() );

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String uniqueID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        logoutRequest.setDeviceId(uniqueID );
        logoutRequest.setIOSorAndroid(false );


        Call<LogoutResponse> registerResponseCall = apiInterface.logoutResponse( token, logoutRequest );
        registerResponseCall.enqueue( new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse user = response.body();


                if (user != null) {

                    if (user.status != false) {
                        PreferenceData.setUserLoggedInStatus(UserProfile.this,false);
                        PreferenceData.setPrefUserName(UserProfile.this,"");
                        name_get =null;
                        Intent logoutintent = new Intent(UserProfile.this, Login.class);
                        startActivity(logoutintent);
                        finish();
                    }


                    progress.dismiss();
                } else {

                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
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




        updateUserPasswordRequest.setNewPassword( password.getText().toString() );


        Call<UpdatePassResponse> registerResponseCall = apiInterface.updatePassResponse( token, updateUserPasswordRequest );
        registerResponseCall.enqueue( new Callback<UpdatePassResponse>() {
            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {
                UpdatePassResponse user = response.body();


                if (user != null) {

                    if (user.status == true) {
                        password.setEnabled( false );
                        update_Pass.setEnabled( false );
                        update_btn.setEnabled( false);
                        pass_eyehide.setVisibility( View.GONE );
                        pass_eye.setVisibility( View.GONE );
                        password.setText( "" );
                        //  name_bg.setBackgroundResource(  R.drawable.button_outline_blue  );
                        pass_bg.setBackgroundResource(  R.drawable.textfiled_bg_blue_round  );
                        Toast.makeText( getApplicationContext(), user.message, Toast.LENGTH_SHORT ).show();
                    }


                    progress.dismiss();
                } else {
//                    Toast.makeText( getApplicationContext(), user.getMessage(), Toast.LENGTH_SHORT ).show();
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }

    private void camera_and_gallery_dialog(){
        final Dialog camera_and_gallery_dialog = new Dialog( UserProfile.this );

        camera_and_gallery_dialog.setContentView(R.layout.camera_and_gallery_dailoglayout);
        camera_and_gallery_dialog.setCancelable(true);
        camera_and_gallery_dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.WHITE));
        camera_and_gallery_dialog.getWindow().setLayout( WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT );
        camera_and_gallery_dialog.setCanceledOnTouchOutside(false);
        camera_and_gallery_dialog.show();

        LinearLayout camera = camera_and_gallery_dialog.findViewById( R.id.profileSetting_camera );
        LinearLayout GALLERY = camera_and_gallery_dialog.findViewById( R.id.profileSetting_gallery );

        camera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                        if (ContextCompat.checkSelfPermission(UserProfile.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            camera_and_gallery_dialog.dismiss();
                            Intent  intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            //  intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE);
                            //  intent.setData( MediaStore.Images.Media.INTERNAL_CONTENT_URI );
                            startActivityForResult(intent, 1);
                    }
                    else
                    {
                        camera_and_gallery_dialog.dismiss();
                       // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
                        ActivityCompat.requestPermissions(UserProfile.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }
                else
                {
                    // if version is below m then write code here,
                }

            }
        } );
        GALLERY.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ContextCompat.checkSelfPermission(UserProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        camera_and_gallery_dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 11);
                    }
                    else
                    {
                        camera_and_gallery_dialog.dismiss();
                        // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
                        ActivityCompat.requestPermissions(UserProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
                    }
                }
                else
                {
                    // if version is below m then write code here,
                }



            }
        } );


    }
    private void logout(){

      logout_api();;
    }
//    private void upload_api(){
//        String token = PreferenceData.getPrefUserToken(getApplicationContext());
//        ProgressDialog progress = new ProgressDialog(getApplicationContext());
//
//        apiInterface = ApiUtils.postSignUpService();
//
//        progress.setMessage("Please wait while loading...");
//        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//        //   progress.show();
//        MultipartBody.Part imagenPerfil = null;
////        if(image!=null){
//        File file = new File( "image/" );
////           // Log.i("Register","Nombre del archivo "+file.getName());
////            // create RequestBody instance from file
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf( image ) );
//        // MultipartBody.Part is used to send also the actual file name
//        //  imagenPerfil = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//        imagenPerfil = MultipartBody.Part.createFormData( "image", String.valueOf( image ) ) ;
//
////        }
//
//        Call<ComplaintCreateResponse> userPropertiesResponseCall = apiInterface.COMPLAINT_CREATE_RESPONSE_CALL(token,complainTpyeid,proID,subComid,comSub,comDetail,imagenPerfil);
//        userPropertiesResponseCall.enqueue(new Callback<ComplaintCreateResponse>() {
//            @Override
//            public void onResponse(Call<ComplaintCreateResponse> call, Response<ComplaintCreateResponse> response) {
//                ComplaintCreateResponse user = response.body();
//
//
//                if (user != null) {
//
//                    if (user.status != false) {
//
//
//
//                    }
//                    progress.dismiss();
//                } else {
//                    System.out.println( "Failed" );
//                }
//
//            }
//            @Override
//            public void onFailure(Call<ComplaintCreateResponse> call, Throwable t) {
//                System.out.println("Failed : " + t.getMessage());
//                call.cancel();
//            }
//        });
//
//
//    }

    private void fillUp_txtFields(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());


        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // news Announcement api intregration

        Call<UserInfoDetailsResponse> newsAndAnnouncementsResponseCall = apiInterface.userInfoDetailsResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<UserInfoDetailsResponse>() {
            @Override
            public void onResponse(Call<UserInfoDetailsResponse> call, Response<UserInfoDetailsResponse> response) {
                UserInfoDetailsResponse user = response.body();


                if (user != null) {


                    if (user.userInfo != null) {
//
                        user_name.setText( user.userInfo.getFullName() );
                        names.setText( user.userInfo.getFullName() );
                        email.setText( user.userInfo.getEmail());
                        phone.setText( user.userInfo.getContactNumber());
                        cnic.setText( user.userInfo.getCNIC());

                        Email_get = user.userInfo.getEmail();
                        Cnic_get = user.userInfo.getCNIC();

                        Image_profile = user.userInfo.Image.toString();

                        // user property status chcek

                        if(user.userInfo.Owner == true){
                            name = "Property Owner";
                            owner_status.setText( name);
                        }
                        else if(user.userInfo.isFamilyMember() == true){
                            name = "Family Member";
                            owner_status.setText( name);
                        }
                        else if(user.userInfo.isTenant() == true){
                            name = "Tenat";
                            owner_status.setText( name);
                        }

                        if(user.userInfo.Owner == true && user.userInfo.isFamilyMember() == true){
                            name += " " +"& Family Member";
                            owner_status.setText( name  );
                        }
                       else if(user.userInfo.Owner == true && user.userInfo.isTenant() == true){
                            name += " " +"& Tenat";
                            owner_status.setText( name  );
                        }
                        else if(user.userInfo.isFamilyMember() == true && user.userInfo.isTenant() == true){
                            name +=  " " +"& Tenat" ;
                            owner_status.setText( name );
                        }
                        if(user.userInfo.Owner == true && user.userInfo.isFamilyMember() == true && user.userInfo.isTenant() == true){
                            name +=  " " +"& Tenat" ;
                            owner_status.setText( name  );
                        }

                        // finger print status check
                        if(PreferenceData.getPrefUserFigerEnableStatus(UserProfile.this  ) != false && PreferenceData.getLoggedInEmailUser(UserProfile.this  ).equals( Email_get ) ){
                            finger_on.setChecked( true );
                        }
                        else {
                            finger_on.setChecked( false );
                        }
                        if(user.userInfo.getImage() != null)
                        {
                            Glide.with( getApplicationContext() ).load( user.userInfo.getImage() ).into( profileimage );
                        }
                        else{
                            profileimage.setImageResource( R.drawable.ic_baseline_person_24 );
                        }

                        // password.setText( user.userInfo.get);
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserInfoDetailsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void Switch_check(){
        String test_email = PreferenceData.getLoggedInEmailUser(UserProfile.this  );

//        finger_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // The toggle is enable
//
//                        biometricPrompt.authenticate(promptInfo);
//
//
//                } else {
//                    // The toggle is disabled
//                    PreferenceData.setPrefUserFigerEnableStatus(UserProfile.this,false);
//
//                }
//            }
//        });
     finger_on.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (finger_on.isChecked()) {
                    // The toggle is enable

                        biometricPrompt.authenticate(promptInfo);


                } else {
                    // The toggle is disabled
                    PreferenceData.setPrefUserFigerEnableStatus(UserProfile.this,false);

                }
         }
     } );

    }
    private void pass_show_functionality(){
        pass_eyehide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );

                password.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                pass_eyehide.setVisibility( View.GONE );
                pass_eye.setVisibility( View.VISIBLE );

            }
        } );
        pass_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );

                password.setTransformationMethod( PasswordTransformationMethod.getInstance());
                pass_eye.setVisibility( View.GONE );
                pass_eyehide.setVisibility( View.VISIBLE );
            }
        } );
    }

    private void update_data(){

        String email_get =  email.getText().toString();
         name_get = names.getText().toString();
        String number_get = phone.getText().toString();

        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // update User info api intregration

        updateUserInfoRequest.setEmail( email_get );
        updateUserInfoRequest.setContactNumber( number_get );
        updateUserInfoRequest.setName( name_get );
        Call<UpdateUserInfoResponse> newsAndAnnouncementsResponseCall = apiInterface.updateUserInfoResponse(token,updateUserInfoRequest);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<UpdateUserInfoResponse>() {
            @Override
            public void onResponse(Call<UpdateUserInfoResponse> call, Response<UpdateUserInfoResponse> response) {
                UpdateUserInfoResponse user = response.body();


                if (user != null) {


                    if (user.status == true) {

                        user_name.setText( name_get);
                        PreferenceData.setPrefUserName(getApplicationContext(), name_get);

                        Toast.makeText( getApplicationContext(), "Update Profile Done", Toast.LENGTH_SHORT ).show();
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UpdateUserInfoResponse> call, Throwable t) {

                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent  intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
            else
            {
                Toast.makeText(this, "Please Allow the camera permission", Toast.LENGTH_LONG).show();
            }
        }
      else   if (requestCode == 11)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 11);
            }
            else
            {
                Toast.makeText(this, "Please Allow the External Storage permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );


//        try {
//            if (requestCode == 1 ) {
//                Bitmap photo = (Bitmap) data.getExtras().get( "data" );
//                if (photo != null) {
//                    profileimage.setImageBitmap( photo );
//                    String token = PreferenceData.getPrefUserToken(getApplicationContext());
//                    ProgressDialog progress = new ProgressDialog(getApplicationContext());
//
//                    apiInterface = ApiUtils.postSignUpService();
//
//                    progress.setMessage("Please wait while loading...");
//                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//                    //   progress.show();
//                    MultipartBody.Part imagenPerfil = null;
//                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//                    Uri tempUri = getImageUri(getApplicationContext(), photo);
//
//                    // CALL THIS METHOD TO GET THE ACTUAL PATH
//                    File finalFile = new File(getRealPathFromURI(tempUri));
////            // create RequestBody instance from file
//                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), finalFile );
//                    // MultipartBody.Part is used to send also the actual file name
//                      imagenPerfil = MultipartBody.Part.createFormData("image", finalFile.getName(), requestFile);
//
//
//                    Call<UploadProfileImageResponse> userPropertiesResponseCall = apiInterface.uploadProfileImageResponse(token,imagenPerfil);
//                    userPropertiesResponseCall.enqueue(new Callback<UploadProfileImageResponse>() {
//                        @Override
//                        public void onResponse(Call<UploadProfileImageResponse> call, Response<UploadProfileImageResponse> response) {
//                            UploadProfileImageResponse user = response.body();
//
//
//                            if (user != null) {
//
//                                if (user.status != false) {
//
//                                    Toast.makeText( getApplicationContext(), "upload successfully", Toast.LENGTH_SHORT ).show();
//                                }
//                                progress.dismiss();
//                            } else {
//                                System.out.println( "Failed" );
//                            }
//
//                        }
//                        @Override
//                        public void onFailure(Call<UploadProfileImageResponse> call, Throwable t) {
//                            System.out.println("Failed : " + t.getMessage());
//                            call.cancel();
//                        }
//                    });
//                } else {
//                    System.out.println( "Failed : " );
//                }
//
//            }
//          else if (requestCode == 11) {
//              Uri uri = data.getData();
//                Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                if (photo != null) {
//                    profileimage.setImageBitmap( photo );
//                    String token = PreferenceData.getPrefUserToken(getApplicationContext());
//                    ProgressDialog progress = new ProgressDialog(getApplicationContext());
//
//                    apiInterface = ApiUtils.postSignUpService();
//
//                    progress.setMessage("Please wait while loading...");
//                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//                    //   progress.show();
//                    MultipartBody.Part imagenPerfil = null;
//                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//                    Uri tempUri = getImageUri(getApplicationContext(), photo);
//
//                    // CALL THIS METHOD TO GET THE ACTUAL PATH
//                    File finalFile = new File(getRealPathFromURI(tempUri));
////            // create RequestBody instance from file
//                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), finalFile );
//                    // MultipartBody.Part is used to send also the actual file name
//                    imagenPerfil = MultipartBody.Part.createFormData("image", finalFile.getName(), requestFile);
//
//
//                    Call<UploadProfileImageResponse> userPropertiesResponseCall = apiInterface.uploadProfileImageResponse(token,imagenPerfil);
//                    userPropertiesResponseCall.enqueue(new Callback<UploadProfileImageResponse>() {
//                        @Override
//                        public void onResponse(Call<UploadProfileImageResponse> call, Response<UploadProfileImageResponse> response) {
//                            UploadProfileImageResponse user = response.body();
//
//
//                            if (user != null) {
//
//                                if (user.status != false) {
//
//                                    Toast.makeText( getApplicationContext(), "upload successfully", Toast.LENGTH_SHORT ).show();
//                                }
//                                progress.dismiss();
//                            } else {
//                                System.out.println( "Failed" );
//                            }
//
//                        }
//                        @Override
//                        public void onFailure(Call<UploadProfileImageResponse> call, Throwable t) {
//                            System.out.println("Failed : " + t.getMessage());
//                            call.cancel();
//                        }
//                    });
//                } else {
//                    System.out.println( "Failed : " );
//                }
//
//            }else {
//                pic = data.getData();
//
//                if (pic != null) {
//                    //    extras = data.getExtras();
////        a  = extras.getParcelable( "image" );
//                    profileimage.setImageURI( pic );
//                } else {
//                    System.out.println( "Failed : " );
//                }
//
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println( "Failed : " );
//            e.printStackTrace();
//        }
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view


        try {
            if (requestCode == 11 || requestCode == 1
                    && resultCode == RESULT_OK
                    && data != null) {

                // Get the Uri of data

                if (data != null) {
                    if (requestCode == 1) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get( "data" );
                        filePath = getImageUri( getApplicationContext(), bitmap );
                    }

                } else if (requestCode == 11) {
                    filePath = data.getData();
                }


                uploadImage();
            }
            }
        catch (Exception e){
            System.out.println(e);
           // Toast.makeText( getApplicationContext(), "No picture select", Toast.LENGTH_SHORT ).show();
        }






    }
    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            int random;
            int max =1000;
            int min= 1;
            Random myRandom = new Random();
            random = myRandom.nextInt(max-min+1)+min;
        String dev_testing =   "DEV/Mobile-Android/Profile-Images/";
            String prod =   "Prod/mobileImages/Android/Profile-Images/";
            String uat =   "DEV/mobileImages/";
            StorageReference ref = storageReference.child( dev_testing + "ProfileImage" + random + ".jpg");

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)

                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                            new OnCompleteListener<Uri>() {

                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task) {
                                                    firebase_image_link = task.getResult().toString();
                                                    //next work with URL
                                                    String token = PreferenceData.getPrefUserToken(getApplicationContext());
                                                    ProgressDialog progress = new ProgressDialog(getApplicationContext());

                                                    apiInterface = ApiUtils.postSignUpService();

                                                    progress.setMessage("Please wait while loading...");
                                                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                                                    //   progress.show();
                                                    RequestBody images = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( firebase_image_link ));
                                                    Call<UploadProfileImageResponse> userPropertiesResponseCall = apiInterface.uploadProfileImageResponse(token,images);
                                                    userPropertiesResponseCall.enqueue(new Callback<UploadProfileImageResponse>() {
                                                        @Override
                                                        public void onResponse(Call<UploadProfileImageResponse> call, Response<UploadProfileImageResponse> response) {
                                                            UploadProfileImageResponse user = response.body();


                                                            if (user != null) {

                                                                if (user.status != false) {
                                                                    try {
                                                                        progressDialog.dismiss();
                                                                        Bitmap bitmap = MediaStore
                                                                                .Images
                                                                                .Media
                                                                                .getBitmap(
                                                                                        getContentResolver(),
                                                                                        filePath);
                                                                        profileimage.setImageBitmap(bitmap);
                                                                        Toast.makeText( getApplicationContext(), "upload successfully", Toast.LENGTH_SHORT ).show();

                                                                    }
                                                                    catch (Exception e){
                                                                        System.out.println( "Failed" );
                                                                    }

                                                                }
                                                                progress.dismiss();
                                                            } else {
                                                                System.out.println( "Failed" );
                                                            }

                                                        }
                                                        @Override
                                                        public void onFailure(Call<UploadProfileImageResponse> call, Throwable t) {
                                                            progress.dismiss();
                                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(UserProfile.this);
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
                                                    // Setting image on image view using Bitmap




                                                }

                                            } );
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(UserProfile.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //   inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        ApiInterface apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {


                    if (user.notificationsList != null) {

                        notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );
                        if(Noti_count.getText().toString().equals( "0" )){
                            Noti_bg.setVisibility( View.GONE );
                        }

                    }
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        Notification_list_api();
        super.onResume();

    }
}