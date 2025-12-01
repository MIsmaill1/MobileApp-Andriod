package com.HS.Topcity.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.DashboardCountModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

public class DashboardCountAdapter extends RecyclerView.Adapter<DashboardCountAdapter.viewHolder> {

    Context context;
    ArrayList<DashboardCountModel> dashboardCountModels;



    public DashboardCountAdapter(Context context, ArrayList<DashboardCountModel> dashboardCountModels, int newsannoce){
        this.context = context;
        this.dashboardCountModels = dashboardCountModels;

    }
    @NonNull
    @Override
    public DashboardCountAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.dashboard_count_layout,parent,false );
        return new DashboardCountAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardCountAdapter.viewHolder holder, int position) {


        DashboardCountModel model = dashboardCountModels.get( position );


        holder.title.setText( model.getTitle() );
        holder.num.setText( String.valueOf( model.getCount() ) );
        holder.num.setTextColor( Color.parseColor( "#" + model.colorCode  )   );



    }

    @Override
    public int getItemCount() {
        return dashboardCountModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView title,num;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            num = itemView.findViewById( R.id.active_comp );
            title = itemView.findViewById( R.id.status );

        }
    }
}
