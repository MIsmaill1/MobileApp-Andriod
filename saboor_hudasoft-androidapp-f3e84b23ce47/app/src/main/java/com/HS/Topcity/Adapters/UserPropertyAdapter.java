package com.HS.Topcity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.PropertyDetails;
import com.HS.Topcity.Models.PropertiesModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserPropertyAdapter extends RecyclerView.Adapter<UserPropertyAdapter.viewHolder> {

    Context context;
    ArrayList<PropertiesModel> properties;


    public UserPropertyAdapter(Context context, ArrayList<PropertiesModel> properties, int propertiesLayout){
        this.context = context;
        this.properties = properties;
    }
    @NonNull
    @Override
    public UserPropertyAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.user_property_layout,parent,false );
        return new UserPropertyAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull UserPropertyAdapter.viewHolder holder, int position) {


        PropertiesModel model = properties.get( position );

//      for (int i = 1 ; i<position ; i++){
//
//          ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.open.getLayoutParams();
//          params.setMarginStart( -20 );
//          holder.open.setLayoutParams(params);
//      }

//        holder.month.setText( model.getDateofNews() );
//        holder.date.setText( model.getDateofNews());

        holder.address.setText( model.getAddress() );
       // holder.descrpition.setText( model.getDescriptions() );
       Glide.with( context ).load( model.getImage()).into( holder.image );


holder.open.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent a = new Intent(context , PropertyDetails.class );
        a.putExtra( "address",model.getAddress() );
        a.putExtra( "image",model.getImage() );
        a.putExtra( "id" , String.valueOf( model.getId()  ));

        a.putExtra( "isOwner" ,String.valueOf(model.getOwner()  ));
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity( a );
    }
} );

//for (int i = 1 ; i < properties.size() ; i++)
//{
//    if (position == i){
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT
//        );
//        params.setMarginStart( -20 );
//        holder.open.setLayoutParams( params);
//    }
//}



    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView address;
        FrameLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.property_image );
            address = itemView.findViewById( R.id.property_address );
            open = itemView.findViewById( R.id.property_Open);



        }
    }
}
