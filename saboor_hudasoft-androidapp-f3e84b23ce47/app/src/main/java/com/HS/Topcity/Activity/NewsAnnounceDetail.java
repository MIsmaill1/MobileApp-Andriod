package com.HS.Topcity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.HS.Topcity.Adapters.NewsAnnoceDetailPageAdapter;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class NewsAnnounceDetail extends AppCompatActivity {

    ShapeableImageView item_image;
        LinearLayout back;
    public static  ViewPager2 viewPager;
    TextView item_date,item_time,item_description,notes,next,perivous;

   public static RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_announce_deatil );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
      //  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // All ids
        ids();

    // attach recyleview Adapter
    //    attach_adapter();
        // back to perious Screen Funtionality
        back();

        viewPager = findViewById(R.id.view_pager);


        ArrayList<NewsAnnouncementModel> newsAnnouncementModels = getIntent().getParcelableArrayListExtra( "announce Array" );


        final int pos = getIntent().getIntExtra("position", 0);


        NewsAnnoceDetailPageAdapter adapter2 = new NewsAnnoceDetailPageAdapter( getApplicationContext(), newsAnnouncementModels,NewsAnnounceDetail.this,R.layout.full_screen_image_layout );

        viewPager.setAdapter(adapter2);
        viewPager.setCurrentItem(pos,false);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                //      Toast.makeText(FullImages.this, "Selected: " + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }

    private void ids(){
        recyclerView =findViewById( R.id.news_annoce_detail_page );
        back = findViewById( R.id.backtodashboard );
    }
    private void back(){
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
    private void attach_adapter(){
        ArrayList<NewsAnnouncementModel> newsAnnouncementModels = getIntent().getParcelableArrayListExtra( "announce Array" );



        NewsAnnoceDetailPageAdapter imagesAdapter = new NewsAnnoceDetailPageAdapter( getApplicationContext(), newsAnnouncementModels,NewsAnnounceDetail.this,R.layout.full_screen_image_layout );

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );


        recyclerView.setLayoutManager( linearLayoutManager2);
        recyclerView.setNestedScrollingEnabled(false);
    //    recyclerView.getLayoutManager().findViewByPosition(getIntent().getIntExtra( "position",0 ) );
     //   recyclerView.smoothScrollToPosition(getIntent().getIntExtra( "position",0 ));
        recyclerView.setAdapter( imagesAdapter );


        PagerSnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);

    }

    private void setValues(){

        Glide.with( this ).load( getIntent().getStringExtra( "image" ) ).into( item_image );
        item_date.setText( getIntent().getStringExtra( "date" ) );
        item_time.setText( getIntent().getStringExtra( "time" ) );
        item_description.setText( getIntent().getStringExtra( "descrpition" ) );
        notes.setText( getIntent().getStringExtra( "notes" ) );

    }
}