package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.MangementDashboard.complaintid_Dash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.ManageDashboardModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ManageDashboardItemAdapter extends RecyclerView.Adapter<ManageDashboardItemAdapter.viewHolder> {
    Context context;
    ArrayList<ManageDashboardModel> manageDashboardModels;
    boolean check;
    boolean statusVisible;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ManageDashboardModel item);
    }

    public ManageDashboardItemAdapter(Context context, ArrayList<ManageDashboardModel> manageDashboardModels, int propertiesLayout, OnItemClickListener listener){
        this.context = context;
        this.manageDashboardModels = manageDashboardModels;
        this.listener = listener;
        selectedItem = 0;
    }
    @NonNull
    @Override
    public ManageDashboardItemAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.manage_dashboard_item_layout,parent,false );
        return new ManageDashboardItemAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ManageDashboardItemAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {


       ManageDashboardModel model = manageDashboardModels.get( position );


//        holder.name.setText(model.getText()  );
//
//        Glide.with( context ).load( model.getImage_unactive()).into( holder.checkimage_unActive);
//        Glide.with( context ).load( model.getImage_active()).into( holder.image_Active);
//
        holder.open.setVisibility( View.VISIBLE );
//        if (selectedItem == position) {
//            holder.image_Active.setVisibility( View.VISIBLE );
//            holder.checkimage_unActive.setVisibility( View.GONE );
//            holder.name.setVisibility( View.VISIBLE );
//            complaintid_Dash =  model.getId();
//            holder.open.setBackgroundColor( context.getColor(R.color.bg_light_blue_color) );
//        }
//        else {
//            holder.image_Active.setVisibility( View.GONE );
//            holder.checkimage_unActive.setVisibility( View.VISIBLE );
//            holder.name.setVisibility( View.GONE );
//            holder.open.setBackgroundColor( context.getColor(R.color.bg_gray_color) );
//        }
//
//        holder.open.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                int previousItem = selectedItem;
//                selectedItem = position;
//                complaintid_Dash =  model.getId();
//                notifyItemChanged(previousItem);
//                notifyItemChanged(position);
//
//
//            }
//        } );
        holder.bind(manageDashboardModels.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return manageDashboardModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image_Active,checkimage_unActive ;
        TextView name;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image_Active = itemView.findViewById( R.id.active_item );
            checkimage_unActive = itemView.findViewById( R.id.unActive_item );
            name = itemView.findViewById( R.id.itemname );
            open = itemView.findViewById( R.id.item_box_frame);

        }
        public void bind(final ManageDashboardModel item, final OnItemClickListener listener) {
           name.setText(item.getText()  );

            Glide.with( context ).load( item.getImage_unactive()).into( checkimage_unActive);
            Glide.with( context ).load( item.getImage_active()).into( image_Active);

            if (selectedItem == getAdapterPosition()) {
             image_Active.setVisibility( View.VISIBLE );
               checkimage_unActive.setVisibility( View.GONE );
               name.setVisibility( View.VISIBLE );
                complaintid_Dash =  item.getId();
               open.setBackgroundColor( context.getColor(R.color.bg_light_blue_color) );
            }
            else {
               image_Active.setVisibility( View.GONE );
                checkimage_unActive.setVisibility( View.VISIBLE );
              name.setVisibility( View.GONE );
                open.setBackgroundColor( context.getColor(R.color.bg_gray_color) );
            }
            open.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int previousItem = selectedItem;
                    selectedItem = getAdapterPosition();
                    complaintid_Dash =  item.getId();
                    notifyItemChanged(previousItem);
                    notifyItemChanged(getAdapterPosition());
                    listener.onItemClick(item);
                }
            });
        }
    }
}
