package com.HS.Topcity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.CommunityServiceModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

public class DownFormAdapter  extends RecyclerView.Adapter<DownFormAdapter.viewHolder>{
    Context context;
    ArrayList<CommunityServiceModel> communityServiceModels;



    public DownFormAdapter(Context context, ArrayList<CommunityServiceModel> communityServiceModels, int newsannoce){
        this.context = context;
        this.communityServiceModels = communityServiceModels;

    }
    @NonNull
    @Override
    public DownFormAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.community_service_item_layout,parent,false );
        return new DownFormAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull DownFormAdapter.viewHolder holder, int position) {


        CommunityServiceModel model = communityServiceModels.get( position );


        holder.title.setText( model.getName() );

        //Glide.with( context ).load( model.getImage()).into( holder.image );
        holder.image.setImageResource( model.getImages() );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(model.getName().equals( "Water" )){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.topcity-1.com/wp-content/uploads/2022/03/Application-For-Water-Connection.pdf"));
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
                else if(model.getName().equals( "Electricity" )){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.topcity-1.com/wp-content/uploads/2022/03/ELECTRIC-FORM-2.pdf"));
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
//
//                    Toast.makeText( context, "This Form Currently Not Available", Toast.LENGTH_SHORT ).show();
                }
                else if(model.getName().equals( "Possession" )){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.topcity-1.com/wp-content/uploads/2022/03/A-PROJECT-OF-DYNAST-ASSOCIATES-ISALAMABAD-1.pdf"));
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
                else if(model.getName().equals( "Excavation" )){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.topcity-1.com/wp-content/uploads/2022/03/MACHINERY-WORK-FORM-1.pdf"));
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

