package com.HS.Topcity.Adapters;

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

import com.HS.Topcity.Activity.EventDetails;
import com.HS.Topcity.Models.EventImagesModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EventimageAdapter extends RecyclerView.Adapter<EventimageAdapter.viewHolder>{

    Context context;
    ArrayList<EventImagesModel> eventImagesModels;



    public EventimageAdapter(Context context, ArrayList<EventImagesModel> eventImagesModels, int newsannoce){
        this.context = context;
        this.eventImagesModels = eventImagesModels;

    }
    @NonNull
    @Override
    public EventimageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.event_layout,parent,false );
        return new EventimageAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull EventimageAdapter.viewHolder holder, int position) {


        EventImagesModel model = eventImagesModels.get( position );

        Glide.with( context ).load( model.getImage()).into( holder.image );

        holder.image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(context, EventDetails.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );
            }
        } );



    }

    @Override
    public int getItemCount() {
        return eventImagesModels.size();
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
