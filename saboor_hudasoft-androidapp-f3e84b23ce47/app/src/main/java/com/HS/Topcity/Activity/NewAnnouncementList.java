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

import com.HS.Topcity.Adapters.NewsAnnouncementAllListAdapter;
import com.HS.Topcity.ApiModels.NewsAndAnnouncements.NewsAndAnnouncementsResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAnnouncementList extends AppCompatActivity {
    ApiInterface apiInterface;

    ArrayList<NewsAnnouncementModel> newsAnnouncementModels;
    RecyclerView newsaAnnoceAllList;
    TextView status,Username;
    ImageView notification,user;
    LinearLayout signup,back;
    TextView Noti_count;
    LinearLayout Noti_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_announcement_list );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // guest
        guest();
        // Notification count funtionality
        noti_count();
        // news announcement list api intrgration
        NewsAnnouncement_lists_api();
        // notification and user or back to privous screen funtionality
        back_noti_user();

    }
    private void ids(){
        newsaAnnoceAllList = findViewById( R.id.news_and_announcements_list );
        back = findViewById( R.id.back_to_news );
        notification = findViewById( R.id.notification_news);
        user = findViewById( R.id.user_icon_news);
        signup = findViewById( R.id.signup_btn_home);
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
    }
    private void back_noti_user(){
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
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Notification.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
    }
    private void NewsAnnouncement_lists_api(){
        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // news Announcement api intregration

        Call<NewsAndAnnouncementsResponse> newsAndAnnouncementsResponseCall = apiInterface.newsAndAnnouncementResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<NewsAndAnnouncementsResponse>() {
            @Override
            public void onResponse(Call<NewsAndAnnouncementsResponse> call, Response<NewsAndAnnouncementsResponse> response) {
                NewsAndAnnouncementsResponse user = response.body();


                if (user != null) {

                    newsAnnouncementModels = new ArrayList<>();
                    if (user.newsAndAnnouncementModel != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.newsAndAnnouncementModel.size(); i++) {
                            NewsAnnouncementModel model = new NewsAnnouncementModel();
                            model.setDateofNews( user.newsAndAnnouncementModel.get( i ).DateofNews );
                            model.setTimeOfNews(  user.newsAndAnnouncementModel.get( i ).TimeOfNews );
                            model.setNewsName( user.newsAndAnnouncementModel.get( i ).newsName );
                            model.setDescriptions( user.newsAndAnnouncementModel.get( i ).Descriptions );
                            model.setNotes( user.newsAndAnnouncementModel.get( i ).notes );
                            model.setImage( user.newsAndAnnouncementModel.get( i ).Image );
                            model.setId( user.newsAndAnnouncementModel.get( i ).id );
                            newsAnnouncementModels.add( model );
                        }

                        NewsAnnouncementAllListAdapter newsAnnouncementAdapter = new NewsAnnouncementAllListAdapter( getApplicationContext(), newsAnnouncementModels, R.layout.news_annocement_list_layout);

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL,false );
                        newsaAnnoceAllList.setLayoutManager( linearLayoutManager2 );
                        newsaAnnoceAllList.setNestedScrollingEnabled(true);
                        newsaAnnoceAllList.setAdapter( newsAnnouncementAdapter);




                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<NewsAndAnnouncementsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }
    private void noti_count(){
        // Notification count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
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