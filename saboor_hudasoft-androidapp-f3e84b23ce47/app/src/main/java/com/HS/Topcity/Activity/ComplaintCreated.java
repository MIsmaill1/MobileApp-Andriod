package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.CreateComplaintImagesAdapters;
import com.HS.Topcity.Adapters.PropertyComplainsCreateAdapter;
import com.HS.Topcity.ApiModels.ComplaintCreate.ComplaintCreateResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.UserProperties.UserPropertiesResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplainSubTypesModels;
import com.HS.Topcity.Models.PropertiesModel;
import com.HS.Topcity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintCreated extends AppCompatActivity {

    RecyclerView property_list, proplem_list, problem_image_list;
    LinearLayout submit_btn, attached;
    public static  LinearLayout box;
    public static  LinearLayout fileds_created_complains;
    ImageView  user, notification;
    TextView title;
    ApiInterface apiInterface;
    ArrayList<PropertiesModel> propertiesModels;
    EditText description;
    RadioButton radioButton;
    ArrayList<Uri> images =  new ArrayList<>();
    ArrayList urlStrings;
    JSONArray json = new JSONArray() ;
    JSONObject json1;
    ArrayList<Bitmap> image;
    ArrayList<File> files;
    Uri tempUri;
    File finalFile;
    TextView Noti_count;
    LinearLayout back,Noti_bg;
    public static int comp_pro_id;
   String subtypeName;
   int subTypeId;
   String check_com;
   String titles;
    ArrayList<ComplainSubTypesModels> complainSubTypesModels;
    FirebaseStorage storage;
    StorageReference storageReference;
    ArrayList<UploadTask> upload ;
  public static   TextView required;
  //  ArrayList<ComplainSubTypesModels> complainSubTypesModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_complaint_created );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
   //     getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // ids
        id();
        // notification and user or back to privous screen funtionality
        noti_user_and_back();
        // notification count funtionality
        noti_count();
      // properties api intregration
        property_Api();
        //create sub complaint radio button funitonlity
        fill_data_and_radioBtn_action();
        // attach selected photos to recycle view adapter
        images_add();
        // open gallery funtionality
        attached_photos();
        // create complaint api and create complaint funtionality
        sumbit_btn();
        // Notification count api
        Notification_list_api();


    }

    private void id() {
        back = findViewById( R.id.back_to_subComplaint );
        user = findViewById( R.id.user_icon_SubComplaint );
        notification = findViewById( R.id.notification_SubComplaint );
        title = findViewById( R.id.sub_complain_name );
        attached = findViewById( R.id.attached );
        problem_image_list = findViewById( R.id.proplem_iamgelist );
        proplem_list = findViewById( R.id.proplem_list );
        property_list = findViewById( R.id.property_list );
        description = findViewById( R.id.complaint_created_descrip );
        submit_btn = findViewById( R.id.submit_complains );
        fileds_created_complains = findViewById( R.id.fileds );
        box = findViewById( R.id.box_frame );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        required = findViewById( R.id.other_required );
    }

    private void noti_count(){
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
    }
    private void fill_data_and_radioBtn_action(){
        title.setText( getIntent().getStringExtra( "complainType_name" ) );
        getIntent().getSerializableExtra("complain_sub_type_model");

         complainSubTypesModels = getIntent().getParcelableArrayListExtra( "complain_sub_type_model" );

        RadioGroup rgp= (RadioGroup) findViewById(R.id.radiogroup);
        RadioGroup.LayoutParams rprms;

        if(complainSubTypesModels != null){
            for(int i=0;i<complainSubTypesModels.size();i++){
                 radioButton = new RadioButton(this);
                radioButton.setText(complainSubTypesModels.get( i ).complaintSubTypeName);
                radioButton.setId(complainSubTypesModels.get( i ).complaintSubTypeId);
                radioButton.setButtonTintList( ColorStateList.valueOf( getColor( R.color.bg_light_blue_color ) ) );
                //  radioButton.setId(View.generateViewId());
                radioButton.setTextColor(Color.BLACK);
                rprms= new RadioGroup.LayoutParams( RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                rgp.addView(radioButton, rprms);
            }
        }
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton.getText();
                RadioButton a  = findViewById( checkedId );
                subtypeName = a.getText().toString();
                if(subtypeName.equals( "Others" )){
                    required.setVisibility( View.VISIBLE );
                }else {
                    required.setVisibility( View.GONE );
                }
                subTypeId = a.getId();

            }
        });

    }
    private void property_Api(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(getApplicationContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //   progress.show();
        Call<UserPropertiesResponse> userPropertiesResponseCall = apiInterface.userPropertiesResponse(token);
        userPropertiesResponseCall.enqueue(new Callback<UserPropertiesResponse>() {
            @Override
            public void onResponse(Call<UserPropertiesResponse> call, Response<UserPropertiesResponse> response) {
                UserPropertiesResponse user = response.body();


                if (user != null) {

                    propertiesModels = new ArrayList<>();
                    if (user.properties != null) {
//
                        for (int i = 0; i < user.properties.size(); i++) {
                            PropertiesModel model = new PropertiesModel();
                            model.setId( user.properties.get( i ).getId() );
                            model.setAddress( user.properties.get( i ).getAddress() );
                            model.setImage( user.properties.get( i ).getImage() );
                            model.setOwner( user.properties.get( i ).getOwner() );
                            model.setLinkToProperty( user.properties.get( i ).getLinkToProperty() );

                            propertiesModels.add( model );
                        }

                        PropertyComplainsCreateAdapter userPropertyAdapter = new PropertyComplainsCreateAdapter( getApplicationContext(), propertiesModels,R.layout.user_property_layout );

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                        property_list.setLayoutManager( linearLayoutManager2 );
                        property_list.setNestedScrollingEnabled(true);
                        property_list.setAdapter( userPropertyAdapter );
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserPropertiesResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });


    }
    private void noti_user_and_back() {
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( getApplicationContext(), Notification.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
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
                Intent a = new Intent( getApplicationContext(), UserProfile.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );

    }

    private void attached_photos(){
        attached.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(images.size() < 4){
                    camera_and_gallery_dialog();
                }
                else {
                    Toast.makeText( getApplicationContext(), R.string.images_limited_text, Toast.LENGTH_SHORT ).show();

                }


            }
        } );
    }
    private void camera_and_gallery_dialog(){
        final Dialog camera_and_gallery_dialog = new Dialog( ComplaintCreated.this );

        camera_and_gallery_dialog.setContentView(R.layout.camera_and_gallery_dailoglayout);
        camera_and_gallery_dialog.setCancelable(true);
        camera_and_gallery_dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.WHITE));
        camera_and_gallery_dialog.getWindow().setLayout( WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT );
        camera_and_gallery_dialog.setCanceledOnTouchOutside(false);
        camera_and_gallery_dialog.show();

        LinearLayout camera = camera_and_gallery_dialog.findViewById( R.id.profileSetting_camera );
        LinearLayout GALLERY = camera_and_gallery_dialog.findViewById( R.id.profileSetting_gallery );

        camera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ContextCompat.checkSelfPermission(ComplaintCreated.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        camera_and_gallery_dialog.dismiss();
                        Intent  intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //intent.setData( MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //  intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE);
                        //  intent.setData( MediaStore.Images.Media.INTERNAL_CONTENT_URI );
                        startActivityForResult(intent, 1);
                    }
                    else
                    {
                        camera_and_gallery_dialog.dismiss();
                        // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
                        ActivityCompat.requestPermissions(ComplaintCreated.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }
                else
                {
                    // if version is below m then write code here,
                }

            }
        } );
        GALLERY.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ContextCompat.checkSelfPermission(ComplaintCreated.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        camera_and_gallery_dialog.dismiss();
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
                        ActivityCompat.requestPermissions(ComplaintCreated.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
                    }
                }
                else
                {
                    // if version is below m then write code here,
                }
            }
        } );



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
        if (requestCode == 1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                Intent  intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
            else
            {
                Toast.makeText(this, "Please Allow the Camera permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void created_comp_api(){
            if(urlStrings != null) {
                try {
                    json = new JSONArray();
                    for (int i = 0; i < urlStrings.size(); i++) {

                        JSONObject json2 = new JSONObject();
                        json2.put( "URL", urlStrings.get( i ) );
                        json.put( json2 );


                    }
                    System.out.println( json1 );
                } catch (Exception e) {
                    System.out.println( "not work" );
                }
            }
            else {
                  try {
                    json = new JSONArray();

                    String link = "https://firebasestorage.googleapis.com/v0/b/pushnotification-96e59.appspot.com/o/images%2Fno-complain.webp?alt=media&token=021f081c-56ab-4c76-bad4-f65c7ab4d119";
                        JSONObject json2 = new JSONObject();
                        json2.put( "URL", link );
                        json.put( json2 );


                } catch (Exception e) {
                    System.out.println( "not work" );
                }
            }
        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(ComplaintCreated.this);

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
         progress.show();
//        MultipartBody.Part[] surveyImagesParts;
//        if(image != null){
//             File[] file = new File[4] ;
//             //  Bitmap[] bitmaps = new Bitmap[4] ;
//             for (int i = 0 ; i<files.size();i++){
//                 file[i] = files.get( i );
//             }
////        for (int i = 0 ; i<image.size();i++){
////            file[i] = files.get( i );
////        }
//            surveyImagesParts = new MultipartBody.Part[image.size()];
//             for (int index = 0; index < image.size(); index++) {
//
//                 File files = new File( file[index].getPath() );
//                 RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), files);
//                 surveyImagesParts[index] = MultipartBody.Part.createFormData("image", files.getName(), requestFile);
//             }
//         }
//        else {
//                surveyImagesParts = null;
//        }



        if (description.getText().toString().equals( null )){
            description.setText( "" );
        }
        RequestBody complainTpyeid = RequestBody.create( MediaType.parse( "multipart/form-data" ),getIntent().getStringExtra( "complainType_id" ).toString()) ;
        RequestBody proID = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( comp_pro_id ));
        RequestBody subComid = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( subTypeId ));
        RequestBody comSub = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( title.getText().toString() ));
        RequestBody comDetail = RequestBody.create( MediaType.parse( "multipart/form-data" ),String.valueOf( description.getText().toString()));

            RequestBody array = RequestBody.create( MediaType.parse( "application/json; charset=utf-8" ),json.toString());



        Call<ComplaintCreateResponse> userPropertiesResponseCall = apiInterface.COMPLAINT_CREATE_RESPONSE_CALL(token,complainTpyeid,proID,subComid,comSub,comDetail,array);
        userPropertiesResponseCall.enqueue(new Callback<ComplaintCreateResponse>() {
            @Override
            public void onResponse(Call<ComplaintCreateResponse> call, Response<ComplaintCreateResponse> response) {
                ComplaintCreateResponse user = response.body();


                if (user != null) {

                    if (user.status != false) {

                        check_com = "check";
                        Intent a = new Intent(getApplicationContext(),ComplaintCreateSuccess.class);
                        a.putExtra( "comp_number",user.ComplainNumber.toString() );
                        startActivity( a );

                    }
                    progress.dismiss();
                } else {
                    Toast.makeText( getApplicationContext(), user.message.toString(), Toast.LENGTH_SHORT ).show();
                    progress.dismiss();
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<ComplaintCreateResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText( getApplicationContext(), "Server Failed", Toast.LENGTH_SHORT ).show();
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });


    }
    private void sumbit_btn(){
        submit_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subtypeName != null){
                    if(subtypeName.equals( "Others" )){
                        if(description.getText().toString().equals( "" )){
                            description.setError( "Please fillout this field" );
                        }
                        else{

                            picture_upload_with_Api();

                        }
                    }
                    else{

                        picture_upload_with_Api();
                    }
                }
                else {
                    Toast.makeText( getApplicationContext(), "Please chose problem", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
    private void picture_upload_with_Api(){
        if(images != null && images.size() != 0){
            ProgressDialog progressDialog= new ProgressDialog(ComplaintCreated.this);
            progressDialog.setMessage("Uploading Images please Wait.....!");

            progressDialog.show();

            upload = new ArrayList<>();
            for (int upload_count = 0; upload_count < images.size(); upload_count++) {
                int random;
                int max =1000;
                int min= 1;
                Random myRandom = new Random();
                random = myRandom.nextInt(max-min+1)+min;
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                String dev_testing =   "DEV/Mobile-Android/Complaint-Images/";
                String prod =   "Prod/mobileImages/Android/Complaint-Images/";
                String uat =   "DEV/mobileImages/";
                Uri IndividualImage = images.get(upload_count);
                StorageReference ref = storageReference.child( dev_testing + "ComplaintImage" + random + ".jpg");


                upload.add( ref.putFile( IndividualImage )  );


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
                                                    created_comp_api();
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
                                        .makeText(ComplaintCreated.this,
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
            //   property_picUpload_api();
            progressDialog.dismiss();

        }
        else {
            created_comp_api();
        }
    }

    private void images_add()
    {
        if (images == null || images.size() == 0 ){
            box.setVisibility( View.GONE );
        }
        if(images != null){
            if(images.size() >0 ){
                box.setVisibility( View.VISIBLE );
            }
            CreateComplaintImagesAdapters complainDetailImagesAdapter = new CreateComplaintImagesAdapters( getApplicationContext(),images,R.layout.created_complaint_layout );
            problem_image_list.setLayoutManager( new GridLayoutManager( getBaseContext(),2 ) );
            problem_image_list.setAdapter(complainDetailImagesAdapter );
            complainDetailImagesAdapter.notifyDataSetChanged();
        }
        else{
            box.setVisibility( View.GONE );

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


                try {
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                        // /  Uri imageUri1, imageUri2, imageUri3, imageUri4, imageUri5, imageUri6;


                        if (count >= 1) {


                            files = new ArrayList<>();


                            if(count >=1)
                            {
                                if(count >= 5 ){
                                    Toast.makeText( getApplicationContext(), "please select only 4 image", Toast.LENGTH_SHORT ).show();
                                }
                                else{
                                    for(int i=0;i<count;i++){

                                        Uri uri=data.getClipData().getItemAt(i).getUri();
                                        images.add( data.getClipData().getItemAt(i).getUri());
//                                        Bitmap bm=null;
//                                        if (data != null) {
//                                            try {
//                                                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                        if (bm != null) { // sanity check
//                                            tempUri = getImageUri(getApplicationContext(), bm);
//                                            finalFile = new File(getRealPathFromURI(tempUri));
//                                         //   finalFile = new File(tempUri.getPath());
////
//                                            files.add( finalFile );
//                                            new File(getRealPathFromURI1(tempUri)).delete();
//                                        }
//                                        image.add(bm);


                                    }
                                    images_add();
                                }

                            }

                        }
                        else {
                            Toast.makeText( getApplicationContext(), "please select image", Toast.LENGTH_SHORT ).show();
                        }

                    }
                    else{

                        files = new ArrayList<>();

                        if(data != null){
//                        if(image.size() >= 0 ){
                            Uri uri = data.getData();
                            images.add( data.getData());
//                            Bitmap bm=null;
//                            if (data != null) {
//                                try {
//                                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (bm != null) { // sanity check
//                                tempUri = getImageUri(getApplicationContext(), bm);
//                                finalFile = new File(getRealPathFromURI(tempUri));
//                                //finalFile = new File(tempUri.getPath());
//                                files.add( finalFile );
//                                new File(getRealPathFromURI1(tempUri)).delete();
//                            }
//
//                            image.add(bm);
                            images_add();
//                        }
//                        else{
//                            Toast.makeText( getApplicationContext(), "only 4 image you select only", Toast.LENGTH_SHORT ).show();
//                        }
                        }
                        else {

                            files = new ArrayList<>();

                            Bitmap bm=null;
                            if (data != null) {
                                Uri uri = data.getData();
                                images.add( data.getData() );
                            }
//                                try {
//                                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (bm != null) { // sanity check
//                                tempUri = getImageUri(getApplicationContext(), bm);
//                                finalFile = new File(getRealPathFromURI(tempUri));
//                             //   finalFile = new File(tempUri.getPath());
//                                files.add( finalFile );
//                            }
//                            new File(getRealPathFromURI1(tempUri)).delete();
//                            image.add(bm);
                            images_add();

                        }



                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Select Multiple Image", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            else if(requestCode == 1){
                if (data != null) {
                    if (requestCode == 1) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get( "data" );
                        Uri filePath = getImageUri( getApplicationContext(), bitmap );
                        if(images.size() < 4){
                            images.add( filePath );
                            images_add();
                        }
                        else {
                            Toast.makeText( getApplicationContext(), "please select only 4 image", Toast.LENGTH_SHORT ).show();

                        }

                    }
                }
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
     //   inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
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
    public String getRealPathFromURI1(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    protected void onResume() {
        Notification_list_api();
        super.onResume();
        if(check_com != null && check_com.equals( "check" )){
          Intent a =  new Intent(getApplicationContext(),ComplaintCreated.class);
          a.putParcelableArrayListExtra( "complain_sub_type_model" ,complainSubTypesModels);
          a.putExtra( "complainType_name",titles );
          startActivity( a );
          finish();
        }
    }
}