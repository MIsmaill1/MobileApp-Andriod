package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.NewsAnnounceDetail;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAnnouncementAllListAdapter extends RecyclerView.Adapter<NewsAnnouncementAllListAdapter.viewHolder> {
    Context context;
    ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels;



    public NewsAnnouncementAllListAdapter(Context context, ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels, int newsannoce){
        this.context = context;
        this.newsAndAnnouncementModels = newsAndAnnouncementModels;

    }
    @NonNull
    @Override
    public NewsAnnouncementAllListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.news_annocement_list_layout,parent,false );
        return new NewsAnnouncementAllListAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAnnouncementAllListAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {


        NewsAnnouncementModel model = newsAndAnnouncementModels.get( position );

//        holder.month.setText( model.getDateofNews() );
//        holder.date.setText( model.getDateofNews());
        String dateFromDB = model.getDateofNews();

//        try {
//            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
//            Date date = parser.parse(dateFromDB);
//
//            String year = (String) DateFormat.format("yyyy", date); // 2013
//            String monthString  = (String) DateFormat.format("MMMM",  date); // Jun
//            String day = (String) DateFormat.format("dd",   date); // 20
//            String hour = (String) DateFormat.format( "hh", date );
//            String minutes = (String) DateFormat.format( "hh", date );
//            String amTopm = (String) DateFormat.format( "a", date );
//
//            holder.date.setText(day+" "+monthString+" "+year);
//            holder.time.setText( hour+":"+minutes+" " + amTopm );
//
//            holder.itemView.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        } );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        holder.time.setText( model.getTimeOfNews() );
        holder.date.setText( model.getDateofNews() );
        holder.title.setText( model.getNewsName() );
        holder.descrpition.setText( model.getDescriptions() );
        Glide.with( context ).load( model.getImage()).into( holder.image );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.time.getText().toString();
                Intent a = new Intent(context, NewsAnnounceDetail.class );
                a.putExtra( "title" ,model.getNewsName());
                a.putExtra( "descrpition",model.getDescriptions() );
                a.putExtra( "date", holder.date.getText().toString() );
                a.putExtra( "time",holder.time.getText().toString() );
                a.putExtra( "notes",model.getNotes() );
                a.putExtra( "image",model.getImage() );
                a.putExtra( "position",position );
                a.putParcelableArrayListExtra( "announce Array",newsAndAnnouncementModels );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new NewsAndAnnocementList();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();

            }
        } );



    }

    @Override
    public int getItemCount() {
        return newsAndAnnouncementModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title,date,time,descrpition;
        FrameLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.news_annoce_list_image);
            title = itemView.findViewById( R.id.news_annoce_list_title);
            descrpition = itemView.findViewById( R.id.news_annoce_list_descrip );
            date = itemView.findViewById( R.id.news_annoce_list_date );
            time = itemView.findViewById( R.id.news_annoce_list_time );
            open = itemView.findViewById( R.id.news_and_announcements_opendetail );


        }
    }
}
