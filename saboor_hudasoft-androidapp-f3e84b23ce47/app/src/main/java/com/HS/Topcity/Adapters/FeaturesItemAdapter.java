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

import com.HS.Topcity.Activity.SubFeature;
import com.HS.Topcity.Models.Feature_itemModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeaturesItemAdapter extends RecyclerView.Adapter<FeaturesItemAdapter.viewHolder> {

    Context context;
    ArrayList<Feature_itemModel> feature_itemModels;



    public FeaturesItemAdapter(Context context, ArrayList<Feature_itemModel> feature_itemModels, int newsannoce){
        this.context = context;
        this.feature_itemModels = feature_itemModels;

    }
    @NonNull
    @Override
    public FeaturesItemAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.feature_item_layout,parent,false );
        return new FeaturesItemAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturesItemAdapter.viewHolder holder, int position) {


        Feature_itemModel model = feature_itemModels.get( position );


        holder.title.setText( model.getName() );

        Glide.with( context ).load( model.getImage()).into( holder.image );
      //  holder.image.setBackgroundResource(   model.getImage()  );
        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a  = new Intent(context, SubFeature.class);
                a.putExtra( "feature_id",String.valueOf(model.getId() ));
                a.putExtra( "feature_name",String.valueOf(model.getName() ));
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );

            }
        } );

    }

    @Override
    public int getItemCount() {
        return feature_itemModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.featureitemimage );
            title = itemView.findViewById( R.id.feature_itemname );
            open = itemView.findViewById( R.id.item_box_frame );

        }
    }
}
