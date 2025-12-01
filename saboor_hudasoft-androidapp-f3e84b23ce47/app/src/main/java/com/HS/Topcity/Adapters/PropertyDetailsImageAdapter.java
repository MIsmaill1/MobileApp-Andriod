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
import com.HS.Topcity.Models.PropertyDetailsimageModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PropertyDetailsImageAdapter extends RecyclerView.Adapter<PropertyDetailsImageAdapter.viewHolder>{
    Context context;

   ArrayList<PropertyDetailsimageModel> propertyImagesList;
   ArrayList<String> image;



    public PropertyDetailsImageAdapter(Context context, ArrayList<PropertyDetailsimageModel> propertyImagesList, int newsannoce){
        this.context = context;
        this.propertyImagesList = propertyImagesList;

    }
    @NonNull
    @Override
    public PropertyDetailsImageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.property_details_images_layout,parent,false );
        return new PropertyDetailsImageAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyDetailsImageAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {



        Glide.with( context ).load( propertyImagesList.get( position ).Image).into( holder.image );
        image =  new ArrayList<>();
        if(propertyImagesList.size() !=0){
            for (int i = 0; i<propertyImagesList.size();i++)
            {
                image.add(propertyImagesList.get( i ).Image  );
            }
        }


        holder.image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(context, FullImages.class);
                a.putStringArrayListExtra( "Array",image);
                a.putExtra( "position",position );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a);
            }
        } );


    }

    @Override
    public int getItemCount() {
        return propertyImagesList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.details_image1 );


        }
    }
}
