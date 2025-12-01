package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DevelopmentImagesAdapter extends RecyclerView.Adapter<DevelopmentImagesAdapter.viewHolder>{
    Context context;
    ArrayList<String> Images;
    ArrayList<String> image;



    public DevelopmentImagesAdapter(Context context, ArrayList<String> Images, int newsannoce){
        this.context = context;
        this.Images = Images;

    }
    @NonNull
    @Override
    public DevelopmentImagesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.development_images_layout,parent,false );
        return new DevelopmentImagesAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopmentImagesAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {




        Glide.with( context ).load( Images.get( position )).into( holder.image );

        image =  new ArrayList<>();
        if(Images.size() !=0){
            for (int i = 0; i<Images.size();i++)
            {
                image.add(Images.get( i )  );
            }
        }


        holder.image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(context, FullImages.class);
                a.putStringArrayListExtra( "Array",image);
                a.putExtra( "position",position);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a);
            }
        } );


    }

    @Override
    public int getItemCount() {
        return Images.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.event_Image );


        }
    }
}

