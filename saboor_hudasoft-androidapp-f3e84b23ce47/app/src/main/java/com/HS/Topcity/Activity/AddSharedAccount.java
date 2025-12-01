package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.AddSharedAccountAdapter;
import com.HS.Topcity.ApiModels.CreateSharedAccounts.CreateSharedAccountsRequest;
import com.HS.Topcity.ApiModels.CreateSharedAccounts.CreateSharedAccountsResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.UserProperties.UserPropertiesResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.PropertiesModel;
import com.HS.Topcity.Models.UserTypeListModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSharedAccount extends AppCompatActivity {
    ImageView notification,user;
    LinearLayout back;
    EditText name,email,phone,cnic;
    String name_get,email_get,phone_get,cnic_get;
   public static int propertyid;
    Spinner usertype;
    boolean isDetail_Status;
    RecyclerView userpropertyList;
    public  static LinearLayout textfileds;
    LinearLayout Share_btn;
    ApiInterface apiInterface;
    ArrayList<PropertiesModel> propertiesModels;
    ArrayList<UserTypeListModel> userTypeListModels;
    ArrayList<String> UserTypeList = new ArrayList<String>();
    CreateSharedAccountsRequest createSharedAccountsRequest = new CreateSharedAccountsRequest();
    TextView Noti_count;
      public static   TextView no_pro_txt;
    LinearLayout Noti_bg;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\*\\&\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_shared_account );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // ids
        ids();

        // api integration property
        property_Api();
        // user type fileds frop down
        usertype_DropDown();
        // Notification and User profile or back to perivous screen funtionality
        noti_user_back();
        // notification count funcationality
        noti_count();
        // automatically dash input
        cnic_auto_dash();
        // share button funtionality
        share_btn();
        // Notication count
        Notification_list_api();

        management_tenat();


    }
    private void ids(){
        notification = findViewById( R.id.notification_add_share_account );
        user = findViewById( R.id.user_icon_add_share_account );
        back = findViewById( R.id.back_to_add_share_account );
        Share_btn = findViewById( R.id.shared_Account_share_btn );
        notification = findViewById( R.id.notification_add_share_account );
        user = findViewById( R.id.user_icon_add_share_account );
        back = findViewById( R.id.back_to_add_share_account );
        Share_btn = findViewById( R.id.shared_Account_share_btn );
        textfileds = findViewById( R.id.add_share_account_textFileds );
        name = findViewById( R.id.add_share_account_name );
        cnic = findViewById( R.id.add_share_account_Cnic );
        phone = findViewById( R.id.add_share_account_phone );
        usertype = findViewById( R.id.user_type_add_share_account );
        email = findViewById( R.id.add_share_account_email );
        userpropertyList = findViewById( R.id.Shared_your_property_list );
        no_pro_txt = findViewById( R.id.no_property_txt );
    }

    private void management_tenat(){
        if (PreferenceData.getIsTenat( getApplicationContext() ) == true || PreferenceData.getIsFamily( getApplicationContext() ) == true || PreferenceData.getIsMangement( getApplicationContext() ) == true ){

            no_pro_txt.setVisibility( View.VISIBLE );
            userpropertyList.setVisibility( View.GONE );
            textfileds.setVisibility( View.GONE );
        }
        else {
            no_pro_txt.setVisibility( View.GONE );
            userpropertyList.setVisibility( View.VISIBLE );

        }
    }
    private void noti_count(){
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );

        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
    }
    private void share_btn(){
        Share_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if(isDetail_Status == true) {

                if(name.getText().toString().equals( "" ) || email.getText().toString().equals( "" ) || phone.getText().toString().equals( "" ) || cnic.getText().toString().equals( "" ) || usertype.getSelectedItem().toString().equals( "Select User Type" )){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder( AddSharedAccount.this);
                    builder1.setMessage("Missig Field");
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
                else {
                    if(cnic.getText().length() == 15){
                        if(phone.getText().length() == 11){
                            if(email.getText().toString().trim().matches(emailPattern)){
                                getData_all_fields_put_in_api_request();
                                share_property_api_intregration();
                            }
                            else {
                                email.setError( "Invalid Email" );
                            }
                        }
                        else {
                            cnic.setError( "Please Enter Correct Phone Number" );
                        }
                    }
                    else {
                        cnic.setError( "This Cnic is Invalid" );
                    }


                }

//                }
//                else {
//                    Toast.makeText( getApplicationContext(), "You are not owner and family member", Toast.LENGTH_SHORT ).show();
//                }

            }
        } );
    }

    private void noti_user_back(){
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(getApplicationContext(), Notification.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), UserProfile.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
    }
    private void property_Api(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(this);

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //   progress.show();
        Call<UserPropertiesResponse> userPropertiesResponseCall = apiInterface.userPropertiesResponse(token);
        userPropertiesResponseCall.enqueue(new Callback<UserPropertiesResponse>() {
            @Override
            public void onResponse(Call<UserPropertiesResponse> call, Response<UserPropertiesResponse> response) {
                UserPropertiesResponse user = response.body();


                if (user != null) {

                    propertiesModels = new ArrayList<>();
                    if (user.properties != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.properties.size(); i++) {
                            PropertiesModel model = new PropertiesModel();
                            model.setId( user.properties.get( i ).getId() );
                            model.setAddress( user.properties.get( i ).getAddress() );
                            model.setImage( user.properties.get( i ).getImage() );
                            model.setOwner( user.properties.get( i ).getOwner() );
                            model.setLinkToProperty( user.properties.get( i ).getLinkToProperty() );

                            // isDetail_Status = user.properties.get( i ).getDetail();
                            propertiesModels.add( model );
                        }

                        AddSharedAccountAdapter userPropertyAdapter = new AddSharedAccountAdapter( getApplicationContext(), propertiesModels,R.layout.user_property_layout );

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                        userpropertyList.setLayoutManager( linearLayoutManager2 );
                        userpropertyList.setNestedScrollingEnabled(true);
                        userpropertyList.setAdapter( userPropertyAdapter );
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserPropertiesResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });


    }

    private void cnic_auto_dash(){
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
    private void usertype_DropDown(){
        UserTypeList.add( "Select User Type" );
        UserTypeList.add( "Family Member" );
        UserTypeList.add( "Tenant User" );

        ArrayAdapter<String> modeladapter=new ArrayAdapter<String>(AddSharedAccount.this,android.R.layout.simple_list_item_1,UserTypeList);
        modeladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(modeladapter);
    }

    private void getData_all_fields_put_in_api_request(){

        name_get = name.getText().toString();
        email_get = email.getText().toString();
        cnic_get = cnic.getText().toString();
        phone_get = phone.getText().toString();

        if(usertype.getSelectedItem().toString().equals(  "Family Member"))
        {
            createSharedAccountsRequest.setShareAccountType(1);
        }
        else if(usertype.getSelectedItem().toString().equals(  "Tenant User")){
            createSharedAccountsRequest.setShareAccountType(2);
        }

        createSharedAccountsRequest.setName( name_get );
        createSharedAccountsRequest.setEmail( email_get );
        createSharedAccountsRequest.setcNICNumber( cnic_get );
        createSharedAccountsRequest.setPhoneNumber( phone_get );
        createSharedAccountsRequest.setPropertyId( propertyid );


    }
    private void share_property_api_intregration(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Call<CreateSharedAccountsResponse> sharedAccountsResponseCall = apiInterface.createSharedAccountsResponse(token,createSharedAccountsRequest);
        sharedAccountsResponseCall.enqueue(new Callback<CreateSharedAccountsResponse>() {
            @Override
            public void onResponse(Call<CreateSharedAccountsResponse> call, Response<CreateSharedAccountsResponse> response) {
                CreateSharedAccountsResponse user = response.body();


                if (user != null) {


                    if (user.status == true) {

                       Intent a = new Intent(getApplicationContext(),SharedAccountSuccessfully.class);
                       a.putExtra( "id",getIntent().getStringExtra( "id" ) );
                       startActivity( a );

                        Toast.makeText( getApplicationContext(), user.message.toString(), Toast.LENGTH_SHORT ).show();
                        finish();
                    }
                    else if(user.status == false){
                        progress.dismiss();
                        Toast.makeText( getApplicationContext(), user.message.toString(), Toast.LENGTH_SHORT ).show();
                    }
                   // Toast.makeText( getApplicationContext(), user.message.toString(), Toast.LENGTH_SHORT ).show();
                    progress.dismiss();
                } else {
                    progress.dismiss();
                    Toast.makeText( getApplicationContext(), user.message.toString(), Toast.LENGTH_SHORT ).show();

                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<CreateSharedAccountsResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText( getApplicationContext(), "Server Failed", Toast.LENGTH_SHORT ).show();
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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