package com.HS.Topcity.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.SubFeaturesModels;
import com.HS.Topcity.R;

import java.util.List;

public class SubFeaturesParentAdapter extends RecyclerView.Adapter<SubFeaturesParentAdapter.viewHolder>{

    Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<SubFeaturesModels> subFeaturesModels;
    // ArrayList<EventDetailsImageModel> eventDetailsImageModels;
    public SubFeaturesParentAdapter(Context context, List<SubFeaturesModels> subFeaturesModels, int appoinment){
        this.context = context;
        this.subFeaturesModels = subFeaturesModels;
    }

    @NonNull
    @Override
    public SubFeaturesParentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.sub_feature_parent_layout,parent,false );
        return new SubFeaturesParentAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull SubFeaturesParentAdapter.viewHolder holder, int position) {


        SubFeaturesModels model = subFeaturesModels.get( position );

        holder.name.setText( model.getName() );
        holder.description.setText( model.getDescription() );


        LinearLayoutManager layoutManager = new LinearLayoutManager( holder.recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false );
        layoutManager.setInitialPrefetchItemCount( model.getImage().size() );



        SubFeatureChildAdapter subFeatureChildAdapter = new SubFeatureChildAdapter( context,model.getImage(),R.layout.sub_feature_image_layout );
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(subFeatureChildAdapter);
        holder.recyclerView.setRecycledViewPool(viewPool);
        if(context !=null){
            int chcek_val = subFeatureChildAdapter.getItemCount();
            if (chcek_val > 1){
                initBottom( subFeatureChildAdapter.getItemCount(),holder.recyclerView ,holder);

            }


        }






    }
    private void transitionDots(LinearLayout li, int lastIndex, int totalitem) {

        for (int i=0;i<li.getChildCount();i++){
            if(li.getChildAt( i ) instanceof TextView){
                li.getChildAt( i ).setBackgroundResource( R.drawable.indictor0 );
            }
        }
        // set dot All position items
        for(int j=0; j<totalitem;j++){
            if(lastIndex>=0){
                li.getChildAt( lastIndex ).setBackgroundResource( R.drawable.indicator1 );
                lastIndex--;
            }
        }
    }
    private  LinearLayout getBottomDot(int count,SubFeaturesParentAdapter.viewHolder holder){
        LinearLayout li = new LinearLayout( context );
        li.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT ));
        li.setOrientation( LinearLayout.HORIZONTAL );
        li.setGravity( Gravity.RIGHT );

        for (int i = 0;i<count;i++)
        {
            TextView tv = new TextView( context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 35,15 );
            params.setMargins( 7,2,3,2 );
            tv.setLayoutParams( params );
            tv.setBackground( context.getResources().getDrawable(R.drawable.indictor0 ) );
            li.addView( tv );
        }
       holder.list.addView(   li );
        return li;
    }
    private void initBottom(int itemCount ,RecyclerView v,SubFeaturesParentAdapter.viewHolder holder){
        LinearLayout li = getBottomDot(itemCount,holder);
        v.setOnScrollChangeListener( new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                int first = ((LinearLayoutManager)v.getLayoutManager()).findFirstVisibleItemPosition();
                int last = ((LinearLayoutManager)v.getLayoutManager()).findLastVisibleItemPosition();
                int total = 0;
                for(int j=first;j<=last;j++){
                    total++;
                }
                transitionDots(li,last,total);

            }

        } );
    }
    @Override
    public int getItemCount() {
        return subFeaturesModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        TextView name,description;
        LinearLayout list;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            recyclerView = itemView.findViewById( R.id.subFeature_Image_list );
            name = itemView.findViewById( R.id.subFeature_name );
            description = itemView.findViewById( R.id.subFeature_descrip );
            list = itemView.findViewById( R.id.list );


        }

    }
}
