package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.PaymentDetail.PaymentDetailRequest;
import com.HS.Topcity.ApiModels.PaymentDetail.PaymentDetailResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailReceipt extends AppCompatActivity {
    ImageView user,notification;
    TextView signUP;
    LinearLayout online_payment;
    EditText token,registration_no,plot_size,typeOfPlot,rupe,email,name,plot,block,transcation,account,cnic;
    TextView Noti_count;
    LinearLayout Noti_bg;
    LinearLayout back,signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+";
    PaymentDetailRequest paymentDetailRequest = new PaymentDetailRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_payment_detail_receipt );
        getSupportActionBar().hide();

  //      getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        id();
        // cnic auto dash functionality
        cnic_field_funtionality();
        // notification and user profile or back to pervious screen funtionality
        noti_user_and_back();
        // update
        update_btn();

        // notifcation count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        signup = findViewById( R.id.signup_btn_home);
        // guest work
        guest();

    }
    private void id(){
        back = findViewById( R.id.back_to_paymentDetail_recipt);
        user = findViewById( R.id.user_icon_paymentDetailrecipt);
        notification = findViewById( R.id.notification_paymentDetailrecipt );
        online_payment = findViewById( R.id.online_payment_receipt_update );
        token= findViewById( R.id.token );
        registration_no= findViewById( R.id.registration_no );
        plot_size= findViewById( R.id.plot_size );
        typeOfPlot= findViewById( R.id.type_of_plot_ex_corner_side );
        rupe= findViewById( R.id.rupee );
        email= findViewById( R.id.email );
        name= findViewById( R.id.name );
        plot= findViewById( R.id.plot );
        block= findViewById( R.id.block );
        transcation= findViewById( R.id.transcation_id );
        account= findViewById( R.id.amountInWords );
        cnic= findViewById( R.id.cnic );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );

    }

    private void update_btn(){

        online_payment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token.getText().toString().equals( "" )){
                    token.setError( "This field is required" );
                }
                if(registration_no.getText().toString().equals( "" )){
                    registration_no.setError( "This field is required" );
                }
                if(email.getText().toString().equals( "" )){
                    email.setError( "This field is required" );
                }
                else {
                    if(!email.getText().toString().trim().matches(emailPattern)){
                        email.setError( "Invalid Email" );
                    }
                }
                if(transcation.getText().toString().equals( "" )){
                    transcation.setError( "This field is required" );
                }
                if(cnic.getText().toString().equals( "" )){
                    cnic.setError( "This field is required" );
                }
                else {
                    if(cnic.length() < 15){
                        cnic.setError( "This Cnic is Invaild" );
                    }
                }

                if(token.getText().toString() != "" && registration_no.getText().toString().toString() != "" && email.getText().toString().toString() != "" &&
                         transcation.getText().toString().toString() != "" &&  cnic.getText().toString().toString() != "" && email.getText().toString().trim().matches(emailPattern) &&  cnic.length() == 15 ){
                    api_payment_detail();
                }

//                if(token.getText().toString().equals( "" ) || registration_no.getText().toString().equals( "" ) || email.getText().toString().equals( "" )
//                        || transcation.getText().toString().equals( "" ) || cnic.getText().toString().equals( "" ) || plot.getText().toString().equals( "" ) || block.getText().toString().equals( "" ) || name.getText().toString().equals( "" )){
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder( PaymentDetailReceipt.this);
//                    builder1.setMessage("Token, Registration Number , Email, Transcation ID and CNIC Number are required fields.");
//                    builder1.setTitle( "Alert" );
//                    builder1.setCancelable(false);
//                    builder1.setPositiveButton(
//                            "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // complaint with draw api call
//                                    dialog.cancel();
//                                }
//                            });
//                    builder1.show();
//                }
//
            }
        } );


    }
    private void cnic_field_funtionality(){
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
    private void payment_Detail_request(){

        paymentDetailRequest.setToken( token.getText().toString() );
        paymentDetailRequest.setCNIC( cnic.getText().toString() );
        paymentDetailRequest.setBlock( block.getText().toString() );
        paymentDetailRequest.setRupees( rupe.getText().toString() );
        paymentDetailRequest.setEmail(  email.getText().toString());
        paymentDetailRequest.setAmountInWords(account.getText().toString()  );
        paymentDetailRequest.setPlotSize(  plot_size.getText().toString());
        paymentDetailRequest.setRegistrationNumber(  registration_no.getText().toString());
        paymentDetailRequest.setTransactionId(  transcation.getText().toString());
        paymentDetailRequest.setTypeOfPlot(typeOfPlot.getText().toString()  );
        paymentDetailRequest.setPlot_No(plot.getText().toString()  );
      paymentDetailRequest.setReceivedWith(name.getText().toString()  );



    }
    private void api_payment_detail(){
        String tokens = PreferenceData.getPrefUserToken(PaymentDetailReceipt.this);
        ProgressDialog progress = new ProgressDialog(PaymentDetailReceipt.this);

     ApiInterface apiInterface = ApiUtils.postSignUpService();

     // payment detail requestd data
        payment_Detail_request();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
         progress.show();



        Call<PaymentDetailResponse> paymentDetailResponseCall = apiInterface.paymentDetailResponse(tokens,paymentDetailRequest);
        paymentDetailResponseCall.enqueue(new Callback<PaymentDetailResponse>() {
            @Override
            public void onResponse(Call<PaymentDetailResponse> call, Response<PaymentDetailResponse> response) {
                PaymentDetailResponse user = response.body();


                if (user != null) {

                if (user.status != false){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder( PaymentDetailReceipt.this);
                    builder1.setMessage(user.message.toString());
                    builder1.setTitle( "Alert" );
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // complaint with draw api call
                                    token.setText( "" );
                                    registration_no.setText( "" );
                                    plot_size.setText( "" );
                                    typeOfPlot.setText( "" );
                                    rupe.setText( "" );
                                    email.setText( "" );
                                    name.setText( "" );
                                    plot.setText( "" );
                                    block.setText( "" );
                                    transcation.setText( "" );
                                    account.setText( "" );
                                    cnic.setText( "" );
                                    dialog.cancel();
                                }
                            });
                    builder1.show();
                }

                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<PaymentDetailResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
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
        token.setText( "" );
        registration_no.setText( "" );
        plot_size.setText( "" );
        typeOfPlot.setText( "" );
        rupe.setText( "" );
        email.setText( "" );
        name.setText( "" );
        plot.setText( "" );
        block.setText( "" );
        transcation.setText( "" );
        account.setText( "" );
        cnic.setText( "" );
        Notification_list_api();
        super.onResume();

    }
}