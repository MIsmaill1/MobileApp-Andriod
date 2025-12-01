package com.HS.Topcity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.Activity.FullScreenImagesActivity;
import com.HS.Topcity.Models.ComplaintImagesModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ComplainDetailImagesAdapter extends RecyclerView.Adapter<ComplainDetailImagesAdapter.viewHolder> {
    Context context;
    boolean isImageFitToScreen;
    List<ComplaintImagesModel> Images;
    ArrayList<String> image;



    public ComplainDetailImagesAdapter(Context context, List<ComplaintImagesModel> Images , int newsannoce){
        this.context = context;
        this.Images = Images;

    }
    @NonNull
    @Override
    public ComplainDetailImagesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.complaint_images_layout,parent,false );
        return new ComplainDetailImagesAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainDetailImagesAdapter.viewHolder holder, int position) {



        Glide.with( context ).load( Images.get( position ).getImage()).into( holder.image );

        image = new ArrayList<>();
        if(Images.size() !=0){
            for (int i = 0; i<Images.size();i++)
            {
                image.add(Images.get( i ).getImage()  );
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
        return Images.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.comp_Image);
        }
    }
}
