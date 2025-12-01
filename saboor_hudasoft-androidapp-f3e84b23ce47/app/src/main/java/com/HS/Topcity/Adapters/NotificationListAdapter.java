package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.FullImages;
import com.HS.Topcity.ApiModels.Notifiation.NotificationRequest;
import com.HS.Topcity.ApiModels.Notifiation.NotificationResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.NotificationListModel;
import com.HS.Topcity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.viewHolder> {
    NotificationRequest notificationRequest = new NotificationRequest();
    Context context;
    OnItemClickListener listener;
    ArrayList<NotificationListModel> notificationListModels;

    ArrayList<String> image;
    int id;
    Activity activity;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    public interface OnItemClickListener {
        void onItemClick(NotificationListModel notificationListModels);
    }

    public NotificationListAdapter(Context context, ArrayList<NotificationListModel> notificationListModels,Activity activity){
        this.context = context;
        this.notificationListModels = notificationListModels;
        this.activity = activity;

    }

    public NotificationListAdapter(Context context, ArrayList<NotificationListModel> notificationListModels, OnItemClickListener listener){
        this.context = context;
        this.notificationListModels = notificationListModels;
        this.listener = listener;

    }
    @NonNull
    @Override
    public NotificationListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.notification_layout,parent,false );
        return new NotificationListAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationListAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.bind(notificationListModels.get(position), listener);

        NotificationListModel model = notificationListModels.get( position );

        holder.title.setText( model.getNotificationSubject() );
         holder.description.setText( model.getNotificationDetails() );
         if (model.getNotificationImage() != null){
             Glide.with( context ).load( model.getNotificationImage()).into( holder.image );
         }
         else {
             holder.image.setImageResource( R.mipmap.topcitysplashlogo );
         }


     //   Glide.with( context ).load( model.getImage()).into( holder.image );


        if(model.getDaysAgo() == 0){
            holder.day.setText("Today" );
        }
        else if(model.getDaysAgo() <30){
            holder.day.setText( String.valueOf( model.DaysAgo ) +" "+ "Days Ago" );
        }
        else if(model.getDaysAgo() >=30 && model.getDaysAgo() <60){
            holder.day.setText( "1 Month Ago" );
        }
        else if(model.getDaysAgo() >=60 && model.getDaysAgo() <90){
            holder.day.setText( "2 Month Ago" );
        }
        else if(model.getDaysAgo() >=90 && model.getDaysAgo() <120){
            holder.day.setText( "3 Month Ago" );
        }
        else if(model.getDaysAgo() >=120 && model.getDaysAgo() <150){
            holder.day.setText( "4 Month Ago" );
        }
        else if(model.getDaysAgo() >=150 && model.getDaysAgo() <180){
            holder.day.setText( "5 Month Ago" );
        }
        else if(model.getDaysAgo() >=180 && model.getDaysAgo() <210){
            holder.day.setText( "6 Month Ago" );
        }
        else if(model.getDaysAgo() >=210 && model.getDaysAgo() <240){
            holder.day.setText( "7 Month Ago" );
        }
        else if(model.getDaysAgo() >=240 && model.getDaysAgo() <270){
            holder.day.setText( "8 Month Ago" );
        }
        else if(model.getDaysAgo() >=300 && model.getDaysAgo() <330){
            holder.day.setText( "9 Month Ago" );
        }
        else if(model.getDaysAgo() >=330 && model.getDaysAgo() <360){
            holder.day.setText( "10 Month Ago" );
        }
        else if(model.getDaysAgo() >=390 && model.getDaysAgo() <420){
            holder.day.setText( "11 Month Ago" );
        }
        else if(model.getDaysAgo() >=420 && model.getDaysAgo() <830){
            holder.day.setText( "1 year" );
        }

        if(model.isNotificationView == false){
            holder.open.setBackgroundResource( R.color.light_blue );
        }
        else {
            holder.open.setBackgroundResource( R.color.white );

        }
        holder.open.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = model.getNotificationToId();
                api();
                Notification_list_api();
                holder.open.setBackgroundResource( R.color.white );



//                int previousItem = selectedItem;
//                selectedItem = position;
//                notifyItemChanged(previousItem);
//                notifyItemChanged(position);
//                notifyDataSetChanged();
          //      notifyDataSetChanged();

//                Intent a = new Intent(context , PropertyDetails.class );
//                context.startActivity( a );



            }
        } );


        holder.image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = model.getNotificationToId();
                api();
                Notification_list_api();
                holder.open.setBackgroundResource( R.color.white );
                image =  new ArrayList<>();
                if(model.NotificationImage != null){
                    image.add( model.NotificationImage );
                }
                else {
                    image.add( "https://www.topcity-1.com/wp-content/uploads/2020/01/logo.png");
                }

                Intent a  = new Intent(context, FullImages.class);
                a.putStringArrayListExtra( "Array",image);
                a.putExtra( "position",position );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( a);
            }
        } );





    }



    @Override
    public int getItemCount() {
        return notificationListModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
         ImageView image ;
        TextView title,description,day;
        LinearLayout open;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
           image = itemView.findViewById( R.id.notification_image );
            title = itemView.findViewById( R.id.title_notification );
            description = itemView.findViewById( R.id.notification_description );
            open = itemView.findViewById( R.id.view_notification);
            day = itemView.findViewById( R.id.time_notification );
//            itemView.setTag( itemView );
//            itemView.setOnClickListener(this); // bind the listener


        }
        public void bind(final NotificationListModel model, final OnItemClickListener listener) {
          title.setText( model.getNotificationSubject() );
            description.setText( model.getNotificationSubject() );
            image.setImageResource( R.mipmap.topcitysplashlogo );

            open.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });
        }

    }
    private void api(){
        String token = PreferenceData.getPrefUserToken(context);
         ApiInterface apiInterface = ApiUtils.postSignUpService();
         notificationRequest.setNotificationToId(id  );

        Call<NotificationResponse> signup = apiInterface.notificationResponse(token,notificationRequest);
        signup.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                NotificationResponse user = response.body();
                if(user != null)
                {
                    if (user.status == true){

                       // Notification_list_api();
                     //   check_noti = "check" ;
//                        Intent a = new Intent(context, Notification.class );
//                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity( a );
//                        activity.finish();


//                        final Dialog dailoginfo = new Dialog( activity.getApplicationContext());
//                        dailoginfo.requestWindowFeature( Window.FEATURE_NO_TITLE);
//                        dailoginfo.getWindow().setGravity( Gravity.BOTTOM);
//                        dailoginfo.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
//                        dailoginfo.setContentView(R.layout.notification_dailog_layout);
//                        dailoginfo.setCanceledOnTouchOutside(false);
//                        dailoginfo.getWindow().setLayout( WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                     //   dailoginfo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id

                      //  notifyDataSetChanged();
                    }
                }

            }
            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText( context, "notification not work", Toast.LENGTH_SHORT ).show();
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });




    }
    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(context);
        ApiInterface apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {


                    if (user.notificationsList != null) {

                            notication_count  = String.valueOf(user.NotificationBadgeCount  ) ;
                    }

                } else {

                    System.out.println( "Failed" );
                }

            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {

                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
}

