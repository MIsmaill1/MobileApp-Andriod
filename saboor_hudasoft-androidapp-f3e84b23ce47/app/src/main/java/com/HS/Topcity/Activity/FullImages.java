package com.HS.Topcity.Activity;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.HS.Topcity.CustomClasses.ExtendedViewPager;
import com.HS.Topcity.CustomClasses.TouchImageView;
import com.HS.Topcity.CustomClasses.TouchPagerImageView;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.yc.gallerylib.recyclerView.GalleryRecyclerView;
import com.yc.gallerylib.recyclerView.OnGalleryListener;

import java.util.ArrayList;

public class FullImages extends AppCompatActivity {
    private ViewPager2 viewPager;
    ArrayList<String> eventImagesModels;
    LinearLayout back;
    private ImageSwiperAdapter2 adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_images);
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        viewPager = findViewById(R.id.view_pager);

        viewPager.requestDisallowInterceptTouchEvent(true);
        back = findViewById(R.id.back);

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        eventImagesModels = getIntent().getStringArrayListExtra("Array");

        final int pos = getIntent().getIntExtra("position", 0);


        adapter2 = new ImageSwiperAdapter2(eventImagesModels, this);



        viewPager.setAdapter(adapter2);
        viewPager.setCurrentItem(pos,false);
        viewPager.requestDisallowInterceptTouchEvent(true);
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

        ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager1);
        mViewPager.setAdapter(new TouchImageAdapter( eventImagesModels));
        mViewPager.setCurrentItem(pos,false);
        mViewPager.requestDisallowInterceptTouchEvent(true);

//        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//
//                //      Toast.makeText(FullImages.this, "Selected: " + position, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });

        gallery(eventImagesModels,pos);
    }

    private void gallery(ArrayList<String> eventImagesModels,int pos){
        GalleryRecyclerView mRecyclerView = findViewById( R.id.recyclerView );
        mRecyclerView
                //设置滑动速度
                .setFlingSpeed(10000)
                //设置adapter
                .setDataAdapter(new ImageSwiperAdapter2(eventImagesModels,getApplicationContext()))
                //设置选中的索引
                .setSelectedPosition(pos)
                //设置横向或者竖向，注意需要限制传入类型
                .setOrientation( RecyclerView.HORIZONTAL)
                //设置滚动监听事件
                .setOnGalleryListener(new OnGalleryListener() {
                    @Override
                    public void onInitComplete() {
                        Log.e(TAG,"onInitComplete初始化完成");
                    }

                    @Override
                    public void onPageRelease(boolean isNext,int position) {
                        Log.e(TAG,"释放的监听，释放位置:"+position +" 下一页:"+isNext);
                        if (isNext){
                            Log.e(TAG,"释放的监听，释放位置:"+position +" 下一页:"+isNext);
                        }else {
                            Log.e(TAG,"释放的监听，释放位置:"+position +" 上一页:"+isNext);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.e(TAG,"释放的监听，释放位置:"+position +" 当前页:"+position);
                    }
                })
                //装载
                .setUp();
    }
    class ImageSwiperAdapter2 extends RecyclerView.Adapter<ImageSwiperAdapter2.ImageSwiper> {

        ArrayList<String> eventImagesModels;
        private Context context;

        public ImageSwiperAdapter2( ArrayList<String> eventImagesModels, Context context) {
            this.eventImagesModels = eventImagesModels;
            this.context = context;
        }

        @NonNull
        @Override
        public ImageSwiper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_image_layout,
                    parent, false);


            return new ImageSwiper(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ImageSwiper holder, int position) {

//        Random rnd = new Random();
//        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//        holder.relativeLayout.setBackgroundColor(color);

            Glide.with(context.getApplicationContext())
                    .load(eventImagesModels.get(position))
                    .into(holder.imageView);



        }

        @Override
        public int getItemCount() {
            return eventImagesModels.size();
        }

       public class ImageSwiper extends RecyclerView.ViewHolder {


            private TouchImageView imageView;



            public ImageSwiper(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
            //    image = itemView.findViewById(R.id.image);


            }


        }
    }
     class TouchImageAdapter extends PagerAdapter {
       // ArrayList<String> images;
     //   private static int[] images = { R.drawable.img1, R.drawable.img2, R.drawable.img3 };

         ArrayList<String> eventImagesModel;
         public TouchImageAdapter(ArrayList<String> eventImagesModel) {
             this.eventImagesModel = eventImagesModel;
         }

         @Override
        public int getCount() {
            return eventImagesModel.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchPagerImageView img = new TouchPagerImageView(container.getContext());
            Glide.with( FullImages.this ).load( eventImagesModel.get( position ) ).into( img );

            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}