package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.CommunityServiceAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.CustomClasses.GridSpacingItemDecoration;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.CommunityServiceModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityService extends AppCompatActivity {
    ApiInterface apiInterface;
    ArrayList<CommunityServiceModel> communityServiceModels;
    RecyclerView item_list;
    ImageView user,notification;
    TextView Noti_count;
    LinearLayout Noti_bg,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_community_service );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        id();
        // notification count funtionality
        noti_count_funtionality();
        // notification and user profile and back to perivous screen funtionality
        noti_user_and_back();
        // Coumnity service recycle view attach adapter
        adpater();
        // notification count Api
        Notification_list_api();

    }
    private void id(){
        item_list = findViewById( R.id.CommunityItem_list );
        back = findViewById( R.id.back_to_Community );
        user = findViewById( R.id.user_icon_Community );
        notification = findViewById( R.id.notification_community );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );

    }
    private void noti_count_funtionality(){
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
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
    private void adpater(){
        communityServiceModels = new ArrayList<>();
        communityServiceModels.add( new CommunityServiceModel(1,"Monthly Maintenance Charges",R.mipmap.water_form_icon) );
        communityServiceModels.add( new CommunityServiceModel(2,"Visitor Info System",R.mipmap.visitor_info_system_icon) );
        communityServiceModels.add( new CommunityServiceModel(3,"Zoo Ticketing",R.mipmap.zoo_ticketing_icon) );
        communityServiceModels.add( new CommunityServiceModel(4,"Community Hall Booking",R.mipmap.community_hall_icon) );


        CommunityServiceAdapter communityServiceAdapter = new CommunityServiceAdapter( getBaseContext(), communityServiceModels, R.layout.feature_item_layout );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getBaseContext(), LinearLayoutManager.VERTICAL, false );
        item_list.setLayoutManager( linearLayoutManager );
        item_list.setLayoutManager( new GridLayoutManager( getBaseContext(), 2 ) );
        item_list.setAdapter( communityServiceAdapter);
        int spanCount = 2; // 3 columns
        int spacing = 35; // 50px
        boolean includeEdge = false;
        item_list.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        item_list.setAdapter( communityServiceAdapter );
        item_list.refreshDrawableState();
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