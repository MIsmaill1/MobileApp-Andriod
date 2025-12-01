package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.FaqsListAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.FAQsModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FandQ extends AppCompatActivity {
    private ArrayList<FAQsModel> faQsModels;
  public static   FaqsListAdapter faqsAdapter;
    RecyclerView recyclerView;
    ImageView user,notification;
    TextView Noti_count;
    LinearLayout Noti_bg;
    LinearLayout back,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fand_q );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        id();
        // guest user funtionality
        guest();
        // notification , userProfile , back pressed Function
        noti_user_and_back();
        // notification count funtionality
        noti_count();
        // faq recycle view adapter attach
        adpater_attach();

    }
    private void id(){

        back = findViewById( R.id.back_to_FAQS);
        user = findViewById( R.id.user_icon_FAQS);
        notification = findViewById( R.id.notification_FAQS);
        recyclerView = findViewById( R.id.faqs_list );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        signup = findViewById( R.id.signup_btn_home);
    }
    private void noti_count(){
        // notifcaition count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
    }
    private void adpater_attach(){
        dumyData();
         faqsAdapter = new FaqsListAdapter(getApplicationContext(), faQsModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( faqsAdapter );

        faqsAdapter.notifyDataSetChanged();
    }
    private void dumyData(){
        faQsModels = new ArrayList<>();
        faQsModels.add( new FAQsModel(R.string.q1,R.string.a1) );
        faQsModels.add( new FAQsModel(R.string.q2,R.string.a2) );
        faQsModels.add( new FAQsModel(R.string.q3,R.string.a3) );
        faQsModels.add( new FAQsModel(R.string.q4,R.string.a4) );
        faQsModels.add( new FAQsModel(R.string.q5,R.string.a5) );
        faQsModels.add( new FAQsModel(R.string.q6,R.string.a6) );
        faQsModels.add( new FAQsModel(R.string.q7,R.string.a7) );
        faQsModels.add( new FAQsModel(R.string.q8,R.string.a8) );
        faQsModels.add( new FAQsModel(R.string.q9,R.string.a9) );



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
//    @Override
//    public boolean onOptionsItemSelected(MenuItem items) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = items.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(items);
//    }
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