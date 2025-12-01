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

import com.HS.Topcity.Activity.UserSharedAccountDetail;
import com.HS.Topcity.Models.UserSharedAccountListModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SharedAccountLisrAdapter extends RecyclerView.Adapter<SharedAccountLisrAdapter.viewHolder> {

    Context context;
    ArrayList<UserSharedAccountListModel> userSharedAccountListModels;



    public SharedAccountLisrAdapter(Context context, ArrayList<UserSharedAccountListModel> userSharedAccountListModels, int newsannoce){
        this.context = context;
        this.userSharedAccountListModels = userSharedAccountListModels;

    }
    @NonNull
    @Override
    public SharedAccountLisrAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.shared_account_user_list_layout,parent,false );
        return new SharedAccountLisrAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull SharedAccountLisrAdapter.viewHolder holder, int position) {


        UserSharedAccountListModel model = userSharedAccountListModels.get( position );


        holder.name.setText( model.getFullName() );

        Glide.with( context ).load( model.getImage()).into( holder.image );
        holder.status.setText( model.getStateOfAccount() );
        holder.pro_name.setText( model.getPropertyName() );
        holder.role.setText( model.getTypeOfAccount().toString() );
        holder.update_Details.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(context, UserSharedAccountDetail.class);
                a.putExtra( "userId",String.valueOf( model.getId() ) );
                a.putExtra( "accountTypeId",String.valueOf( model.getTypeAccountId() ) );
                a.putExtra( "accountType",String.valueOf( model.getTypeOfAccount() ) );
                a.putExtra( "propertyId",String.valueOf( model.getPropertyId() ) );
                a.putExtra( "image",String.valueOf( model.getImage() ) );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );
            }
        } );



    }

    @Override
    public int getItemCount() {
        return userSharedAccountListModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView name,role,status,pro_name;
        LinearLayout update_Details;


        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.Shared_account_image );
            name = itemView.findViewById( R.id.Shared_account_name );
            role = itemView.findViewById( R.id.Shared_account_role );
            pro_name = itemView.findViewById( R.id.Shared_account_address );
            status = itemView.findViewById( R.id.shared_Account_status );
            update_Details = itemView.findViewById( R.id.shared_Account_updateDetail_btn );

        }
    }
}
