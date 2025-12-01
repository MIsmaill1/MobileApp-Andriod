package com.HS.Topcity.Activity;

import static android.content.ContentValues.TAG;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.NotificationListAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.NotificationListModel;
import com.HS.Topcity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity  {

    ApiInterface apiInterface;
    RecyclerView notification_list;
    ImageView notification,user;
    Button click;

    ArrayList<NotificationListModel> notificationListModels;
    LinearLayout no_notification,back;
 public static   String check_noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification );

        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // All ids
        ids();
         user_and_back();
         Notification_list_api();




        click.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = Notification.this;

                //   String uniqueID = UUID.randomUUID().toString();
                String uniqueID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener( new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful()) {

                            String token = task.getResult().getToken();


                        }

                    }
                });


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
                            }
                        });

            }
        } );
    }

    private void ids(){
        click = findViewById( R.id.notification);
        back = findViewById( R.id.back_to_Notification);
        user = findViewById( R.id.user_icon_notifiction);
        notification_list = findViewById( R.id.Notifiction_list );
        no_notification = findViewById( R.id.no_notification_text );
    }

    private void user_and_back(){
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

    private void Notification_list_api(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // news Announcement api intregration

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {

                    notificationListModels = new ArrayList<>();
                    if (user.notificationsList != null && user.notificationsList.size() != 0) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.notificationsList.size(); i++) {
                            NotificationListModel model = new NotificationListModel();
                            model.setNotificationToId( user.notificationsList.get( i ).notificationToId );
                            model.setNotificationSubject(  user.notificationsList.get( i ).notificationSubject );
                            model.setNotificationView( user.notificationsList.get( i ).isNotificationView );
                            model.setNotificationDetails( user.notificationsList.get( i ).notificationDetails );
                            model.setNotificationImage( user.notificationsList.get( i ).getNotificationImage() );
                            model.setDaysAgo( user.notificationsList.get( i ).getDaysAgo() );
                            notication_count  = String.valueOf(user.NotificationBadgeCount  ) ;
                            notificationListModels.add( model );
                        }

                       NotificationListAdapter notificationListAdapter = new NotificationListAdapter( getApplicationContext(), notificationListModels,Notification.this);
//                        notification_list.setAdapter(new NotificationListAdapter( getApplicationContext(), notificationListModels, new NotificationListAdapter.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(NotificationListModel notificationListModels) {
//                                Toast.makeText( getApplicationContext(), "notificationListModels.getNotificationSubject()", Toast.LENGTH_SHORT ).show();
//
//                            }
//                        }));

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );
                        notification_list.setLayoutManager( linearLayoutManager2 );
                        notification_list.setNestedScrollingEnabled(true);
                        notification_list.setAdapter( notificationListAdapter );
//                        notification_list.setAdapter( new NotificationListAdapter( getApplicationContext(), notificationListModels, new NotificationListAdapter.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(NotificationListModel notificationListModels) {
//
//                            }
//                        } ));







                    }
                    else {
                        no_notification.setVisibility( View.VISIBLE );
                    }
                    progress.dismiss();
                } else {
                    no_notification.setVisibility( View.VISIBLE );
                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                no_notification.setVisibility( View.VISIBLE );
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
//    @Override
//    public void onClick(View view, int position) {
//        // The onClick implementation of the RecyclerView item click
//         NotificationListModel city = notificationListModels.get(position);
//        Toast.makeText( getApplicationContext(), position, Toast.LENGTH_SHORT ).show();
//
//    }
    @Override
    protected void onResume() {
        super.onResume();
//        if(check_noti != null && check_noti.equals( "check" )){
//            check_noti = null;
//            Intent a = new Intent(getApplicationContext(),Notification.class);
//            startActivity( a );
//            finish();
//
//        }

    }
}