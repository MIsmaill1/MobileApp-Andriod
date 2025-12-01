package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.Models.PropertyOwnersDetailModels;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PropertyOwnerDetailsAdapter extends RecyclerView.Adapter<PropertyOwnerDetailsAdapter.viewHolder>{
    Context context;
    ArrayList<PropertyOwnersDetailModels> propertyOwnersDetailModels;
    ArrayList<String> image;



    public PropertyOwnerDetailsAdapter(Context context, ArrayList<PropertyOwnersDetailModels> propertyOwnersDetailModels, int newsannoce){
        this.context = context;
        this.propertyOwnersDetailModels = propertyOwnersDetailModels;

    }
    @NonNull
    @Override
    public PropertyOwnerDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.property_owner_layout,parent,false );
        return new PropertyOwnerDetailsAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyOwnerDetailsAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {


        PropertyOwnersDetailModels model = propertyOwnersDetailModels.get( position );

//        holder.month.setText( model.getDateofNews() );
//        holder.date.setText( model.getDateofNews());


        holder.name.setText( model.getPropertyOwnerName() );
        holder.phone.setText( model.getPropertyOwnerContactNumber() );
        holder.currentAddres.setText( model.getPropertyOwnerCurrentAddress() );
        Glide.with( context ).load( model.getPropertyOwnerImage()).into( holder.owner_image );

        image =  new ArrayList<>();
        if(propertyOwnersDetailModels.size() !=0){
            for (int i = 0; i<propertyOwnersDetailModels.size();i++)
            {
                image.add(propertyOwnersDetailModels.get( i ).propertyOwnerImage  );
            }
        }


        holder.owner_image.setOnClickListener( new View.OnClickListener() {
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
        return propertyOwnersDetailModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView owner_image ;
        TextView name,phone,currentAddres;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            name= itemView.findViewById( R.id.property_detail_userName );
            phone= itemView.findViewById( R.id.property_detail_userNumber );
            currentAddres = itemView.findViewById( R.id.property_detail_currentAddress );
            owner_image = itemView.findViewById( R.id.user_image );

        }
    }
}
