package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.Models.EventDetailsModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class EvenDetailsAdapter extends RecyclerView.Adapter<EvenDetailsAdapter.viewHolder> {

    Context context;
    List<EventDetailsModel> eventsDetailsModels;
    List<String> Images;
    ArrayList<String> image;
  //  ArrayList<EventDetailsImageModel> eventDetailsImageModels;



    public EvenDetailsAdapter(Context context, List<String> Images , int newsannoce){
        this.context = context;
        this.Images = Images;

    }
    @NonNull
    @Override
    public EvenDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.event_layout,parent,false );
        return new EvenDetailsAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull EvenDetailsAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

     //   List<String> model = Images.get( position );


      //  EventDetailsModel model = eventsDetailsModels.get( position );

      //  EventDetailsImageModel model1 = eventDetailsImageModels.get( position );

      //  event_details_name.setText( eventsDetailsModels.get( 0 ).getEventName() );


//        for (int i = 0 ; i < eventsDetailsModels.size() ; i++)
//        {
//        if (Images.size() == 1){
//          //  int last_pos = Images.size() - 1 ;
//            if (holder.getAdapterPosition() == 1){
//                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
//                        ConstraintLayout.LayoutParams.mat,
//                        ConstraintLayout.LayoutParams.MATCH_PARENT
//                );
//             //   params.setMarginEnd( 10 );
//
//                params.setMarginStart( 10 );
//                holder.open.setLayoutParams( params);
//            }
//        }

      //  }

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
                a.putExtra( "position",position );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a);
            }
        } );
       // }





    }

    @Override
    public int getItemCount() {
        return Images.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
      //  TextView title,descrpition;
        ConstraintLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.event_Image);
            open = itemView.findViewById( R.id.open);
           // descrpition = itemView.findViewById( R.id.news_annoce_list_descrip );


        }
    }
}
