package com.HS.Topcity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.ComplaintCreated;
import com.HS.Topcity.Models.ComplainSubTypesModels;
import com.HS.Topcity.Models.ComplaintTypeModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AddNewComplainAdapter extends RecyclerView.Adapter<AddNewComplainAdapter.viewHolder> {

    Context context;
    ArrayList<ComplainSubTypesModels> complainSubTypesModels;
    ArrayList<ComplaintTypeModel> complaintTypeModels;

    public AddNewComplainAdapter(Context context, ArrayList<ComplaintTypeModel> complaintTypeModels, ArrayList<ComplainSubTypesModels> complainSubTypesModels, int newsannoce){
        this.context = context;
        this.complaintTypeModels = complaintTypeModels;
        this.complainSubTypesModels = complainSubTypesModels;

    }
    @NonNull
    @Override
    public AddNewComplainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.complaint_addnew_layout,parent,false );
        return new AddNewComplainAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull AddNewComplainAdapter.viewHolder holder, int position) {


        ComplaintTypeModel model = complaintTypeModels.get( position );


        holder.title.setText( model.getComplaintTypeName() );

        Glide.with( context ).load( model.getComplaintTypeImage()).into( holder.image );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a  = new Intent(context, ComplaintCreated.class);
                a.putExtra( "complainType_id",String.valueOf(model.getComplaintTypeId() ));
                    a.putExtra( "complainType_name",String.valueOf(model.getComplaintTypeName() ));
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("mylist", model.complainSubTypesModels);
                a.putParcelableArrayListExtra("complain_sub_type_model", model.complainSubTypesModels );
               //     a.putExtra( "complain_sub_type_model",model.complainSubTypesModels);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );

            }
        } );

    }

    @Override
    public int getItemCount() {
        return complaintTypeModels.size();
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
