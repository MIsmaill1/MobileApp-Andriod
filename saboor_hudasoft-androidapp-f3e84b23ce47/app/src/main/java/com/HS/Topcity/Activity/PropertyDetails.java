package com.HS.Topcity.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.PropertyDetailsImageAdapter;
import com.HS.Topcity.Adapters.PropertyOwnerDetailsAdapter;
import com.HS.Topcity.ApiModels.PropertyDetail.PropertyDetailsRequest;
import com.HS.Topcity.ApiModels.PropertyDetail.PropertyDetailsResponse;
import com.HS.Topcity.ApiModels.PropertyimageUpload.PropertyimageUploadResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.DetailViewModels;
import com.HS.Topcity.Models.PropertyDetailsimageModel;
import com.HS.Topcity.Models.PropertyOwnersDetailModels;
import com.HS.Topcity.Models.SmartViewModels;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class PropertyDetails extends AppCompatActivity {

    TextView address,block,plotno,propertyType,registrationNumber,name,phone,currentAddres
            ,installPay,totalBal,currentOut,surchargePay,surchargePaid,surchargeWaived,surchargeBal
            ,netOut,grossPay,shared_acc_no,user_count,share_now_btn,view_details_btn
            ,smartView_btn,detailView_btn;
    TextView smart_total_paid,smart_instal_pay,smart_total_bal, smart_currentOut,smart_surchargePay,smart_surchargePaid,smart_surchargeWaived,smart_surchargeBal
            ,smart_netOut,smart_grossPay;
    ProgressBar progressBar_smart_total_paid, progressBar_smart_currentOut,progressBar_smart_surchargePay,progressBar_smart_surchargePaid,progressBar_smart_surchargeWaived,progressBar_smart_surchargeBal
            ,progressBar_smart_netOut,progressBar_smart_grossPay;
    ImageView  owner_image,shared_user_image1,shared_user_image2,shared_user_image3,add_image;
    ShapeableImageView bg_image;
    CardView shareImage1,shareImage2,shareImage3;
    ApiInterface apiInterface;
    RecyclerView recyclerView,owner_ship_list;
    LinearLayout image_and_shareCount,hideSmartview,hideDetailView;
    PropertyDetailsRequest propertyDetailsRequest = new PropertyDetailsRequest();
    ArrayList<PropertyOwnersDetailModels> propertyOwnersDetailModels;
    ArrayList<DetailViewModels> detailViewModels;
    ArrayList<SmartViewModels> smartViewModels;
    ScrollingPagerIndicator recyclerIndicator;
    ArrayList<PropertyDetailsimageModel> propertyImagesList;

    LinearLayout pro_share_hide;
    ArrayList<Bitmap> image;
    ArrayList<Uri> images =  new ArrayList<>();
    ArrayList urlStrings;
    ArrayList<File> files;
    Uri tempUri;
    File finalFile;
    Map<String, Object> jsonParams;
    JSONArray json = new JSONArray() ;
    JSONObject json1;
    String check,addres,ids,owner;

    LinearLayout back,list,no_account_text,hide_account;
    FirebaseStorage storage;
    StorageReference storageReference;
    ArrayList<UploadTask> upload ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_property_details );
        getSupportActionBar().hide();
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        ids();


        // data fill
        indent_data_put();
       // back pressed buton
        back();
        // share now button
        Share_now_btn();
        // api function call
        property_detail_api();
        //smart view button
        smart_view_Btn();
        // details view button
        detail_view_Btn();
        // view details btn
        View_Details_Account_btn();
        // add image
        add_image();

        hide_payment_Detail();


    }

    private void ids(){
        address = findViewById( R.id.propertyDetail_Address);
        bg_image = findViewById( R.id.details_image );
        back = findViewById( R.id.backtodetails );
        block = findViewById( R.id.property_detail_block );
        plotno = findViewById( R.id.property_detail_plot );
        propertyType= findViewById( R.id.property_detail_propertyType );
        registrationNumber= findViewById( R.id.property_detail_registerNo );
        name= findViewById( R.id.property_detail_userName );
        phone= findViewById( R.id.property_detail_userNumber );
        currentAddres = findViewById( R.id.property_detail_currentAddress );
        installPay= findViewById( R.id.property_detail_installmentPayable );
        totalBal= findViewById( R.id.property_detail_totalBalance );
        currentOut= findViewById( R.id.property_detail_Current_Outstanding );
        surchargePay= findViewById( R.id.property_detail_SurchargePayable );
        surchargePaid= findViewById( R.id.property_detail_SurchargePaid );
        surchargeWaived= findViewById( R.id.property_detail_SurchargeWaived );
        surchargeBal = findViewById( R.id.property_detail_Surchargebalance );
        netOut= findViewById( R.id.property_detail_NetOutstanding );
        grossPay = findViewById( R.id.property_detail_GrossPayable );
        shared_acc_no = findViewById( R.id.shared_account_users );
        owner_image = findViewById( R.id.user_image );
        shared_user_image1 = findViewById( R.id.Shareuser_image1 );
        shared_user_image2 = findViewById( R.id.Shareuser_image2 );
        shared_user_image3 = findViewById( R.id.Shareuser_image3 );
        user_count = findViewById( R.id.shared_account_users_Extra );
        share_now_btn = findViewById( R.id.shared_property );
        view_details_btn = findViewById( R.id.propertyDetail_viewDetail_btn );
        image_and_shareCount = findViewById( R.id.property_share_acc_image );

        owner_ship_list =  findViewById( R.id.ownerShip_list );

        pro_share_hide =  findViewById( R.id.isDetail);
        // card view Start
        shareImage1= findViewById( R.id.Shareuser1);
        shareImage2= findViewById( R.id.Shareuser2 );
        shareImage3= findViewById( R.id.Shareuser3 );
        //end

        recyclerView = findViewById( R.id.property_Details_images );
        detailView_btn = findViewById( R.id.detailView_btn );
        smartView_btn = findViewById( R.id.smart_view_btn );
        hideSmartview = findViewById( R.id.property_smart_view );
        hideDetailView = findViewById( R.id.property_detail_view );

        // smart view details

        smart_total_paid= findViewById( R.id.property_smart_totalPaid );
        smart_instal_pay = findViewById( R.id.property_smart_instal_pay );
        smart_total_bal= findViewById( R.id.property_smart_totalbal );
        smart_currentOut = findViewById( R.id.property_smart_Current_Outstanding );
        smart_surchargePay = findViewById( R.id.property_smart_SurchargePayable);
        smart_surchargePaid = findViewById( R.id.property_smart_SurchargePaid );
        smart_surchargeWaived = findViewById( R.id.property_smart_SurchargeWaived );
        smart_surchargeBal = findViewById( R.id.property_smart_Surchargebalance);
        smart_netOut = findViewById( R.id.property_smart_NetOutstanding );
        smart_grossPay = findViewById( R.id.property_smart_GrossPayable );


        // progress

        progressBar_smart_total_paid = findViewById( R.id.total_paid_progress );
        progressBar_smart_currentOut = findViewById( R.id.current_outstanding_progress );
     //   progressBar_smart_surchargePay = findViewById( R.id.SurchargePayable_progress );
        progressBar_smart_surchargePaid = findViewById( R.id.SurchargePaid_progress );
        progressBar_smart_surchargeWaived = findViewById( R.id.SurchargeWaived_progress );
        progressBar_smart_surchargeBal = findViewById( R.id.Surchargebalance_progress );
        progressBar_smart_netOut =  findViewById( R.id.NetOutstanding_progress );
        progressBar_smart_grossPay = findViewById( R.id.GrossPayable_progress );
        recyclerIndicator = findViewById( R.id.indicato1r );
        add_image = findViewById( R.id.addimage );
        list = findViewById( R.id.list_dot );
        no_account_text = findViewById( R.id.no_Acconunts );
        hide_account = findViewById( R.id.shared );


    }
    private void hide_payment_Detail(){
        if (getIntent().getStringExtra( "isOwner" ).equals( "false" )){
            pro_share_hide.setVisibility( View.GONE );
            add_image.setVisibility( View.GONE );
        }
        else {
            pro_share_hide.setVisibility( View.VISIBLE );
        }
    }
    private void indent_data_put(){
        Glide.with( this ).load( getIntent().getStringExtra( "image" ) ).into( bg_image );
        address.setText( getIntent().getStringExtra( "address" )  );
        addres = getIntent().getStringExtra( "address" );
      owner =  getIntent().getStringExtra( "isOwner" );
    }

    private void back(){
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void Share_now_btn(){
        share_now_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = "check";
                Intent a = new Intent(PropertyDetails.this, SharedAccount.class);
                startActivity(a);
            }
        } );
    }

    private void View_Details_Account_btn(){
        view_details_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = "check";
                Intent a = new Intent(PropertyDetails.this, SharedAccountUserList.class);
                a.putExtra( "id_property" ,getIntent().getStringExtra( "id" ) );
                a.putExtra( "hide_share_button","true" );
                startActivity(a);
            }
        } );
    }

    private void add_image(){
        add_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ContextCompat.checkSelfPermission(PropertyDetails.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        //  intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 11);
                    }
                    else
                    {
                        // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
                        ActivityCompat.requestPermissions(PropertyDetails.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
                    }
                }
                else
                {
                    // if version is below m then write code here,
                }

            }
        } );
    }
    private void detail_view_Btn(){
        detailView_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSmartview.setVisibility( View.GONE );
                hideDetailView.setVisibility( View.VISIBLE );
                detailView_btn.setBackground( ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_blue_button));
                detailView_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.white) );
                smartView_btn.setBackgroundColor( Color.parseColor("#ffffff"));
                smartView_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.black) );
            }
        } );
    }
    private void smart_view_Btn(){
        smartView_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDetailView.setVisibility( View.GONE );
                hideSmartview.setVisibility( View.VISIBLE );
                smartView_btn.setBackground( ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_blue_button));
                smartView_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.white) );
                detailView_btn.setBackgroundColor( Color.parseColor("#ffffff"));
                detailView_btn.setTextColor( ContextCompat.getColor(getApplicationContext(), R.color.black) );

            }
        } );

    }
    private void property_detail_api(){
        apiInterface = ApiUtils.postSignUpService();
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
       // progress.show();
        ids = getIntent().getStringExtra( "id" );
        propertyDetailsRequest.setPropertyId(Integer.parseInt(  ids) );

        Call<PropertyDetailsResponse> shopUsedVehicle = apiInterface.propertyDetailsResponse(token, propertyDetailsRequest);
        shopUsedVehicle.enqueue(new Callback<PropertyDetailsResponse>() {
            @Override
            public void onResponse(Call<PropertyDetailsResponse> call, Response<PropertyDetailsResponse> response) {
                PropertyDetailsResponse user = response.body();
                if(user != null) {

                    smartViewModels = new ArrayList<>();
                    detailViewModels = new ArrayList<>();

                    if(user.propertyDetailsmodels != null){

                        propertyImagesList = new ArrayList<>();


                        for (int i = 0; i < user.propertyDetailsmodels.propertyImages.size(); i++) {
                            PropertyDetailsimageModel eventImagesModel = new PropertyDetailsimageModel();


                            eventImagesModel.setImage( user.propertyDetailsmodels.propertyImages.get(i) );
                            propertyImagesList.add( eventImagesModel );
                        }


                        PropertyDetailsImageAdapter propertyDetailsImageAdapter = new PropertyDetailsImageAdapter( getApplicationContext(), propertyImagesList,R.layout.event_layout );

                    LinearLayoutManager    linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                        recyclerView.setLayoutManager( linearLayoutManager2 );
                        PagerSnapHelper snapHelper = new PagerSnapHelper();
                        snapHelper.attachToRecyclerView(recyclerView);
                        if(getApplicationContext() !=null){
                            int chcek_val = propertyDetailsImageAdapter.getItemCount();
                            if (chcek_val > 1){
                                initBottom( propertyDetailsImageAdapter.getItemCount(),recyclerView );

                            }


                        }



//                        recyclerView.setNestedScrollingEnabled(true);
//                        recyclerIndicator.setSelectedDotColor(getApplicationContext().getResources().getColor( R.color.bg_mhendi_color ));
//                        recyclerIndicator.setDotColor(getApplicationContext().getResources().getColor(R.color.white ));
                       // recyclerIndicator.attachToRecyclerView(recyclerView);
                     //   recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());
                        //or

                        recyclerView.setAdapter( propertyDetailsImageAdapter );
//                        final int speedScroll = 1400;
//                        final Handler handler = new Handler();
//                        final Runnable runnable = new Runnable() {
//                            int count = 0;
//                            boolean flag = true;
//                            @Override
//                            public void run() {
//                                if(count < propertyDetailsImageAdapter.getItemCount()){
//                                    if(count==propertyDetailsImageAdapter.getItemCount()-1){
//                                        flag = false;
//                                    }else if(count == 0){
//                                        flag = true;
//                                    }
//                                    if(flag) count++;
//                                    else count--;
//
//                                    recyclerView.smoothScrollToPosition(count);
//                                    handler.postDelayed(this,speedScroll);
//                                }
//                            }
//                        };
//
//                        handler.postDelayed(runnable,speedScroll);


                        address.setText( user.propertyDetailsmodels.getPropertyAddress() );
                        block.setText( user.propertyDetailsmodels.getBlock() );
                        plotno.setText( user.propertyDetailsmodels.getPlotNumber() );
                        propertyType.setText( user.propertyDetailsmodels.propertyType );
                        registrationNumber.setText( user.propertyDetailsmodels.registrationNumber );


                        propertyOwnersDetailModels = new ArrayList<>();
                        if(user.propertyDetailsmodels != null){

                            for (int a = 0 ; a<user.propertyDetailsmodels.propertyOwnersDetailsModels.size();a++)
                            {
                                PropertyOwnersDetailModels model = new PropertyOwnersDetailModels();
                                model.setPropertyOwnerId( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( a ).propertyOwnerId );
                                model.setPropertyOwnerName( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( a ).propertyOwnerName );
                                model.setPropertyOwnerContactNumber( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( a ).propertyOwnerContactNumber );
                                model.setPropertyOwnerImage( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( a ).propertyOwnerImage );
                                model.setPropertyOwnerCurrentAddress( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( a ).propertyOwnerCurrentAddress );
                                propertyOwnersDetailModels.add( model );
                            }
                            PropertyOwnerDetailsAdapter propertyOwnerDetailsAdapter = new PropertyOwnerDetailsAdapter( getApplicationContext(), propertyOwnersDetailModels,R.layout.property_owner_layout );

                            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                            owner_ship_list.setLayoutManager( linearLayoutManager3 );
                            owner_ship_list.setAdapter( propertyOwnerDetailsAdapter );

                        }




//                        name.setText( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( 0 ).propertyOwnerName );
//                        Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( 0 ).propertyOwnerImage ).into( owner_image );
//                        phone.setText( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( 0 ).propertyOwnerContactNumber );
//
                       // currentAddres.setText( user.propertyDetailsmodels.propertyOwnersDetailsModels.get( 0 ).propertyOwnerCurrentAddress );



                        // Detail View Data
                        installPay.setText( user.propertyDetailsmodels.detailsViewModel.getInstallmentPayable() );
                        totalBal.setText( user.propertyDetailsmodels.detailsViewModel.getTotalBalance() );
                        currentOut.setText( user.propertyDetailsmodels.detailsViewModel.getCurrentOutStanding());
                        surchargePay.setText( user.propertyDetailsmodels.detailsViewModel.getSurchangePayable());
                        surchargePaid.setText( user.propertyDetailsmodels.detailsViewModel.getSurchangePaid());
                        surchargeWaived.setText( user.propertyDetailsmodels.detailsViewModel.getSurchangeWavied() );
                        surchargeBal.setText( user.propertyDetailsmodels.detailsViewModel.getSurchangeBalance());
                        netOut.setText( user.propertyDetailsmodels.detailsViewModel.getNetOutStanding());
                        grossPay.setText( user.propertyDetailsmodels.detailsViewModel.getGrossPayable());

                        // smart view data






                        // set progress
                        NumberFormat myFormat = NumberFormat.getInstance();
                        myFormat.setGroupingUsed(true);
                        String total=  user.propertyDetailsmodels.smartViewModel.getTotlaPaid().replaceAll(",", "");
                        double Totalpaid = Math.round(Double.parseDouble(   total )     );
                        int totalpaid =Integer.parseInt(  String.valueOf( myFormat.format( Totalpaid ) ))  ;

                        String currentOutStanding1=   user.propertyDetailsmodels.smartViewModel.getCurrentOutStanding() .replaceAll(",", "");
                        double currentOutStanding = Math.round(Double.parseDouble(currentOutStanding1  )     );
                        String currentOutStanding2 = String.valueOf( myFormat.format( currentOutStanding ));
                        String currentOutStanding3=   currentOutStanding2.replaceAll(",", "");
                        int CurrentOutStanding =Integer.parseInt(  String.valueOf( currentOutStanding3  ))  ;


                        String surchangePaid1 =   user.propertyDetailsmodels.smartViewModel.getSurchangePaid().replaceAll(",", "");
                        double surchangePaid = Math.round(Double.parseDouble(surchangePaid1 )  );
                        String surchangePaid2 = String.valueOf( myFormat.format( surchangePaid ));
                        String surchangePaid3=   surchangePaid2.replaceAll(",", "");
                        int SurchangePaid =Integer.parseInt(  String.valueOf(surchangePaid3 ))  ;

                        String surchangeWavied1 =   user.propertyDetailsmodels.smartViewModel.getSurchangeWavied().replaceAll(",", "");
                        double surchangeWavied = Math.round(Double.parseDouble( surchangeWavied1  )     );
                        String surchangeWavied2 = String.valueOf( myFormat.format( surchangeWavied ));
                        String surchangeWavied3=   surchangeWavied2.replaceAll(",", "");
                        int SurchangeWavied =Integer.parseInt(  String.valueOf( surchangeWavied3))  ;

                        String surchangeBalance1 =   user.propertyDetailsmodels.smartViewModel.getSurchangeBalance().replaceAll(",", "");
                        double surchangeBalance = Math.round(Double.parseDouble( surchangeBalance1 )     );
                        String surchangeBalance2 = String.valueOf( myFormat.format( surchangeBalance ));
                        String surchangeBalance3=   surchangeBalance2.replaceAll(",", "");
                        int SurchangeBalance =Integer.parseInt(  String.valueOf( surchangeBalance3))  ;

//                        String surchangePayable1=   user.propertyDetailsmodels.smartViewModel.getSurchangePayable().replaceAll(",", "");
//                        double surchangePayable = Math.round(Double.parseDouble(surchangePayable1  )     );
//                        String surchangePayable2 = String.valueOf( myFormat.format( surchangePayable ));
//                        String surchangePayable3=   surchangePayable2.replaceAll(",", "");
//                        int SurchangePayable =Integer.parseInt(surchangePayable3);
//
//                        String netOutStanding1 =   user.propertyDetailsmodels.smartViewModel.getNetOutStanding().replaceAll(",", "");
//                        double netOutStanding = Math.round(Double.parseDouble( netOutStanding1   )     );
//                        String netOutStanding2 = String.valueOf( myFormat.format( netOutStanding ));
//                        String netOutStanding3=   netOutStanding2.replaceAll(",", "");
//                        int NetOutStanding =Integer.parseInt(  String.valueOf( netOutStanding3))  ;
//
//                        String grossPayable1 =   user.propertyDetailsmodels.smartViewModel.getGrossPayable().replaceAll(",", "");
//                        double grossPayable = Math.round(Double.parseDouble(grossPayable1  )     );
//                        String grossPayable2 = String.valueOf( myFormat.format( grossPayable ));
//                        String grossPayable3=   grossPayable2.replaceAll(",", "");
//                        int GrossPayable =Integer.parseInt(  String.valueOf( grossPayable3 ))  ;

                        //     smart_netOut.setText(String.valueOf( NetOutStanding ));
                        //      smart_grossPay.setText( String.valueOf(GrossPayable  ));
                        //    smart_surchargePay.setText( String.valueOf( SurchangePayable ) );

                        smart_total_paid.setText( String.valueOf(totalpaid)  );
                        smart_instal_pay.setText( String.valueOf( user.propertyDetailsmodels.smartViewModel.getInstallmentPayable() ) );
                        smart_total_bal.setText( String.valueOf(user.propertyDetailsmodels.smartViewModel.getTotalBalance()  ) );
                        smart_currentOut.setText( String.valueOf(CurrentOutStanding  ) );
                        smart_surchargePaid.setText( String.valueOf(SurchangePaid  ) );
                        smart_surchargeWaived.setText( String.valueOf( SurchangeWavied ) );
                        smart_surchargeBal.setText( String.valueOf( SurchangeBalance ));

//
//
                        progressBar_smart_total_paid.setProgress( totalpaid );
                        progressBar_smart_currentOut.setProgress(CurrentOutStanding    );

                        progressBar_smart_surchargePaid.setProgress(  SurchangePaid   );
                        progressBar_smart_surchargeWaived.setProgress( SurchangeWavied   );
                        progressBar_smart_surchargeBal.setProgress(SurchangeBalance    );
//                        progressBar_smart_netOut.setProgress( NetOutStanding   );
//                        progressBar_smart_grossPay.setProgress( GrossPayable   );
//                        progressBar_smart_surchargePay.setProgress(SurchangePayable    );


                        shared_acc_no.setText(String.valueOf(user.propertyDetailsmodels.getSharedAccountCount()  ) + " " + "Users");
                        if(user.propertyDetailsmodels.sharedAccountImages.size() == 1)
                        {

                            shareImage1.setVisibility( View.VISIBLE );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 0 ).toString() ).into( shared_user_image1 );
                            shareImage2.setVisibility( View.GONE );
                            shareImage3.setVisibility(View.GONE);
                        }
                        else if(user.propertyDetailsmodels.sharedAccountImages.size() == 2)
                        {
                            shareImage1.setVisibility( View.VISIBLE );
                            shareImage2.setVisibility( View.VISIBLE );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 0 ).toString() ).into( shared_user_image1 );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 1 ).toString() ).into( shared_user_image2 );
                            shareImage3.setVisibility(View.GONE);
                        }
                        else if(user.propertyDetailsmodels.sharedAccountImages.size() == 3)
                        {
                            shareImage1.setVisibility( View.VISIBLE );
                            shareImage2.setVisibility( View.VISIBLE );
                            shareImage3.setVisibility(View.VISIBLE);
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 0 ).toString() ).into( shared_user_image1 );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 1 ).toString() ).into( shared_user_image2 );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 2 ).toString() ).into( shared_user_image3 );
                        }
                        else if(user.propertyDetailsmodels.sharedAccountImages.size() > 3)
                        {
                            int count = user.propertyDetailsmodels.sharedAccountImages.size();
                            int total1 = count - 3;

                            user_count.setText( "+" +total1);
                            user_count.setVisibility( View.VISIBLE );
                            shared_acc_no.setVisibility( View.GONE );
                            shareImage1.setVisibility( View.VISIBLE );
                            shareImage2.setVisibility( View.VISIBLE );
                            shareImage3.setVisibility(View.VISIBLE);
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 0 ).toString() ).into( shared_user_image1 );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 1 ).toString() ).into( shared_user_image2 );
                            Glide.with( getApplicationContext() ).load( user.propertyDetailsmodels.sharedAccountImages.get( 2 ).toString() ).into( shared_user_image3 );
                        }
                        else {
                            image_and_shareCount.setVisibility( View.GONE );
                            view_details_btn.setVisibility( View.GONE );
                            hide_account.setVisibility( View.GONE );
                            no_account_text.setVisibility( View.VISIBLE );
                            //share_now_btn.setVisibility( View.VISIBLE );
                        }






                    }


                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<PropertyDetailsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void transitionDots(LinearLayout li, int lastIndex, int totalitem) {

        for (int i=0;i<li.getChildCount();i++){
            if(li.getChildAt( i ) instanceof TextView){
                li.getChildAt( i ).setBackgroundResource( R.drawable.indictor0 );
            }
        }
        // set dot All position items
        for(int j=0; j<totalitem;j++){
            if(lastIndex>=0){
                li.getChildAt( lastIndex ).setBackgroundResource( R.drawable.indicator1 );
                lastIndex--;
            }
        }
    }
    private LinearLayout getBottomDot(int count){
        LinearLayout li = new LinearLayout( getApplicationContext() );
        li.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT ));
        li.setOrientation( LinearLayout.HORIZONTAL );
        li.setGravity( Gravity.CENTER );

        for (int i = 0;i<count;i++)
        {
            TextView tv = new TextView( getApplicationContext() );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 35,15 );
            params.setMargins( 7,2,6,2 );
            tv.setLayoutParams( params );
            tv.setBackground( getApplicationContext().getResources().getDrawable(R.drawable.indictor0 ) );
            li.addView( tv );
        }
        list.addView(   li );
        return li;
    }
    private void initBottom(int itemCount ,RecyclerView v){
        LinearLayout li = getBottomDot(itemCount);
        v.setOnScrollChangeListener( new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                int first = ((LinearLayoutManager)v.getLayoutManager()).findFirstVisibleItemPosition();
                int last = ((LinearLayoutManager)v.getLayoutManager()).findLastVisibleItemPosition();
                int total = 0;
                for(int j=first;j<=last;j++){
                    total++;
                }
                transitionDots(li,last,total);

            }

        } );
    }
    private void property_picUpload_api(){




//                                                    File[] file = new File[4] ;
//                                                    //  Bitmap[] bitmaps = new Bitmap[4] ;
//                                                    for (int i = 0 ; i<files.size();i++){
//                                                        file[i] = files.get( i );
//                                                    }
//        for (int i = 0 ; i<image.size();i++){
//            file[i] = files.get( i );
//        }
//        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[image.size()];
//        for (int index = 0; index < image.size(); index++) {
//
//            File files = new File( file[index].getPath() );
//            RequestBody requestFile = RequestBody.create( MediaType.parse("image/*"), files);
//            surveyImagesParts[index] = MultipartBody.Part.createFormData("image", files.getName(), requestFile);
//        }


        //next work with URL
        if(urlStrings != null){
            try {
                json = new JSONArray();
                for (int i = 0; i < urlStrings.size(); i++) {

                    jsonParams = new ArrayMap<>();
                    jsonParams.put( "URL", urlStrings.get( i ) );
                    JSONObject json2 = new JSONObject();
                    json2.put( "URL", urlStrings.get( i ) );
                    json.put( json2);


                }
                System.out.println(json1);
            }
            catch (Exception e){
                System.out.println("not work");
            }
            String token = PreferenceData.getPrefUserToken(getApplicationContext());
            ProgressDialog progress = new ProgressDialog(PropertyDetails.this);

            apiInterface = ApiUtils.postSignUpService();

            progress.setMessage("Please wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            //  progress.show();
            RequestBody proID = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( getIntent().getStringExtra( "id" )  ));
            RequestBody array = RequestBody.create( MediaType.parse( "application/json; charset=utf-8" ),json.toString());
            // MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            //  RequestBody[] surveyImagesParts = new RequestBody[jsonParams.size()];
//        for(int a=0; a< jsonParams.size();a++){
//            //    RequestBody requestFile = RequestBody.create( MediaType.parse("image/*"), files);
//            //             surveyImagesParts[a] = RequestBody.create( okhttp3.MediaType.parse( "image/",));
//        }
            //     RequestBody string_url = RequestBody.create(MediaType.parse("application/json; charset=utf-8",new (jsonParams).toString())) ;
            //RequestBody image = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( urlStrings ));


            Call<PropertyimageUploadResponse> userPropertiesResponseCall = apiInterface.propertyimageUploadResponse(token,proID,array);
            userPropertiesResponseCall.enqueue(new Callback<PropertyimageUploadResponse>() {
                @Override
                public void onResponse(Call<PropertyimageUploadResponse> call, Response<PropertyimageUploadResponse> response) {
                    PropertyimageUploadResponse user = response.body();


                    if (user != null) {

                        if (user.status != false) {
                            // check = "check";
                            Intent a = new Intent(getApplicationContext(),PropertyDetails.class);
                            a.putExtra( "id",ids );
                            a.putExtra( "address",addres );
                            a.putExtra( "isOwner",owner );
                            startActivity( a );
                            finish();
                            Toast.makeText( getApplicationContext(), "Images Upload Successfully", Toast.LENGTH_SHORT ).show();
                            //     property_detail_api();

                        }
                        progress.dismiss();
                    } else {
                        System.out.println( "Failed" );
                    }

                }
                @Override
                public void onFailure(Call<PropertyimageUploadResponse> call, Throwable t) {
                    System.out.println("Failed : " + t.getMessage());
                    call.cancel();
                }
            });
        }








    }
    private void progressBar_value_set(){
    int totalPaid = Integer.parseInt(  smart_total_paid.getText().toString());
 //   int instal_pay = Integer.parseInt( smart_instal_pay.getText().toString() );
//    int total_bal =  Integer.parseInt( smart_total_bal.getText().toString() );
    int currentOut =  Integer.parseInt( smart_currentOut.getText().toString() );
    int surchargePay = Integer.parseInt(smart_surchargePay.getText().toString()  );
    int surchargePaid =   Integer.parseInt( smart_surchargePaid.getText().toString() );
    int surchargeWaived =  Integer.parseInt(smart_surchargeWaived.getText().toString()  );
    int surchargeBal =   Integer.parseInt( smart_surchargeBal.getText().toString() );
    int netOut =   Integer.parseInt(smart_netOut.getText().toString()  );
    int grossPay =  Integer.parseInt(smart_grossPay.getText().toString()  ) ;

//    progressBar_smart_total_paid.setProgress( totalPaid );
//    progressBar_smart_currentOut.setProgress(currentOut  );
//    progressBar_smart_surchargePay.setProgress(surchargePay  );
//    progressBar_smart_surchargePaid.setProgress( surchargePaid );
//    progressBar_smart_surchargeWaived.setProgress( surchargeWaived );
//    progressBar_smart_surchargeBal.setProgress(surchargeBal  );
//    progressBar_smart_netOut.setProgress( netOut );
//    progressBar_smart_grossPay.setProgress( grossPay );
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

           if (requestCode == 11)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //  intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 11);
            }
            else
            {
                Toast.makeText(this, "Please Allow the External Storage permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        //  pic = data.getClipData().getItemAt(0).getUri();
        //  extras = data.getExtras();
//        a  = extras.getParcelable( "image" );

        try {



            if (requestCode == 11) {

                if(data.getClipData() !=null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PropertyDetails.this);
                    builder1.setMessage("Are You sure to selected new photos upload");
                    builder1.setTitle( "Alert" );
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // complaint with draw api call
                                    try {
                                        if (data.getClipData() != null) {
                                            int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                                            // /  Uri imageUri1, imageUri2, imageUri3, imageUri4, imageUri5, imageUri6;


                                            if (count >= 1) {

                                                image = new ArrayList<>();
                                                files = new ArrayList<>();


                                                if(count >=1)
                                                {
                                                    if(count <= 4){
                                                        images = new ArrayList<>();
                                                        for(int i=0;i<count;i++){

                                                            Uri uri=data.getClipData().getItemAt(i).getUri();
                                                            images.add(data.getClipData().getItemAt(i).getUri()  );

//                                                            Bitmap bm=null;
//                                                            if (data != null) {
//                                                                try {
//                                                                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                                                } catch (IOException e) {
//                                                                    e.printStackTrace();
//                                                                }
//                                                            }
//                                                            if (bm != null) { // sanity check
//                                                                tempUri = getImageUri(getApplicationContext(), bm);
//                                                                finalFile = new File(getRealPathFromURI(tempUri));
////
//                                                                files.add( finalFile );
//                                                            }
//                                                            image.add(bm);
                                                        }
                                                        ProgressDialog progressDialog= new ProgressDialog(PropertyDetails.this);
                                                        progressDialog.setMessage("Uploading Images please Wait.....!");
                                                        image = new ArrayList<>();
                                                        progressDialog.show();
                                                        //   alert.setText("If Loading Takes to long press button again");
                                                        // get the Firebase  storage reference

                                                        //StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child(dev_testing);
                                                        upload = new ArrayList<>();
                                                        for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                                                            int random;
                                                            int max =1000;
                                                            int min= 1;
                                                            Random myRandom = new Random();
                                                            random = myRandom.nextInt(max-min+1)+min;
                                                            storage = FirebaseStorage.getInstance();
                                                            storageReference = storage.getReference();
                                                            String dev_testing =   "DEV/Mobile-Android/Property-Images/";
                                                            String prod =   "Prod/mobileImages/Android/Property-Images/";
                                                            String uat =   "DEV/mobileImages/";
                                                            Uri IndividualImage = images.get(upload_count);
                                                            StorageReference ref = storageReference.child( dev_testing + "PropertyImages" + random + ".jpg");


                                                            upload.add( ref.putFile( IndividualImage )  );

//                    ref.putFile(IndividualImage).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            urlStrings =  new ArrayList();
//                              urlStrings.add(task.getResult().toString());
//                        }
//                    } );

                                                            //     urlStrings =  new ArrayList();
                                                            //      urlStrings.add( ref.getDownloadUrl().toString());




                                                        }
                                                        urlStrings = new ArrayList();
                                                        for(int a = 0; a < upload.size() ; a++ ){
                                                          UploadTask uploadTask =  upload.get( a );
                                                          uploadTask  .addOnSuccessListener(
                                                                  new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                                      @Override
                                                                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                          taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                                                                  new OnCompleteListener<Uri>() {

                                                                                      @Override
                                                                                      public void onComplete(@NonNull Task<Uri> task) {

                                                                                          urlStrings.add(  task.getResult().toString() );

                                                                                          if(upload.size() == urlStrings.size()){
                                                                                              property_picUpload_api();
                                                                                          }
                                                                                      }

                                                                                  } );
                                                                      }
                                                                  })

                                                                  .addOnFailureListener(new OnFailureListener() {
                                                                      @Override
                                                                      public void onFailure(@NonNull Exception e)
                                                                      {

                                                                          // Error, Image not uploaded
                                                                          progressDialog.dismiss();
                                                                          Toast
                                                                                  .makeText(PropertyDetails.this,
                                                                                          "Failed " + e.getMessage(),
                                                                                          Toast.LENGTH_SHORT)
                                                                                  .show();
                                                                      }
                                                                  })
                                                                  .addOnProgressListener(
                                                                          new OnProgressListener<UploadTask.TaskSnapshot>() {

                                                                              // Progress Listener for loading
                                                                              // percentage on the dialog box
                                                                              @Override
                                                                              public void onProgress(
                                                                                      UploadTask.TaskSnapshot taskSnapshot)
                                                                              {
                                                                                  double progress
                                                                                          = (100.0
                                                                                          * taskSnapshot.getBytesTransferred()
                                                                                          / taskSnapshot.getTotalByteCount());
                                                                                  progressDialog.setMessage(
                                                                                          "Uploaded "
                                                                                                  + (int)progress + "%");
                                                                              }
                                                                          });
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                    else {
                                                        Toast.makeText( getApplicationContext(), "please select only 4 image", Toast.LENGTH_SHORT ).show();
                                                    }

                                                }

                                            }
                                            else {
                                                Toast.makeText( getApplicationContext(), "please select image", Toast.LENGTH_SHORT ).show();
                                            }

                                        }
                                        else{


                                            if(data != null){
//                        if(image.size() >= 0 ){
                                                images = new ArrayList<>();
                                                Uri uri = data.getData();
                                                images.add(data.getData()  );
                                                ProgressDialog progressDialog= new ProgressDialog(PropertyDetails.this);
                                                progressDialog.setMessage("Uploading Images please Wait.....!");
                                                image = new ArrayList<>();
                                                progressDialog.show();
                                                //   alert.setText("If Loading Takes to long press button again");
                                                // get the Firebase  storage reference

                                                //StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child(dev_testing);
                                                upload = new ArrayList<>();
                                                for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                                                    int random;
                                                    int max =1000;
                                                    int min= 1;
                                                    Random myRandom = new Random();
                                                    random = myRandom.nextInt(max-min+1)+min;
                                                    storage = FirebaseStorage.getInstance();
                                                    storageReference = storage.getReference();
                                                    String dev_testing =   "DEV/Mobile-Android/Property-Images/";
                                                    String prod =   "Prod/mobileImages/Android/Property-Images/";
                                                    String uat =   "DEV/mobileImages/";
                                                    Uri IndividualImage = images.get(upload_count);
                                                    StorageReference ref = storageReference.child( dev_testing + "PropertyImages" + random + ".jpg");


                                                    upload.add( ref.putFile( IndividualImage )  );

//                    ref.putFile(IndividualImage).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            urlStrings =  new ArrayList();
//                              urlStrings.add(task.getResult().toString());
//                        }
//                    } );

                                                    //     urlStrings =  new ArrayList();
                                                    //      urlStrings.add( ref.getDownloadUrl().toString());




                                                }
                                                urlStrings = new ArrayList();
                                                for(int a = 0; a < upload.size() ; a++ ){
                                                    UploadTask uploadTask =  upload.get( a );
                                                    uploadTask  .addOnSuccessListener(
                                                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                                @Override
                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                                                            new OnCompleteListener<Uri>() {

                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Uri> task) {

                                                                                    urlStrings.add(  task.getResult().toString() );

                                                                                    if(upload.size() == urlStrings.size()){
                                                                                        property_picUpload_api();
                                                                                    }
                                                                                }

                                                                            } );
                                                                }
                                                            })

                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e)
                                                                {

                                                                    // Error, Image not uploaded
                                                                    progressDialog.dismiss();
                                                                    Toast
                                                                            .makeText(PropertyDetails.this,
                                                                                    "Failed " + e.getMessage(),
                                                                                    Toast.LENGTH_SHORT)
                                                                            .show();
                                                                }
                                                            })
                                                            .addOnProgressListener(
                                                                    new OnProgressListener<UploadTask.TaskSnapshot>() {

                                                                        // Progress Listener for loading
                                                                        // percentage on the dialog box
                                                                        @Override
                                                                        public void onProgress(
                                                                                UploadTask.TaskSnapshot taskSnapshot)
                                                                        {
                                                                            double progress
                                                                                    = (100.0
                                                                                    * taskSnapshot.getBytesTransferred()
                                                                                    / taskSnapshot.getTotalByteCount());
                                                                            progressDialog.setMessage(
                                                                                    "Uploaded "
                                                                                            + (int)progress + "%");
                                                                        }
                                                                    });
                                                }
                                                progressDialog.dismiss();
    //                                                Bitmap bm=null;
    //                                                if (data != null) {
    //                                                    try {
    //                                                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
    //                                                    } catch (IOException e) {
    //                                                        e.printStackTrace();
    //                                                    }
    //                                                }
    //                                                if (bm != null) { // sanity check
    //                                                    tempUri = getImageUri(getApplicationContext(), bm);
    //                                                    finalFile = new File(getRealPathFromURI(tempUri));
    ////
    //                                                    files.add( finalFile );
    //                                                }
    //                                                image.add(bm);

                                               // property_picUpload_api();
//                        }
//                        else{
//                            Toast.makeText( getApplicationContext(), "only 4 image you select only", Toast.LENGTH_SHORT ).show();
//                        }
                                            }
                                            else {
                                                images = new ArrayList<>();
                                                Uri uri = data.getData();
                                                images.add(data.getData()  );
                                                ProgressDialog progressDialog= new ProgressDialog(PropertyDetails.this);
                                                progressDialog.setMessage("Uploading Images please Wait.....!");
                                                image = new ArrayList<>();
                                                progressDialog.show();
                                                //   alert.setText("If Loading Takes to long press button again");
                                                // get the Firebase  storage reference

                                                //StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child(dev_testing);
                                                upload = new ArrayList<>();
                                                for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                                                    int random;
                                                    int max =1000;
                                                    int min= 1;
                                                    Random myRandom = new Random();
                                                    random = myRandom.nextInt(max-min+1)+min;
                                                    storage = FirebaseStorage.getInstance();
                                                    storageReference = storage.getReference();
                                                    String dev_testing =   "DEV/Mobile-Android/Property-Images/";
                                                    String prod =   "Prod/mobileImages/Android/Property-Images/";
                                                    String uat =   "DEV/mobileImages/";
                                                    Uri IndividualImage = images.get(upload_count);
                                                    StorageReference ref = storageReference.child( dev_testing + "PropertyImages" + random + ".jpg");


                                                    upload.add( ref.putFile( IndividualImage )  );

//                    ref.putFile(IndividualImage).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            urlStrings =  new ArrayList();
//                              urlStrings.add(task.getResult().toString());
//                        }
//                    } );

                                                    //     urlStrings =  new ArrayList();
                                                    //      urlStrings.add( ref.getDownloadUrl().toString());




                                                }
                                                urlStrings = new ArrayList();
                                                for(int a = 0; a < upload.size() ; a++ ){
                                                    UploadTask uploadTask =  upload.get( a );
                                                    uploadTask  .addOnSuccessListener(
                                                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                                @Override
                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                                                            new OnCompleteListener<Uri>() {

                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Uri> task) {

                                                                                    urlStrings.add(  task.getResult().toString() );

                                                                                    if(upload.size() == urlStrings.size()){
                                                                                        property_picUpload_api();
                                                                                    }
                                                                                }

                                                                            } );
                                                                }
                                                            })

                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e)
                                                                {

                                                                    // Error, Image not uploaded
                                                                    progressDialog.dismiss();
                                                                    Toast
                                                                            .makeText(PropertyDetails.this,
                                                                                    "Failed " + e.getMessage(),
                                                                                    Toast.LENGTH_SHORT)
                                                                            .show();
                                                                }
                                                            })
                                                            .addOnProgressListener(
                                                                    new OnProgressListener<UploadTask.TaskSnapshot>() {

                                                                        // Progress Listener for loading
                                                                        // percentage on the dialog box
                                                                        @Override
                                                                        public void onProgress(
                                                                                UploadTask.TaskSnapshot taskSnapshot)
                                                                        {
                                                                            double progress
                                                                                    = (100.0
                                                                                    * taskSnapshot.getBytesTransferred()
                                                                                    / taskSnapshot.getTotalByteCount());
                                                                            progressDialog.setMessage(
                                                                                    "Uploaded "
                                                                                            + (int)progress + "%");
                                                                        }
                                                                    });
                                                }
                                                progressDialog.dismiss();
//                                                Bitmap bm=null;
//                                                if (data != null) {
//                                                    try {
//                                                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                                    } catch (IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                                if (bm != null) { // sanity check
//                                                    tempUri = getImageUri(getApplicationContext(), bm);
//                                                    finalFile = new File(getRealPathFromURI(tempUri));
////
//                                                    files.add( finalFile );
//                                                }
//                                                image.add(bm);
                                             //   property_picUpload_api();

                                            }


                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Toast.makeText(getApplicationContext(), "Please Select Multiple Image", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }


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
                else  if(data.getData() !=null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PropertyDetails.this);
                    builder1.setMessage("Are You sure uplaod this picture");
                    builder1.setTitle( "Alert" );
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // complaint with draw api call
                                    try {
                                        if (data.getClipData() != null) {
                                            int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                                            // /  Uri imageUri1, imageUri2, imageUri3, imageUri4, imageUri5, imageUri6;


                                            if (count >= 1) {

                                                image = new ArrayList<>();
                                                files = new ArrayList<>();


                                                if(count >=1)
                                                {
                                                    if(count >=4){
                                                        Toast.makeText( getApplicationContext(), "please select only 4 image", Toast.LENGTH_SHORT ).show();
                                                    }
                                                    else {
                                                        for (int i = 0; i < count; i++) {
                                                            images = new ArrayList<>();

                                                            Uri uri = data.getClipData().getItemAt( i ).getUri();
                                                            images.add( data.getClipData().getItemAt( i ).getUri() );

                                                            ProgressDialog progressDialog= new ProgressDialog(PropertyDetails.this);
                                                            progressDialog.setMessage("Uploading Images please Wait.....!");
                                                            image = new ArrayList<>();
                                                            progressDialog.show();
                                                            //   alert.setText("If Loading Takes to long press button again");
                                                            // get the Firebase  storage reference

                                                            //StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child(dev_testing);
                                                            upload = new ArrayList<>();
                                                            for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                                                                int random;
                                                                int max =1000;
                                                                int min= 1;
                                                                Random myRandom = new Random();
                                                                random = myRandom.nextInt(max-min+1)+min;
                                                                storage = FirebaseStorage.getInstance();
                                                                storageReference = storage.getReference();
                                                                String dev_testing =   "DEV/Mobile-Android/Property-Images/";
                                                                String prod =   "Prod/mobileImages/Android/Property-Images/";
                                                                String uat =   "DEV/mobileImages/";
                                                                Uri IndividualImage = images.get(upload_count);
                                                                StorageReference ref = storageReference.child( dev_testing + "PropertyImages" + random + ".jpg");


                                                                upload.add( ref.putFile( IndividualImage )  );

//                    ref.putFile(IndividualImage).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            urlStrings =  new ArrayList();
//                              urlStrings.add(task.getResult().toString());
//                        }
//                    } );

                                                                //     urlStrings =  new ArrayList();
                                                                //      urlStrings.add( ref.getDownloadUrl().toString());




                                                            }
                                                            urlStrings = new ArrayList();
                                                            for(int a = 0; a < upload.size() ; a++ ){
                                                                UploadTask uploadTask =  upload.get( a );
                                                                uploadTask  .addOnSuccessListener(
                                                                        new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                                            @Override
                                                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                                                                        new OnCompleteListener<Uri>() {

                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Uri> task) {

                                                                                                urlStrings.add(  task.getResult().toString() );

                                                                                                if(upload.size() == urlStrings.size()){
                                                                                                    property_picUpload_api();
                                                                                                }
                                                                                            }

                                                                                        } );
                                                                            }
                                                                        })

                                                                        .addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e)
                                                                            {

                                                                                // Error, Image not uploaded
                                                                                progressDialog.dismiss();
                                                                                Toast
                                                                                        .makeText(PropertyDetails.this,
                                                                                                "Failed " + e.getMessage(),
                                                                                                Toast.LENGTH_SHORT)
                                                                                        .show();
                                                                            }
                                                                        })
                                                                        .addOnProgressListener(
                                                                                new OnProgressListener<UploadTask.TaskSnapshot>() {

                                                                                    // Progress Listener for loading
                                                                                    // percentage on the dialog box
                                                                                    @Override
                                                                                    public void onProgress(
                                                                                            UploadTask.TaskSnapshot taskSnapshot)
                                                                                    {
                                                                                        double progress
                                                                                                = (100.0
                                                                                                * taskSnapshot.getBytesTransferred()
                                                                                                / taskSnapshot.getTotalByteCount());
                                                                                        progressDialog.setMessage(
                                                                                                "Uploaded "
                                                                                                        + (int)progress + "%");
                                                                                    }
                                                                                });
                                                            }
                                                            progressDialog.dismiss();

//                                                            Bitmap bm = null;
//                                                            if (data != null) {
//                                                                try {
//                                                                    bm = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(), uri );
//                                                                } catch (IOException e) {
//                                                                    e.printStackTrace();
//                                                                }
//                                                            }
//                                                            if (bm != null) { // sanity check
//                                                                tempUri = getImageUri( getApplicationContext(), bm );
//                                                                finalFile = new File( getRealPathFromURI( tempUri ) );
////
//                                                                files.add( finalFile );
//                                                            }
//                                                            image.add( bm );
                                                        }
                                                    }
                                                }

                                            }
                                            else {
                                                Toast.makeText( getApplicationContext(), "please select image", Toast.LENGTH_SHORT ).show();
                                            }

                                        }
                                        else{

                                            files = new ArrayList<>();
                                            image = new ArrayList<>();
                                            if(data != null){
//                        if(image.size() >= 0 ){
                                                images = new ArrayList<>();
                                                Uri uri = data.getData();
                                                images.add( data.getData() );
                                                ProgressDialog progressDialog= new ProgressDialog(PropertyDetails.this);
                                                progressDialog.setMessage("Uploading Images please Wait.....!");
                                                image = new ArrayList<>();
                                                progressDialog.show();
                                                //   alert.setText("If Loading Takes to long press button again");
                                                // get the Firebase  storage reference

                                                //StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child(dev_testing);
                                                upload = new ArrayList<>();
                                                for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                                                    int random;
                                                    int max =1000;
                                                    int min= 1;
                                                    Random myRandom = new Random();
                                                    random = myRandom.nextInt(max-min+1)+min;
                                                    storage = FirebaseStorage.getInstance();
                                                    storageReference = storage.getReference();
                                                    String dev_testing =   "DEV/Mobile-Android/Property-Images/";
                                                    String prod =   "Prod/mobileImages/Android/Property-Images/";
                                                    String uat =   "DEV/mobileImages/";
                                                    Uri IndividualImage = images.get(upload_count);
                                                    StorageReference ref = storageReference.child( dev_testing + "PropertyImages" + random + ".jpg");


                                                    upload.add( ref.putFile( IndividualImage )  );

//                    ref.putFile(IndividualImage).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            urlStrings =  new ArrayList();
//                              urlStrings.add(task.getResult().toString());
//                        }
//                    } );

                                                    //     urlStrings =  new ArrayList();
                                                    //      urlStrings.add( ref.getDownloadUrl().toString());




                                                }
                                                urlStrings = new ArrayList();
                                                for(int a = 0; a < upload.size() ; a++ ){
                                                    UploadTask uploadTask =  upload.get( a );
                                                    uploadTask  .addOnSuccessListener(
                                                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                                @Override
                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                                                                            new OnCompleteListener<Uri>() {

                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Uri> task) {

                                                                                    urlStrings.add(  task.getResult().toString() );

                                                                                    if(upload.size() == urlStrings.size()){
                                                                                        property_picUpload_api();
                                                                                    }
                                                                                }

                                                                            } );
                                                                }
                                                            })

                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e)
                                                                {

                                                                    // Error, Image not uploaded
                                                                    progressDialog.dismiss();
                                                                    Toast
                                                                            .makeText(PropertyDetails.this,
                                                                                    "Failed " + e.getMessage(),
                                                                                    Toast.LENGTH_SHORT)
                                                                            .show();
                                                                }
                                                            })
                                                            .addOnProgressListener(
                                                                    new OnProgressListener<UploadTask.TaskSnapshot>() {

                                                                        // Progress Listener for loading
                                                                        // percentage on the dialog box
                                                                        @Override
                                                                        public void onProgress(
                                                                                UploadTask.TaskSnapshot taskSnapshot)
                                                                        {
                                                                            double progress
                                                                                    = (100.0
                                                                                    * taskSnapshot.getBytesTransferred()
                                                                                    / taskSnapshot.getTotalByteCount());
                                                                            progressDialog.setMessage(
                                                                                    "Uploaded "
                                                                                            + (int)progress + "%");
                                                                        }
                                                                    });
                                                }
                                                progressDialog.dismiss();
//                                                Bitmap bm=null;
//                                                if (data != null) {
//                                                    try {
//                                                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                                    } catch (IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                                if (bm != null) { // sanity check
//                                                    tempUri = getImageUri(getApplicationContext(), bm);
//                                                    finalFile = new File(getRealPathFromURI(tempUri));
////
//                                                    files.add( finalFile );
//                                                }
//                                                image.add(bm);
//                        }
//                        else{
//                            Toast.makeText( getApplicationContext(), "only 4 image you select only", Toast.LENGTH_SHORT ).show();
//                        }
                                            }
                                            else {

                                                files = new ArrayList<>();
                                                image = new ArrayList<>();
                                                Uri uri = data.getData();
//                                                Bitmap bm=null;
//                                                if (data != null) {
//                                                    try {
//                                                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                                    } catch (IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                                if (bm != null) { // sanity check
//                                                    tempUri = getImageUri(getApplicationContext(), bm);
//                                                    finalFile = new File(getRealPathFromURI(tempUri));
////
//                                                    files.add( finalFile );
//                                                }
                                              //  image.add(bm);


                                            }


                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Toast.makeText(getApplicationContext(), "Please Select Multiple Image", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                    property_picUpload_api();
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


            } else {


//                if(image != null){
//                    if(image.size() < 4 ){
//                        Uri uri = data.getData();
//                        Bitmap bm=null;
//                        if (data != null) {
//                            try {
//                                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        if (bm != null) { // sanity check
//                            tempUri = getImageUri(getApplicationContext(), bm);
//                            finalFile = new File(getRealPathFromURI(tempUri));
////
//                            files.add( finalFile );
//                        }
//                        image.add(bm);
//
//                    }
//                    else{
//                        Toast.makeText( getApplicationContext(), "only 4 image you select only", Toast.LENGTH_SHORT ).show();
//                    }
//                }
//                else {
                    if(data != null){
                        images = new ArrayList<>();
                        Uri uri = data.getData();
                        images.add(  data.getData());

//                        Bitmap bm=null;
//                        if (data != null) {
//                            try {
//                                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        if (bm != null) { // sanity check
//                            tempUri = getImageUri(getApplicationContext(), bm);
//                            finalFile = new File(getRealPathFromURI(tempUri));
////
//                            files.add( finalFile );
//                        }
//                        image.add(bm);

                  //  }

                }

                //  Toast.makeText( getApplicationContext(), "picture not select", Toast.LENGTH_SHORT ).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, "no picture select", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "IMG_" + Calendar.getInstance().getTime(),null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(check != null && check.equals( "check" )){
            Intent a = new Intent(getApplicationContext(),PropertyDetails.class);
            a.putExtra( "id",ids );
            a.putExtra( "address",addres );
            a.putExtra( "isOwner",owner );
            startActivity( a );
            finish();

        }

    }
}