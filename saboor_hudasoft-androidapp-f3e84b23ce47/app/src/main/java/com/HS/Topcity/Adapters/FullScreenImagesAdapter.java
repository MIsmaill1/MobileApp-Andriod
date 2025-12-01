package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.FullScreenImagesActivity.item_position;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.CustomClasses.TouchPagerImageView;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FullScreenImagesAdapter extends RecyclerView.Adapter<FullScreenImagesAdapter.viewHolder>{
    Context context;
    ArrayList<String> eventImagesModels;
    private static int lastClickedPosition = -1;
    private int selectedItem;



    public FullScreenImagesAdapter(Context context, ArrayList<String> eventImagesModels, int newsannoce){
        this.context = context;
        this.eventImagesModels = eventImagesModels;
        selectedItem = 0;
    }
    @NonNull
    @Override
    public FullScreenImagesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.full_screen_image_layout,parent,false );
        return new FullScreenImagesAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull FullScreenImagesAdapter.viewHolder holder, int position) {


        position = item_position;
//      if(eventImagesModels.size() < position){
//          if(position > 0 ){
//              position
//          }
//      }

        Glide.with( context ).load( eventImagesModels.get( position )).into( holder.image );

        if (selectedItem == position) {


        }


    }

    @Override
    public int getItemCount() {
        return eventImagesModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType( item_position );
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        TouchPagerImageView image ;
        TextView title;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.full_screen_image );


        }
    }
}
