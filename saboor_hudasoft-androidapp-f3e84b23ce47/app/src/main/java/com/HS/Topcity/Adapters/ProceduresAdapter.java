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

import com.HS.Topcity.Activity.Allotment;
import com.HS.Topcity.Activity.Possession;
import com.HS.Topcity.Activity.Transfer;
import com.HS.Topcity.Activity.WaterConnection;
import com.HS.Topcity.Models.CommunityServiceModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

public class ProceduresAdapter extends RecyclerView.Adapter<ProceduresAdapter.viewHolder>{
    Context context;
    ArrayList<CommunityServiceModel> communityServiceModels;



    public ProceduresAdapter(Context context, ArrayList<CommunityServiceModel> communityServiceModels, int newsannoce){
        this.context = context;
        this.communityServiceModels = communityServiceModels;

    }
    @NonNull
    @Override
    public ProceduresAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.community_service_item_layout,parent,false );
        return new ProceduresAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ProceduresAdapter.viewHolder holder, int position) {


        CommunityServiceModel model = communityServiceModels.get( position );


        holder.title.setText( model.getName() );

        //Glide.with( context ).load( model.getImage()).into( holder.image );
        holder.image.setImageResource( model.getImages() );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(model.getName().equals( "Transfer" )){

                    Intent browserIntent = new Intent(context, Transfer.class );
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
                else if(model.getName().equals( "Allotment" )){
                    Intent browserIntent = new Intent(context, Allotment.class );
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
                else if(model.getName().equals( "Possession" )){
                    Intent browserIntent = new Intent(context, Possession.class );
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
                else if(model.getName().equals( "Water Connection" )){
                    Intent browserIntent = new Intent(context, WaterConnection.class );
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }

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