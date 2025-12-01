package com.HS.Topcity.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.UserComplainAdapter;
import com.HS.Topcity.Adapters.UserSharedAccountPropetyImagesAdapter;
import com.HS.Topcity.ApiModels.UserSharedAccountDetail.UserSharedAccountDetalRequest;
import com.HS.Topcity.ApiModels.UserSharedAccountDetail.UserSharedAccountDetalResponse;
import com.HS.Topcity.ApiModels.UserSharedAccountRemove.UserSharedAccountRemoveRequest;
import com.HS.Topcity.ApiModels.UserSharedAccountRemove.UserSharedAccountRemoveResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplainsModel;
import com.HS.Topcity.Models.PropertyDetailsimageModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSharedAccountDetail extends AppCompatActivity {

    ImageView user_image;
    LinearLayout remove_user;
    ApiInterface apiInterface;
    ArrayList<PropertyDetailsimageModel> propertyImagesList;
    ArrayList<ComplainsModel> complainsModels;
    ArrayList<String> images;
    LinearLayout  back,no_comp_txt;
    UserSharedAccountRemoveRequest userSharedAccountRemoveRequest = new UserSharedAccountRemoveRequest();
    UserSharedAccountDetalRequest userSharedAccountDetalRequest = new UserSharedAccountDetalRequest();
    TextView status,name,cnic,phone,email,user_type,block,plot,propertyTypeId,Propertytype;
    RecyclerView propertyImageList,Complains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_shared_account_detail );
        getSupportActionBar().hide();
   //     getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        ids();

        indent_data_put();

        back();
        User_sharedAccount_detail_api();
        Remove_user();


    }

    private void ids(){
        back = findViewById(R.id.shared_Account_detail_back );
        user_image = findViewById(R.id.shared_Account_detail_image );
        remove_user = findViewById(R.id.shared_Account_detail_removeuser );
        propertyImageList= findViewById(R.id.shared_Account_detail_propertyImagesList);
        Complains = findViewById(R.id.shared_Account_detail_ComplaintsList );
        status= findViewById(R.id.shared_Account_detail_active_status );
        name = findViewById(R.id.Shared_account_detail_name);
        cnic= findViewById(R.id.Shared_account_detail_cnic);
        phone= findViewById(R.id.Shared_account_detail_phone);
        email= findViewById(R.id.Shared_account_detail_email );
        user_type= findViewById(R.id.Shared_account_detail_userType );
        block= findViewById(R.id.shared_Account_detail_block);
        plot= findViewById(R.id.shared_Account_detail_plot );
        propertyTypeId= findViewById(R.id.shared_Account_detail_propertyid );
        Propertytype = findViewById(R.id.shared_Account_detail_propertyType );
        no_comp_txt = findViewById( R.id.no_complaint_text );
    }
    private void Remove_user(){
        remove_user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(UserSharedAccountDetail.this);
                builder1.setMessage("This action can't be undone. Are you sure remove this user");
                builder1.setTitle( "Alert" );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // complaint with draw api call
                                api_SharedAccountRemove();
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
    private void back(){
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void indent_data_put(){
        Glide.with( this ).load( getIntent().getStringExtra( "image" ) ).into( user_image );



        user_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( getIntent().getStringExtra( "image" ) !=null){
                    images =  new ArrayList<>();
                    images.add( getIntent().getStringExtra( "image" )  );
                    Intent a  = new Intent(getApplicationContext(), FullScreenImagesActivity.class);
                    a.putStringArrayListExtra( "Array",images);
                    a.putExtra( "position",0 );
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a);
                }



            }
        } );
    }
    private void api_SharedAccountRemove(){
        apiInterface = ApiUtils.postSignUpService();
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        // progress.show();
        userSharedAccountRemoveRequest.setSharedAccountId(Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
        userSharedAccountRemoveRequest.setTypeOfAccountId(Integer.parseInt( getIntent().getStringExtra( "accountTypeId" ) ) );
        userSharedAccountRemoveRequest.setPropertyId(Integer.parseInt(propertyTypeId.getText().toString()  ));
        Call<UserSharedAccountRemoveResponse> userSharedAccountRemoveResponse = apiInterface.userSharedAccountRemoveResponse(token, userSharedAccountRemoveRequest);
        userSharedAccountRemoveResponse.enqueue(new Callback<UserSharedAccountRemoveResponse>() {
            @Override
            public void onResponse(Call<UserSharedAccountRemoveResponse> call, Response<UserSharedAccountRemoveResponse> response) {
                UserSharedAccountRemoveResponse user = response.body();

                int splash = 3000;

                if(user != null) {

                    if(user.status != false){
                        final Dialog camera_and_gallery_dialog = new Dialog( UserSharedAccountDetail.this );

                        camera_and_gallery_dialog.setContentView(R.layout.user_remove_success_layout);
                        camera_and_gallery_dialog.setCancelable(true);
                        camera_and_gallery_dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.WHITE));
                        camera_and_gallery_dialog.getWindow().setLayout( WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT );
                        camera_and_gallery_dialog.setCanceledOnTouchOutside(false);
                        camera_and_gallery_dialog.show();
                        new Handler().postDelayed( new Runnable() {
                            @Override
                            public void run() {

                               camera_and_gallery_dialog.dismiss();
                               finish();
//
                            }
                        }, splash);


                    }


                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<UserSharedAccountRemoveResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void User_sharedAccount_detail_api(){
        apiInterface = ApiUtils.postSignUpService();
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        // progress.show();
        userSharedAccountDetalRequest.setPropertyId(Integer.parseInt( getIntent().getStringExtra( "propertyId" ) ) );
        userSharedAccountDetalRequest.setSharedAccountId(Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
        userSharedAccountDetalRequest.setTypeOfAccount(getIntent().getStringExtra( "accountType" )  );
        userSharedAccountDetalRequest.setTypeOfAccountId(Integer.parseInt( getIntent().getStringExtra( "accountTypeId" ) ) );

        Call<UserSharedAccountDetalResponse> userSharedAccountDetalResponseCall = apiInterface.user_shared_account_detail_response(token, userSharedAccountDetalRequest);
        userSharedAccountDetalResponseCall.enqueue(new Callback<UserSharedAccountDetalResponse>() {
            @Override
            public void onResponse(Call<UserSharedAccountDetalResponse> call, Response<UserSharedAccountDetalResponse> response) {
                UserSharedAccountDetalResponse user = response.body();
                if(user != null) {

                    if(user.shareAccountUserInfoModel != null){

                        // user info

                        status.setText( user.shareAccountUserInfoModel.getStateOfAccount() );
                        name.setText( user.shareAccountUserInfoModel.getName() );
                        email.setText( user.shareAccountUserInfoModel.getEmail() );
                        phone.setText( user.shareAccountUserInfoModel.getContactNumber() );
                        user_type.setText( user.shareAccountUserInfoModel.getTypeOfAccount() );
                        cnic.setText( user.shareAccountUserInfoModel.getcNIC() );


                        // change status color
                        status_color_change();

                        // remove button hide for family and tenat
                        if (user.shareAccountUserInfoModel.canRemove == false){
                            remove_user.setVisibility( View.GONE );
                        }
                        else {
                            remove_user.setVisibility( View.VISIBLE );
                        }
                        // property shared
                        block.setText( user.shareAccountUserInfoModel.getPropertyDetailsModel().getBlock() );
                        plot.setText( user.shareAccountUserInfoModel.getPropertyDetailsModel().getPlotNo()  );
                        propertyTypeId.setText( String.valueOf( user.shareAccountUserInfoModel.getPropertyDetailsModel().getPropertyId()  ) );
                        Propertytype.setText( user.shareAccountUserInfoModel.getPropertyDetailsModel().getPropertyType()  );


                        propertyImagesList = new ArrayList<>();

                        if (user.shareAccountUserInfoModel.propertyDetailsModel.propertiesImages.size() != 0)
                        {
                            for (int i = 0; i < user.shareAccountUserInfoModel.propertyDetailsModel.propertiesImages.size(); i++) {
                                PropertyDetailsimageModel eventImagesModel = new PropertyDetailsimageModel();


                                eventImagesModel.setImage( user.shareAccountUserInfoModel.propertyDetailsModel.propertiesImages.get(i) );
                                propertyImagesList.add( eventImagesModel );
                            }


                            UserSharedAccountPropetyImagesAdapter propertyDetailsImageAdapter = new UserSharedAccountPropetyImagesAdapter( getApplicationContext(), propertyImagesList,R.layout.event_layout );

                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                            propertyImageList.setLayoutManager( linearLayoutManager2 );
                            propertyImageList.setNestedScrollingEnabled(true);
                            propertyImageList.setAdapter( propertyDetailsImageAdapter );
                        }


                        complainsModels = new ArrayList<>();
                        if (user.shareAccountUserInfoModel.complainsDetailsModels.size() > 0) {
                            no_comp_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.shareAccountUserInfoModel.complainsDetailsModels.size(); i++) {
                                ComplainsModel model = new ComplainsModel();
                                model.setId( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getComplainId() );
                                model.setName( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getComplainName());
                                model.setNumber( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getComplainNumber() );
                                model.setStatus( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getComplainStatus() );
                                model.setImage( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getImage() );
                                model.setPropertyname( user.shareAccountUserInfoModel.complainsDetailsModels.get( i ).getPropertyName() );
                                complainsModels.add( model );
                            }

                            UserComplainAdapter userComplainAdapter = new UserComplainAdapter( getApplicationContext(), complainsModels,R.layout.complains_layout );
                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.VERTICAL,false );
                            Complains.setLayoutManager( linearLayoutManager2 );
                            Complains.setNestedScrollingEnabled(true);
                            Complains.setAdapter( userComplainAdapter );
                        }
                        else {
                            no_comp_txt.setVisibility( View.VISIBLE );
                        }

                    }


                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<UserSharedAccountDetalResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void status_color_change(){
//        if(status.getText().toString().equals( "Invitation Send" )) {
//            status.setTextColor( this.getApplicationContext().getColor( R.color.bg_mhendi_color ) );
//        }
        if(status.getText().toString().equals( "Active" )){
          status.setTextColor(this.getApplicationContext().getColor( R.color.dark_green ));

        }

    }



    @Override
    public void onResume() {

        User_sharedAccount_detail_api();
        super.onResume();

    }
}