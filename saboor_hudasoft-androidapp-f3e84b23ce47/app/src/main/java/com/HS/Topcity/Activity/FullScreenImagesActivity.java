package com.HS.Topcity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.FullScreenImagesAdapter;
import com.HS.Topcity.CustomClasses.TouchImageView;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FullScreenImagesActivity extends AppCompatActivity {

    ArrayList<String> image;
    RecyclerView imagesList;
    LinearLayout back;
    TouchImageView touchImageView;
    public static int item_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_full_screen_images );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
  //      getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();
        // back to perivous screeen
        back();
        // full screen image reccyle view adapter attach
        full_screen_image();



    }
    private void ids(){
        back = findViewById( R.id.back );
        imagesList = findViewById( R.id.images );
        touchImageView = findViewById( R.id.images1 );
    }
    private void full_screen_image(){
        image = getIntent().getStringArrayListExtra("Array");

        Glide.with( getApplicationContext() ).load( image.get( 0 ).toString() ).into( touchImageView );
        FullScreenImagesAdapter imagesAdapter = new FullScreenImagesAdapter( getApplicationContext(), image,R.layout.full_screen_image_layout );

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
        imagesList.setLayoutManager( linearLayoutManager2 );
        imagesList.setNestedScrollingEnabled(false);
        imagesList.setAdapter( imagesAdapter );
        imagesList.post(new Runnable() {
            @Override
            public void run() {
                View view = imagesList.getLayoutManager().findViewByPosition(getIntent().getIntExtra( "position",0 ));
            }
        });
       // imagesList.smoothScrollToPosition(getIntent().getIntExtra( "position",0 ));
        item_position = getIntent().getIntExtra( "position",0 );
//        imagesAdapter.getItemViewType( item_position );
        imagesList.findViewHolderForAdapterPosition( 3 );
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(imagesList);
    }
    private void back(){
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
}