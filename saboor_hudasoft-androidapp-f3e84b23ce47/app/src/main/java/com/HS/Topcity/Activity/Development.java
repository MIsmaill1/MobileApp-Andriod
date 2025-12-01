package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.HS.Topcity.Adapters.DevelopmentTabAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Fragsment.Development.ImagesFrag;
import com.HS.Topcity.Fragsment.Development.VideoFrag;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Development extends AppCompatActivity {
    ImageView user,notification;
    TabLayout tabLayout;
    ViewPager viewPager;
    FrameLayout frag;
    TextView Noti_count,video_btn,image_btn;;
    LinearLayout Noti_bg;
    LinearLayout back,signup;

    public static boolean  check_video = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_development );

        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        id();
        // notification , userProfile , back pressed Function
        noti_user_and_back();
        // notification  count
        noti_count();
        // guest user funtionality
        guest();
        // tablayout adapter attach
        tablayout();

        image_Btn();
        video_Btn();
        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag_develop, new ImagesFrag() ).commit();

    }
    private void id(){
        back = findViewById( R.id.back_to_Development );
        user = findViewById( R.id.user_icon_Development);
        notification = findViewById( R.id.notification_Development );
        tabLayout = (TabLayout) findViewById( R.id.DevelopmentTablayout );
        viewPager = findViewById( R.id.Development_Viewpager );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        signup = findViewById( R.id.signup_btn_home);
        image_btn = findViewById( R.id.image_btn );
        video_btn = findViewById( R.id.smart_view_btn );
        frag = findViewById( R.id.view_frag_develop );

    }
    private void video_Btn(){
        video_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace( R.id.view_frag_develop, new VideoFrag() ).commit();
//                hideDetailView.setVisibility( View.GONE );
//                hideSmartview.setVisibility( View.VISIBLE );
                video_btn.setBackground( ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_blue_button));
                video_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.white) );
                image_btn.setBackgroundColor( Color.parseColor("#EDEAEA"));
                image_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.black) );

            }
        } );

    }
    private void image_Btn(){
        image_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     hideSmartview.setVisibility( View.GONE );
           //     hideDetailView.setVisibility( View.VISIBLE );
                getSupportFragmentManager().beginTransaction().replace( R.id.view_frag_develop, new ImagesFrag() ).commit();

                image_btn.setBackground( ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_blue_button));
                image_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.white) );
                video_btn.setBackgroundColor( Color.parseColor("#EDEAEA"));
                video_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.black) );
            }
        } );
    }
    private void tablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Images") );
        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        DevelopmentTabAdapter developmentTabAdapter= new DevelopmentTabAdapter( getSupportFragmentManager(), tabLayout.getTabCount() );
        viewPager.setAdapter( developmentTabAdapter);

        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition() );


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(  tabLayout) );


    }
    private void noti_count(){
        // notification count api
        Notification_list_api();
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
    private void guest(){
        if(guest != null){
            Noti_bg.setVisibility( View.GONE );
            user.setVisibility( View.GONE );
            notification.setVisibility( View.GONE );
            signup.setVisibility( View.VISIBLE );
            signup.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a  = new Intent(getApplicationContext(), Signup.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a );
                }
            } );
        }
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