package com.HS.Topcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.CustomClasses.TouchImageView;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class ImageSwiperAdapter2 extends RecyclerView.Adapter<ImageSwiperAdapter2.ImageSwiper> {

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
                    .listener( new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    }
                    //{
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                       // holder..setBackgroundColor( Color.TRANSPARENT);
//                        return false;
//                    }

//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                  //      holder.relativeLayout.setBackgroundColor(Color.TRANSPARENT);
//
//                        return false;
//                    }
//                })
                    ).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return eventImagesModels.size();
    }

    class ImageSwiper extends RecyclerView.ViewHolder {


        private TouchImageView imageView;


        public ImageSwiper(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);


        }


    }
}
