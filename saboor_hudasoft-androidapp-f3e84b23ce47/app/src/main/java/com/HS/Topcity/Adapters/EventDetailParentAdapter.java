package com.HS.Topcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.EventDetailsModel;
import com.HS.Topcity.R;

import java.util.List;

public class EventDetailParentAdapter extends RecyclerView.Adapter<EventDetailParentAdapter.viewHolder>{
    Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<EventDetailsModel> eventDetailsModels;
   // ArrayList<EventDetailsImageModel> eventDetailsImageModels;
    public EventDetailParentAdapter(Context context, List<EventDetailsModel> eventDetailsModels, int appoinment){
        this.context = context;
        this.eventDetailsModels = eventDetailsModels;
    }

    @NonNull
    @Override
    public EventDetailParentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.event_detail_parent_layout,parent,false );
        return new EventDetailParentAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull EventDetailParentAdapter.viewHolder holder, int position) {


        EventDetailsModel model = eventDetailsModels.get( position );

        holder.name.setText( model.getEventName() );
        holder.description.setText( model.getDescriptions() );
        holder.time.setText( model.getEventTime() );
        holder.date.setText( model.getEventDate() );


        LinearLayoutManager layoutManager = new LinearLayoutManager( holder.recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false );
        layoutManager.setInitialPrefetchItemCount( model.getImages().size() );



       EvenDetailsAdapter pendingAppoinitmentAdapter = new EvenDetailsAdapter( context,model.getImages(),R.layout.event_layout );
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(pendingAppoinitmentAdapter);
        holder.recyclerView.setRecycledViewPool(viewPool);





    }

    @Override
    public int getItemCount() {
        return eventDetailsModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        TextView name,description,date,time;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            recyclerView = itemView.findViewById( R.id.event_Image_list );
            name = itemView.findViewById( R.id.name_eventdetails );
            description = itemView.findViewById( R.id.description_eventdetails );
            date = itemView.findViewById( R.id.event_date );
            time = itemView.findViewById( R.id.event_time );


        }
    }
}
