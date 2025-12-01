package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.AddSharedAccount.propertyid;
import static com.HS.Topcity.Activity.ComplaintCreated.comp_pro_id;
import static com.HS.Topcity.Activity.ComplaintCreated.fileds_created_complains;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.PropertiesModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PropertyComplainsCreateAdapter extends RecyclerView.Adapter<PropertyComplainsCreateAdapter.viewHolder> {
    Context context;
    ArrayList<PropertiesModel> properties;
    boolean check;
    boolean statusVisible;
    private static int lastClickedPosition = -1;
    private int selectedItem;


    public PropertyComplainsCreateAdapter(Context context, ArrayList<PropertiesModel> properties, int propertiesLayout){
        this.context = context;
        this.properties = properties;
        selectedItem = 0;
    }
    @NonNull
    @Override
    public PropertyComplainsCreateAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.share_your_account_layout,parent,false );
        return new PropertyComplainsCreateAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyComplainsCreateAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {


        PropertiesModel model = properties.get( position );


        holder.open.setTag( 1 );

//      for (int i = 1 ; i<position ; i++){
//
//          ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.open.getLayoutParams();
//          params.setMarginStart( -20 );
//          holder.open.setLayoutParams(params);
//      }
        if (model.getOwner() == false)
        {
            holder.open.setVisibility( View.GONE );
            //  textfileds.setVisibility( View.GONE );
        }
        else {
            holder.open.setVisibility( View.VISIBLE );
            if (selectedItem == position) {
                holder.image.setAlpha(1f);
                holder.check.setVisibility( View.VISIBLE );
                propertyid = model.getId();
                comp_pro_id = model.getId();
                fileds_created_complains.setVisibility( View.VISIBLE );
            }
            else {
                holder.check.setVisibility( View.GONE );
            }
        }


        holder.address.setText( model.getAddress() );

        Glide.with( context ).load( model.getImage()).into( holder.image );


        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.check.setVisibility( View.VISIBLE );
//                propertyid = model.getId();


                int previousItem = selectedItem;
                selectedItem = position;

                notifyItemChanged(previousItem);
                notifyItemChanged(position);



            }
        } );

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image,check ;
        TextView address;
        FrameLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.property_image );
            address = itemView.findViewById( R.id.property_address );
            open = itemView.findViewById( R.id.property_Open);
            check = itemView.findViewById( R.id.select_property );




        }
    }
}
