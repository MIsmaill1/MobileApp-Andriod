package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.ComplainDetailImagesAdapter;
import com.HS.Topcity.ApiModels.ComplaintDetail.ComplaintDetailRequest;
import com.HS.Topcity.ApiModels.ComplaintDetail.ComplaintDetailResponse;
import com.HS.Topcity.ApiModels.ComplaintWithDraw.ComplaintWithDrawRequest;
import com.HS.Topcity.ApiModels.ComplaintWithDraw.ComplaintWithDrawResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplaintDetailModel;
import com.HS.Topcity.Models.ComplaintImagesModel;
import com.HS.Topcity.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintDetails extends AppCompatActivity {
    ImageView notification,user;
    TextView comp_num,comp_Title,comp_day,comp_address,comp_Desrip,comp_status,comp_subject,signup
            ,dates,time,user_name,complaint_remarks;
    LinearLayout withDraw_btn,comp_status_bg,back,complaint_remarks_layout;
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    String dateFromDB;
    ComplaintDetailRequest complaintDetailRequest = new ComplaintDetailRequest();
    ComplaintWithDrawRequest complaintWithDrawRequest = new ComplaintWithDrawRequest();
    ArrayList<ComplaintDetailModel> complaintDetailModels;
    List<ComplaintImagesModel> images;
    TextView Noti_count;
    LinearLayout Noti_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_complaint_details );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // ids
        ids();

        // notification , userProfile , back pressed Function
        noti_user_and_back();

        // Notification count funtionality
        noti_count();

        // api
        api_Complaint_detail();

        // with draw action
        WithDraw_btn();
    }
    private void ids(){
        user = findViewById( R.id.user_icon_Complaint_detail );
        notification = findViewById( R.id.notification_Complaint_detail );
        back = findViewById( R.id.back_to_Complaint_detail );
        signup = findViewById( R.id.signup_text_Complaint_detail );
        comp_subject = findViewById( R.id.complaint_subject );
        comp_num = findViewById( R.id.complaint_number );
        comp_status = findViewById( R.id.complaint_status);
        comp_status_bg = findViewById( R.id.complaint_status_bg);
        comp_Title = findViewById( R.id.complaint_Titlename );
        comp_day = findViewById( R.id.complaint_days );
        comp_address = findViewById( R.id.complaint_address );
        comp_Desrip = findViewById( R.id.complaint_descrip );
        withDraw_btn = findViewById( R.id.complaint_withDraw_btn );
        recyclerView = findViewById( R.id.complain_Imagelist );
        dates = findViewById( R.id.complain_detail_date );
        time = findViewById( R.id.complain_detail_time);
        user_name = findViewById( R.id.name);
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        complaint_remarks = findViewById( R.id.complaint_remarks );
        complaint_remarks_layout = findViewById( R.id.complaint_remarks_layout );

    }
    private void noti_count(){

        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        // notificaton count api
        Notification_list_api();
    }
    private  void WithDraw_btn(){
        withDraw_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ComplaintDetails.this);
                builder1.setMessage("Are you sure you want to withdraw this complaint");
                builder1.setTitle( "Alert" );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // complaint with draw api call
                            api_Complaint_withDraw();
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


    private void api_Complaint_detail(){
        apiInterface = ApiUtils.postSignUpService();
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        // progress.show();
        complaintDetailRequest.setComplaintId(Integer.parseInt( getIntent().getStringExtra( "id" ) ) );
        Call<ComplaintDetailResponse> shopUsedVehicle = apiInterface.complaintDetailResponse(token, complaintDetailRequest);
        shopUsedVehicle.enqueue(new Callback<ComplaintDetailResponse>() {
            @Override
            public void onResponse(Call<ComplaintDetailResponse> call, Response<ComplaintDetailResponse> response) {
                ComplaintDetailResponse user = response.body();
                if(user != null) {

                    complaintDetailModels = new ArrayList<>();

                    images = new ArrayList<>();
                    if(user.complaintModel != null){

                        comp_Title.setText( user.complaintModel.getComplainName() );
                        comp_subject.setText( user.complaintModel.getComplaintSubject() );
                        comp_num.setText( user.complaintModel.getComplainNumber() );
                        comp_status.setText( user.complaintModel.getComplaintStatus() );
                        comp_address.setText( user.complaintModel.getPropertyAddress() );
                        comp_Desrip.setText( user.complaintModel.getComplaintDetails() );
                        user_name.setText( user.complaintModel.getUserName() );
                        if(user.complaintModel.getComplaint_remarks() != null && !user.complaintModel.getComplaint_remarks().equals( "" )){
                            complaint_remarks.setText( user.complaintModel.getComplaint_remarks() );
                        }
                        else {
                            complaint_remarks_layout.setVisibility( View.GONE );
                        }

                        if( user.complaintModel.getDaysAgo() == 0){
                            comp_day.setText("Today" );
                        }
                        else if(user.complaintModel.getDaysAgo() <30){
                            comp_day.setText( String.valueOf( user.complaintModel.getDaysAgo() ) +" "+ "Days Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=30 && user.complaintModel.getDaysAgo() <60){
                            comp_day.setText( "1 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=60 && user.complaintModel.getDaysAgo() <90){
                            comp_day.setText( "2 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=90 && user.complaintModel.getDaysAgo() <120){
                            comp_day.setText( "3 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=120 && user.complaintModel.getDaysAgo() <150){
                            comp_day.setText( "4 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=150 && user.complaintModel.getDaysAgo() <180){
                            comp_day.setText( "5 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=180 && user.complaintModel.getDaysAgo() <210){
                            comp_day.setText( "6 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=210 && user.complaintModel.getDaysAgo() <240){
                            comp_day.setText( "7 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=240 && user.complaintModel.getDaysAgo() <270){
                            comp_day.setText( "8 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=300 && user.complaintModel.getDaysAgo() <330){
                            comp_day.setText("9 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=330 && user.complaintModel.getDaysAgo() <360){
                            comp_day.setText( "10 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=390 && user.complaintModel.getDaysAgo() <420){
                            comp_day.setText("11 Month Ago" );
                        }
                        else if(user.complaintModel.getDaysAgo() >=420 && user.complaintModel.getDaysAgo() <830){
                            comp_day.setText( "1 year" );
                        }


                        dateFromDB =  user.complaintModel.getComplainDateTime() ;
                        // date time function     "yyyy-MMM-dd'T'hh:mm:ss.Z"
                        try {
                            SimpleDateFormat parser = new SimpleDateFormat( "yyyy-MM-dd'T'hh:mm:ss.S");
                            Date date = parser.parse(dateFromDB);

                            String year = (String) DateFormat.format("yyyy", date); // 2013
                            String monthString  = (String) DateFormat.format("MMM",  date); // Jun
                            String day = (String) DateFormat.format("dd",   date); // 20
                            String hour = (String) DateFormat.format( "hh", date );
                            String minutes = (String) DateFormat.format( "mm", date );
                            String amTopm = (String) DateFormat.format( "a", date );

                            dates.setText(day+"-"+monthString+"-"+year);
                            SimpleDateFormat utcFormat = new SimpleDateFormat("hh:mm a");
                            utcFormat.setTimeZone( TimeZone.getTimeZone("UTC"));

                            Date date1 = utcFormat.parse(hour+":"+minutes+" " + amTopm);

                            SimpleDateFormat deviceFormat = new SimpleDateFormat("hh:mm a");
                            deviceFormat.setTimeZone(TimeZone.getDefault()); //Device timezone

                            String convertedTime = deviceFormat.format(date1);


                            time.setText( convertedTime );
                          //  time.setText( hour+":"+minutes+" " + amTopm );

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                        // change background color change
                        chnage_Status_bg_color();

                        if(user.complaintModel.getComplaintImages().size() != 0)
                        {
                            for (int i = 0 ; i < user.complaintModel.getComplaintImages().size();i++ )
                            {
                                ComplaintImagesModel complaintImagesModel = new ComplaintImagesModel();
                               complaintImagesModel.setImage(  user.complaintModel.getComplaintImages().get( i ));
                                images.add( complaintImagesModel);
                            }
                            ComplainDetailImagesAdapter complainDetailImagesAdapter = new ComplainDetailImagesAdapter( getApplicationContext(),images,R.layout.complaint_images_layout );
//                            int fullSpanSize = 2;
//                            int normalSpanSize = 1;
//
//                            GridLayoutManager layout = new GridLayoutManager(getApplicationContext(), fullSpanSize);
//                            layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                                @Override
//                                public int getSpanSize(int position) {
//                                    return position == 2? fullSpanSize : normalSpanSize;
//                                }
//                            });
                           // recyclerView.setLayoutManager(layout);
                            recyclerView.setLayoutManager( new GridLayoutManager( getBaseContext(),2 ) );
                            recyclerView.setAdapter(complainDetailImagesAdapter );
                        }

                        // need status bg color change








                    }


                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<ComplaintDetailResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void chnage_Status_bg_color(){
        if(comp_status.getText().toString().equals( "Withdraw" )){
            withDraw_btn.setVisibility( View.GONE );
            comp_status_bg.setBackgroundColor( getResources().getColor( R.color.red  ));
        }
        else   if(comp_status.getText().toString().equals( "Close" ) || comp_status.getText().toString().equals( "Invalid" ) || comp_status.getText().toString().equals( "OverTime" ))
        {
            comp_status_bg.setBackgroundColor( getResources().getColor( R.color.red  ));
        }
        else  if(comp_status.getText().toString().equals( "Active" )){
            comp_status_bg.setBackgroundColor( getResources().getColor( R.color.dark_green  ));
        }
        else   if(comp_status.getText().toString().equals( "Pending" ) || comp_status.getText().toString().equals( "InProgress" ))
        {
            comp_status_bg.setBackgroundColor( getResources().getColor( R.color.bg_mhendi_color ));
        }
    }

    private void api_Complaint_withDraw(){
        apiInterface = ApiUtils.postSignUpService();
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(ComplaintDetails.this);
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
         progress.show();
        complaintWithDrawRequest.setComplaintId(Integer.parseInt( getIntent().getStringExtra( "id" ) ) );
        Call<ComplaintWithDrawResponse> complaintWithDrawResponseCall = apiInterface.COMPLAINT_WITH_DRAW_RESPONSE_CALL(token, complaintWithDrawRequest);
        complaintWithDrawResponseCall.enqueue(new Callback<ComplaintWithDrawResponse>() {
            @Override
            public void onResponse(Call<ComplaintWithDrawResponse> call, Response<ComplaintWithDrawResponse> response) {
                ComplaintWithDrawResponse user = response.body();
                if(user != null) {

                    if(user.status != false){
                    Intent a  = new Intent( getApplicationContext(),ComplaintWithDrawSuccess.class);
                    startActivity( a );
                    finish();
                    }


                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<ComplaintWithDrawResponse> call, Throwable t) {
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