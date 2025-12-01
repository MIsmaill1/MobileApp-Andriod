package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.NewsAnnounceDetail.viewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullScreenImagesActivity;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class NewsAnnoceDetailPageAdapter extends RecyclerView.Adapter<NewsAnnoceDetailPageAdapter.viewHolder> {
    Context context;
    ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels;
ArrayList image;
    Activity activity;


    public NewsAnnoceDetailPageAdapter(Context context, ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels,Activity activity, int newsannoce){
        this.context = context;
        this.newsAndAnnouncementModels = newsAndAnnouncementModels;
        this.activity = activity;

    }
    @NonNull
    @Override
    public NewsAnnoceDetailPageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.news_annoce_detail_page_layout,parent,false );
        return new NewsAnnoceDetailPageAdapter.viewHolder( v );
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType( position );
    }
    @Override
    public void onBindViewHolder(@NonNull NewsAnnoceDetailPageAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {


        NewsAnnouncementModel model = newsAndAnnouncementModels.get( position );


        if (position == 0){
            holder.perivous.setVisibility( View.GONE );
        }
        else {
            holder.perivous.setVisibility( View.VISIBLE );
        }
        int count = newsAndAnnouncementModels.size();
        int total = count - 1;
        if (count != 0){
            if( total == position){
                holder.next.setVisibility( View.GONE );
                holder.perivous.setVisibility( View.VISIBLE );
                if(position == 0){
                    holder.perivous.setVisibility( View.GONE );
                }
            }
            else {
                holder.next.setVisibility( View.VISIBLE );
            }
        }



        holder.item_time.setText( model.getTimeOfNews() );
        holder.item_date.setText( model.getDateofNews() );
        holder.title.setText( model.getNewsName() );
        holder.item_description.setText( model.getDescriptions() );
        holder.notes.setText( model.getNotes() );

        try {
            Glide.with( context ).load( model.getImage()).into( holder.image );
        }
        catch (Exception e){
            System.out.println(e);
        }



        holder.image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image =  new ArrayList<>();
                image.add(newsAndAnnouncementModels.get( position ).getImage()  );
                Intent a  = new Intent(context, FullScreenImagesActivity.class);
                a.putStringArrayListExtra( "Array",image);
                a.putExtra( "position",0 );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a);
            }
        } );
        holder.next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            int pos =  holder.getAdapterPosition() + 1;
              // getItemViewType(pos  );
                viewPager.setCurrentItem(pos,false);
               // recyclerView.smoothScrollToPosition( pos );
               notifyItemChanged( holder.getAdapterPosition()  );
               // notifyDataSetChanged();
            }
        } );
        holder.perivous.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =  holder.getAdapterPosition() - 1;
                // getItemViewType(pos  );
                viewPager.setCurrentItem(pos,false);
            //    recyclerView.smoothScrollToPosition( pos );
                notifyItemChanged( holder.getAdapterPosition()  );
            }
        } );


        holder.back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Now call this method from             anywhere activity.onBackPressed()

                activity.onBackPressed();
            }
        } );

        getItemViewType( 3 );

    }




    @Override
    public int getItemCount() {
        return newsAndAnnouncementModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView image;
        ImageView back;
        TextView item_date,item_time,item_description,notes,next,perivous,title;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.details_image );
        item_date = itemView.findViewById( R.id.details_date);
            title = itemView.findViewById( R.id.details_title);
        item_time = itemView.findViewById( R.id.details_time );
        item_description = itemView.findViewById( R.id.details_description );
        next = itemView.findViewById( R.id.details_next );
        perivous = itemView.findViewById( R.id.details_perrvious );
        back = itemView.findViewById( R.id.backtodashboard );
        notes = itemView.findViewById( R.id.details_note );


        }
    }

}
