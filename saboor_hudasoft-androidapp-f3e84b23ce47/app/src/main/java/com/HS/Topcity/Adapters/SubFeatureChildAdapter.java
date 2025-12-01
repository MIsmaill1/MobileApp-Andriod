package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.Models.SubFeaturesModels;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SubFeatureChildAdapter extends RecyclerView.Adapter<SubFeatureChildAdapter.viewHolder>{
    Context context;
    List<SubFeaturesModels> subFeaturesModels;
    ArrayList<String> Images;
    ArrayList<String> image;



    public SubFeatureChildAdapter(Context context, ArrayList<String> Images , int newsannoce){
        this.context = context;
        this.Images = Images;

    }
    @NonNull
    @Override
    public SubFeatureChildAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.sub_feature_image_layout,parent,false );
        return new SubFeatureChildAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull SubFeatureChildAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {



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
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.putExtra( "position",position );
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

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.subFeature_list_image);
        }
    }
}
