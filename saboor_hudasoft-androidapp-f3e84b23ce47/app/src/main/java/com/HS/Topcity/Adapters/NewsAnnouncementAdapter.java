package com.HS.Topcity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.NewAnnouncementList;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAnnouncementAdapter extends RecyclerView.Adapter<NewsAnnouncementAdapter.viewHolder> {
    Context context;
    ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels;



    public NewsAnnouncementAdapter(Context context, ArrayList<NewsAnnouncementModel> newsAndAnnouncementModels, int newsannoce){
        this.context = context;
        this.newsAndAnnouncementModels = newsAndAnnouncementModels;

    }
    @NonNull
    @Override
    public NewsAnnouncementAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.news_annocement_layout,parent,false );
        return new NewsAnnouncementAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAnnouncementAdapter.viewHolder holder, int position) {


        NewsAnnouncementModel model = newsAndAnnouncementModels.get( position );

//        holder.month.setText( model.getDateofNews() );
//        holder.date.setText( model.getDateofNews());

        String dateFromDB = model.getDateofNews();

        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = parser.parse(dateFromDB);

//            String year = (String) DateFormat.format("yyyy", date); // 2013
            String monthString  = (String) DateFormat.format("MMM",  date); // Jun
            String day          = (String) DateFormat.format("dd",   date); // 20
            holder.date.setText(day);
            holder.month.setText( monthString );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.title.setText( model.getNewsName() );
        holder.descrpition.setText( model.getDescriptions() );
        Glide.with( context ).load( model.getImage()).into( holder.image );

        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new NewsAndAnnocementList();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();


                Intent a  = new Intent(context, NewAnnouncementList.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a );
//                Intent back = new Intent(context, Dashboard.class);
//                back.putExtra( "newsAndAnnoucelist","newsAndAnnoucelist" );
//                context.startActivity( back );

            }
        } );

//        for (int i = 1 ; i < newsAndAnnouncementModels.size() ; i++)
//        {
//            if (position == i){
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                        FrameLayout.LayoutParams.MATCH_PARENT,
//                        FrameLayout.LayoutParams.MATCH_PARENT
//                );
//                params.setMarginStart( -10 );
//                holder.open.setLayoutParams( params);
//            }
//        }


    }

    @Override
    public int getItemCount() {
        if(newsAndAnnouncementModels.size() >5)
        {
            return 5;
        }
        else {
            return newsAndAnnouncementModels.size();
        }

    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title,date,month,descrpition;
        FrameLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.news_and_announcements_image );
            title = itemView.findViewById( R.id.news_annoce_title );
            descrpition = itemView.findViewById( R.id.news_annoce_descrip );
            date = itemView.findViewById( R.id.news_annoce_date );
            month = itemView.findViewById( R.id.news_annoce_month );
            open = itemView.findViewById( R.id.news_and_announcements_open );


        }
    }
}
