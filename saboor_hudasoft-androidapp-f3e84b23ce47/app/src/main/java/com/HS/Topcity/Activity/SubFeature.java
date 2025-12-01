package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
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

import com.HS.Topcity.Adapters.SubFeaturesParentAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.SubFeature.SubFeatureRequest;
import com.HS.Topcity.ApiModels.SubFeature.SubFeatureResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.SubFeaturesModels;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubFeature extends AppCompatActivity {
    ImageView notification,user;
    LinearLayout signUp_guest;
    TextView subfeature_details_name,signup;
    RecyclerView subFeature_Details_list;
    LinearLayout no_SubFeatures_text;
    ApiInterface apiInterface;
    ArrayList<SubFeaturesModels> subFeaturesModels;
    TextView Noti_count;
    LinearLayout Noti_bg,back;
    SubFeatureRequest subFeatureRequest = new SubFeatureRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sub_feature );
        getSupportActionBar().hide();
        //getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // All ids
        ids();
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        // Notification count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        // notification , userProfile , back pressed Function
        noti_user_and_back();
        // api hit with adapter
        SubFeature_Api_Intregration();
        //guest
        guest();

        subfeature_details_name.setText( getIntent().getStringExtra( "feature_name" ) );


    }

    private void ids(){
        user = findViewById( R.id.user_icon_SubFeature );
        notification = findViewById( R.id.notification_SubFeature );
        back = findViewById( R.id.back_to_SubFeature );
        subFeature_Details_list = findViewById( R.id.subFeature_list );
        subfeature_details_name = findViewById( R.id.feature_itemname_details );
        signUp_guest = findViewById( R.id.signup_btn_home );
        no_SubFeatures_text = findViewById( R.id.no_subFeature_text );
    }
    private void guest(){
        if(guest != null){
            Noti_bg.setVisibility( View.GONE );
            user.setVisibility( View.GONE );
            notification.setVisibility( View.GONE );
            signUp_guest.setVisibility( View.VISIBLE );
            signUp_guest.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a  = new Intent(getApplicationContext(), Signup.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a );
                }
            } );
        }
    }
    private void noti_user_and_back(){
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

    private void  SubFeature_Api_Intregration(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        subFeatureRequest.setFeatureId( Integer.parseInt( getIntent().getStringExtra( "feature_id" ) ) );

        Call<SubFeatureResponse> subFeatureResponseCall = apiInterface.SUB_FEATURE_RESPONSE_CALL(token,subFeatureRequest);
        subFeatureResponseCall.enqueue(new Callback<SubFeatureResponse>() {
            @Override
            public void onResponse(Call<SubFeatureResponse> call, Response<SubFeatureResponse> response) {
                SubFeatureResponse user = response.body();


                if (user != null) {

                    subFeaturesModels = new ArrayList<>();


                    if(user.subFeatures.size() != 0)
                    {
                        no_SubFeatures_text.setVisibility( View.GONE );
                        if (user.subFeatures != null) {
                            for (int i = 0; i < user.subFeatures.size(); i++) {
                                SubFeaturesModels model = new SubFeaturesModels();
                                model.setName( user.subFeatures.get( i ).name );
                                model.setDescription( user.subFeatures.get( i ).description );
                                model.setSubFeatureId( user.subFeatures.get( i ).subFeatureId );
                                model.setFeatureId( user.subFeatures.get( i ).featureId );
                                model.setImage( user.subFeatures.get( i ).image );
                                subFeaturesModels.add( model );
                            }
                            SubFeaturesParentAdapter subFeaturesParentAdapter = new SubFeaturesParentAdapter( getApplicationContext(), subFeaturesModels, R.layout.sub_feature_parent_layout );
                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );
                            subFeature_Details_list.setLayoutManager( linearLayoutManager2 );
                            subFeature_Details_list.setNestedScrollingEnabled( true );
                            subFeature_Details_list.setAdapter( subFeaturesParentAdapter );

                        }
                    }
                    else{
                        no_SubFeatures_text.setVisibility( View.VISIBLE );
                    }

                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<SubFeatureResponse> call, Throwable t) {
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
        Notification_list_api();
        super.onResume();

    }
}