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

import com.HS.Topcity.Activity.ComplaintDetails;
import com.HS.Topcity.Models.ComplainsModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserComplainAdapter extends RecyclerView.Adapter<UserComplainAdapter.viewHolder>{
    Context context;
    ArrayList<ComplainsModel>complainsModels;


    public UserComplainAdapter(Context context, ArrayList<ComplainsModel> complainsModels, int propertiesLayout){
        this.context = context;
        this.complainsModels = complainsModels;
    }
    @NonNull
    @Override
    public UserComplainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.complains_layout,parent,false );
        return new UserComplainAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull UserComplainAdapter.viewHolder holder, int position) {


        ComplainsModel model = complainsModels.get( position );

        holder.name.setText( model.getName() );
        holder.number.setText( model.getNumber() );
        holder.status.setText( model.getStatus() );

        holder.view_detil.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a  = new Intent(context, ComplaintDetails.class);
                a.putExtra( "id",String.valueOf(model.getId() ));
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );

            }
        } );
        if(holder.status.getText().toString().equals( "Pending" ) || holder.status.getText().toString().equals( "InProgress" ))
        {
            holder.status.setTextColor(this.context.getColor( R.color.bg_mhendi_color ));
            holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_pending_status, 0, 0, 0);


        }
         if(holder.status.getText().toString().equals( "Close" ) || holder.status.getText().toString().equals( "Cancel" ) || holder.status.getText().toString().equals( "Withdraw" ) || holder.status.getText().toString().equals( "Invalid" ) || holder.status.getText().toString().equals( "OverTime" ))
        {
            holder.status.setTextColor(this.context.getColor( R.color.red ));
            holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_close_status, 0, 0, 0);

        }

        if(holder.status.getText().toString().equals( "Active" )){
            holder.status.setTextColor(this.context.getColor( R.color.dark_green ));
            holder.status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_active_status, 0, 0, 0);

        }


//        if(holder.status.getText().equals( "Pending" ))
//        {
//            holder.status.setTextColor(this.context.getColor( R.color.bg_mhendi_color ));
//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_active_status);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable, this.context.getColor( R.color.bg_mhendi_color ));
//        }
//         if(holder.status.getText().equals( "InProgress" ))
//        {
//            holder.status.setTextColor(this.context.getColor( R.color.bg_mhendi_color ));
//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_active_status);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable, this.context.getColor( R.color.bg_mhendi_color ));
//        }
//         if(holder.status.getText().equals( "Close" ))
//        {
//            holder.status.setTextColor(this.context.getColor( R.color.bg_mhendi_color ));
//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_active_status);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable,  Color.RED );
//        }
//         if(holder.status.getText().equals( "Cancel" ))
//        {
//            holder.status.setTextColor(this.context.getColor( R.color.bg_mhendi_color ));
//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_active_status);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable, Color.RED);
//        }
//        if(holder.status.getText().equals( "Active" )){
//            holder.status.setTextColor(this.context.getColor( R.color.dark_green ));
//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.circle_active_status);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable, this.context.getColor( R.color.dark_green ));
//        }

           Glide.with( context ).load( model.getImage()).into( holder.image );



    }

    @Override
    public int getItemCount() {
        return complainsModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView name,number,status;
        LinearLayout view_detil;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.complains_image );
            name = itemView.findViewById( R.id.complains_title );
            number = itemView.findViewById( R.id.complains_idNumber );
            status = itemView.findViewById( R.id.complains_active_status );
            view_detil = itemView.findViewById( R.id.update_details_complains );



        }
    }
}
