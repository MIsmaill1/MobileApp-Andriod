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

import com.HS.Topcity.Activity.SubCommunityService;
import com.HS.Topcity.Models.CommunityServiceModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

public class CommunityServiceAdapter extends RecyclerView.Adapter<CommunityServiceAdapter.viewHolder>{
    Context context;
    ArrayList<CommunityServiceModel> communityServiceModels;



    public CommunityServiceAdapter(Context context, ArrayList<CommunityServiceModel> communityServiceModels, int newsannoce){
        this.context = context;
        this.communityServiceModels = communityServiceModels;

    }
    @NonNull
    @Override
    public CommunityServiceAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.community_service_item_layout,parent,false );
        return new CommunityServiceAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityServiceAdapter.viewHolder holder, int position) {


        CommunityServiceModel model = communityServiceModels.get( position );


        holder.title.setText( model.getName() );

        //Glide.with( context ).load( model.getImage()).into( holder.image );
        holder.image.setImageResource( model.getImages() );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a  = new Intent(context, SubCommunityService.class);
                a.putExtra( "community_id",String.valueOf(model.getId() ));
                a.putExtra( "Community_name",String.valueOf(model.getName() ));
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );

            }
        } );

    }

    @Override
    public int getItemCount() {
        return communityServiceModels.size();
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
