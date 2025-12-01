package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.PropertySharedAccountDetailsAdapter;
import com.HS.Topcity.Adapters.SharedAccountLisrAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.PropertySharedAccountDetail.PropertySharedAccountDetailsRequest;
import com.HS.Topcity.ApiModels.PropertySharedAccountDetail.PropertySharedAccountDetailsResponse;
import com.HS.Topcity.ApiModels.UserSharedAccounts.UserSharedAccountsResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.UserSharedAccountListModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharedAccountUserList extends AppCompatActivity {
    ImageView notification,user;
    LinearLayout add_user_btn;
    ApiInterface apiInterface;
    RecyclerView list;
    TextView Noti_count;
    LinearLayout Noti_bg,back;
    int id;
    ArrayList<UserSharedAccountListModel> userSharedAccountListModels;
    PropertySharedAccountDetailsRequest propertySharedAccountDetailsRequest = new PropertySharedAccountDetailsRequest();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shared_account_user_list );
        getSupportActionBar().hide();
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        ids();
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );

        // Notifcation count api
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        if(getIntent().getStringExtra( "id_property" ) != null) {
           id = Integer.parseInt(getIntent().getStringExtra( "id_property" )  );
       }

        // api intregration
       show_propertySharedAccountDetails();
        // UserSharedAccounts_apiIntregration();
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
        add_user_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), AddSharedAccount.class );
                a.putExtra( "id",getIntent().getStringExtra( "id_property" ) );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
                finish();
            }
        } );
    }
    private void ids(){
        notification = findViewById( R.id.notification_shared_Account );
        user = findViewById( R.id.user_icon_sharedAccount );
        back = findViewById( R.id.back_to_add_share_account_list );
        add_user_btn = findViewById( R.id.shared_Account_AddNewUser_Btn);
        list = findViewById( R.id.Shared_account_detail_list );
    }

    private void show_propertySharedAccountDetails(){
        if( id  != 0)
        {
            propertySharedAccountDetailsRequest.setPropertyId( id );

            // hide shared account btn
            String val = getIntent().getStringExtra( "hide_share_button" );
            if (val.equals( "true" )){
                add_user_btn.setVisibility( View.GONE );
            }

            // api User Shared Accounts Details
            UserSharedAccountsDetails_apiIntregration();



        }
        else {
            // api User Shared Accounts
            UserSharedAccounts_apiIntregration();
        }
    }
    private void UserSharedAccounts_apiIntregration(){
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
          // progress.show();



        Call<UserSharedAccountsResponse> newsAndAnnouncementsResponseCall = apiInterface.userSharedAccountsResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<UserSharedAccountsResponse>() {
            @Override
            public void onResponse(Call<UserSharedAccountsResponse> call, Response<UserSharedAccountsResponse> response) {
                UserSharedAccountsResponse user = response.body();


                if (user != null) {

                    userSharedAccountListModels = new ArrayList<>();
                    if (user.userInfosModels != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.userInfosModels.size(); i++) {
                            UserSharedAccountListModel model = new UserSharedAccountListModel();
                            model.setFullName( user.userInfosModels.get( i ).getFullName() );
                            model.setStateOfAccount( user.userInfosModels.get( i ).getStateOfAccount() );
                            model.setTypeAccountId( user.userInfosModels.get( i ).getTypeOfAccountId() );
                            model.setTypeOfAccount( user.userInfosModels.get( i ).getTypeOfAccount() );
                            model.setPropertyId( user.userInfosModels.get( i ).getPropertyId() );
                            model.setImage( user.userInfosModels.get( i ).getImage() );
                            model.setId( user.userInfosModels.get( i ).getId() );
                            model.setPropertyName( user.userInfosModels.get( i ).getPropertyName() );
                            userSharedAccountListModels.add( model );
                        }

                        //  NewsAnnouncementAdapter newsAnnouncementAdapter = new NewsAnnouncementAdapter( container.getContext(), newsAnnouncementModels,R.layout.news_annocement_layout );

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.VERTICAL,false );
                        list.setLayoutManager( linearLayoutManager2 );
                        list.setNestedScrollingEnabled(true);
                        list.setAdapter( new SharedAccountLisrAdapter(getApplicationContext(), userSharedAccountListModels, R.layout.shared_account_user_list_layout));



                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserSharedAccountsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }



    private void UserSharedAccountsDetails_apiIntregration(){
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
      //  progress.show();

        Call<PropertySharedAccountDetailsResponse> newsAndAnnouncementsResponseCall = apiInterface.propertySharedAccountDetailsResponse(token,propertySharedAccountDetailsRequest);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<PropertySharedAccountDetailsResponse>() {
            @Override
            public void onResponse(Call<PropertySharedAccountDetailsResponse> call, Response<PropertySharedAccountDetailsResponse> response) {
                PropertySharedAccountDetailsResponse user = response.body();


                if (user != null) {

                    userSharedAccountListModels = new ArrayList<>();
                    if (user.propertySharedAccountDetails != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.propertySharedAccountDetails.size(); i++) {
                            UserSharedAccountListModel model = new UserSharedAccountListModel();
                            model.setFullName( user.propertySharedAccountDetails.get( i ).getFullName() );
                            model.setStateOfAccount( user.propertySharedAccountDetails.get( i ).getStateOfAccount() );
                            model.setTypeAccountId( user.propertySharedAccountDetails.get( i ).getTypeOfAccountId() );
                            model.setTypeOfAccount( user.propertySharedAccountDetails.get( i ).getTypeOfAccount() );
                            model.setPropertyId( user.propertySharedAccountDetails.get( i ).getPropertyId() );
                            model.setImage( user.propertySharedAccountDetails.get( i ).getImage() );
                            model.setId( user.propertySharedAccountDetails.get( i ).getId() );
                            model.setPropertyName( user.propertySharedAccountDetails.get( i ).getPropertyName() );
                            userSharedAccountListModels.add( model );
                        }

                        //  NewsAnnouncementAdapter newsAnnouncementAdapter = new NewsAnnouncementAdapter( container.getContext(), newsAnnouncementModels,R.layout.news_annocement_layout );

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.VERTICAL,false );
                        list.setLayoutManager( linearLayoutManager2 );
                        list.setNestedScrollingEnabled(true);
                        list.setAdapter( new PropertySharedAccountDetailsAdapter(getApplicationContext(), userSharedAccountListModels, R.layout.shared_account_user_list_layout));



                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<PropertySharedAccountDetailsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
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
        show_propertySharedAccountDetails();
        Notification_list_api();
        super.onResume();

    }
}