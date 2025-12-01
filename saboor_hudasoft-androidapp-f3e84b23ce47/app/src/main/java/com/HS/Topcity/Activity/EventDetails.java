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

import com.HS.Topcity.Adapters.EventDetailParentAdapter;
import com.HS.Topcity.ApiModels.EventDetails.EventDetailsResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.EventDetailsImageModel;
import com.HS.Topcity.Models.EventDetailsModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetails extends AppCompatActivity {

    ImageView notification,user;
    public static TextView event_details_name;
    RecyclerView Event_Details;
    ApiInterface apiInterface;
    LinearLayout signup,back;
    ArrayList<EventDetailsModel> eventDetails;
    ArrayList<EventDetailsImageModel> eventDetailsImageModels;
    TextView Noti_count;
    LinearLayout Noti_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_event_details );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
 //       getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // noti count
        noti_count();
        // notification , userProfile , back pressed Function
           noti_user_back();
      //  Event_Details_Api_Intregration();
        Event_Details_Api_Intregration_test();
        // guest work
       guest();



    }

    private void ids(){
        notification = findViewById( R.id.notification_Event_details);
        user = findViewById( R.id.user_icon_EventDetails );
        back = findViewById( R.id.back_to_EventDetails);
        event_details_name = findViewById( R.id.name_eventdetails );
        Event_Details = findViewById( R.id.event_details_list);
        signup = findViewById( R.id.signup_btn_home);
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
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

    private void  Event_Details_Api_Intregration_test(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // news Announcement api intregration

        Call<EventDetailsResponse> eventDetailResponse = apiInterface.eventDetailResponse(token);
        eventDetailResponse.enqueue(new Callback<EventDetailsResponse>() {
            @Override
            public void onResponse(Call<EventDetailsResponse> call, Response<EventDetailsResponse> response) {
                EventDetailsResponse user = response.body();


                if (user != null) {

                    eventDetails = new ArrayList<>();
                    eventDetailsImageModels = new ArrayList<>();

                    if (user.Eventsmodel != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.Eventsmodel.size(); i++) {
                            EventDetailsModel model = new EventDetailsModel();
                            model.setEventName( user.Eventsmodel.get( i ).eventName );
                            model.setDescriptions( user.Eventsmodel.get( i ).descriptions );
                            model.setEventDate(user.Eventsmodel.get( i ).EventDate  );
                            model.setEventTime(user.Eventsmodel.get( i ).EventTime  );
                            model.setId( user.Eventsmodel.get( i ).id );
                            model.setImages( user.Eventsmodel.get( i ).image );
                            eventDetails.add( model );
//                            for(int j = 0 ; j < user.Eventsmodel.get( i ).image.size();j++)
//                            {
//                                EventDetailsImageModel eventDetailsImageModel = new  EventDetailsImageModel();
//                                eventDetailsImageModel.setImage( user.Eventsmodel.get( i ).image.get( i ) );
//                                eventDetailsImageModels.add(eventDetailsImageModel);
//                            }







                        }
                        EventDetailParentAdapter evenDetailsAdapter = new EventDetailParentAdapter( getApplicationContext(), eventDetails, R.layout.event_detail_parent_layout );
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );
                        Event_Details.setLayoutManager( linearLayoutManager2 );
                        Event_Details.setNestedScrollingEnabled( true );
                        Event_Details.setAdapter( evenDetailsAdapter );

                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<EventDetailsResponse> call, Throwable t) {
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