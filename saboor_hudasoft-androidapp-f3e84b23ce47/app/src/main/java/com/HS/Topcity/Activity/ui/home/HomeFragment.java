package com.HS.Topcity.Activity.ui.home;

import static android.content.ContentValues.TAG;
import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.check_menu_open;
import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.UserProfile.name_get;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.HS.Topcity.Activity.EventDetails;
import com.HS.Topcity.Activity.MangementDashboard;
import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.Signup;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.Adapters.EventApplandingAdapter;
import com.HS.Topcity.Adapters.EventimageAdapter;
import com.HS.Topcity.Adapters.NewsAnnouncementAdapter;
import com.HS.Topcity.Adapters.UserComplainAdapter;
import com.HS.Topcity.Adapters.UserPropertyAdapter;
import com.HS.Topcity.ApiModels.Complains.UserComplainsResponse;
import com.HS.Topcity.ApiModels.Event.EventResponse;
import com.HS.Topcity.ApiModels.EventDetails.EventDetailsResponse;
import com.HS.Topcity.ApiModels.GuestNotification.GuestNotificationRequest;
import com.HS.Topcity.ApiModels.GuestNotification.GuestNotificationResponse;
import com.HS.Topcity.ApiModels.NewsAndAnnouncements.NewsAndAnnouncementsResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.UpdateFCMToken.UpdateFcmTokenRequest;
import com.HS.Topcity.ApiModels.UpdateFCMToken.UpdateFcmTokenResponse;
import com.HS.Topcity.ApiModels.UserProperties.UserPropertiesResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.CustomClasses.LinePagerIndicatorDecoration;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplainsModel;
import com.HS.Topcity.Models.EventDetailsImageModel;
import com.HS.Topcity.Models.EventDetailsModel;
import com.HS.Topcity.Models.EventImagesModel;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.Models.NotificationListModel;
import com.HS.Topcity.Models.PropertiesModel;
import com.HS.Topcity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;
import com.yarolegovich.slidingrootnav.transform.RootTransformation;

import java.util.ArrayList;
import java.util.Calendar;

import me.huseyinozer.TooltipIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;


public class HomeFragment extends Fragment {

    ApiInterface apiInterface;
    ArrayList<NewsAnnouncementModel> newsAnnouncementModels;
    ArrayList<PropertiesModel> propertiesModels;
    ArrayList<ComplainsModel> complainsModels;
    ArrayList<EventImagesModel> eventImagesModels;
    ArrayList<EventDetailsImageModel> eventDetailsImageModels;
    RecyclerView newsaAnnoceList,userpropertyList,complainsList,eventImageslist;
    TextView status,Username,view_more;
    ImageView userSetting,notification,menu,close,mange_dash;
    LinearLayout signup,no_pro_txt,no_comp_txt,no_news_txt,no_event_txt;
    LinearLayoutManager linearLayoutManager2;
    ArrayList<NotificationListModel> notificationListModels;
    ArrayList<EventDetailsModel> eventDetails;
    LinearLayout pro_comp_hide;
    ScrollingPagerIndicator recyclerIndicator;
    UpdateFcmTokenRequest updateFcmTokenRequest = new UpdateFcmTokenRequest();
    GuestNotificationRequest guestNotificationRequest = new GuestNotificationRequest();
    public  static String notication_count;
    TextView Noti_count;
    LinearLayout Noti_bg;
    ViewPager viewPager;
    TooltipIndicator tooltipIndicator ;

    WormDotsIndicator wormDotsIndicator;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout list,list1;

    int slide_timer = 5000;
//    ActivityMainBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate( R.layout.fragment_home, container, false );
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//        binding = ActivityMainBinding.inflate( getLayoutInflater() );
//        RootView = binding.getRoot();



        // ids
      newsaAnnoceList = RootView.findViewById( R.id.news_and_announcements_list );
      userpropertyList = RootView.findViewById( R.id.my_property_list );
      complainsList = RootView.findViewById( R.id.complains_list );
        eventImageslist = RootView.findViewById( R.id.event_list );
      status = RootView.findViewById( R.id.morning_status );
      Username = RootView.findViewById( R.id.user_name );
      signup = RootView.findViewById( R.id.signup_btn_home );
      userSetting =  RootView.findViewById( R.id.user_icon );
      view_more = RootView.findViewById( R.id.event_viewmore );
        notification = RootView.findViewById( R.id.notification_home );
        no_pro_txt = RootView.findViewById( R.id.no_property_txt );
        no_comp_txt = RootView.findViewById( R.id.no_complaint_text );
        no_news_txt = RootView.findViewById( R.id.no_news_txt );
        no_event_txt = RootView.findViewById( R.id.no_event_txt );
        pro_comp_hide = RootView.findViewById( R.id.property_and_complaint );
       // menu = RootView.findViewById( R.id.nav_menu_bar );
        close = RootView.findViewById( R.id.nav_close );
        Noti_count = RootView.findViewById( R.id.notification_Count );
        Noti_bg = RootView.findViewById( R.id.notification_Count_bg );

//         recyclerIndicator = RootView.findViewById(R.id.indicator);
//        tooltipIndicator= RootView.findViewById(R.id.tooltip_indicator);
//        viewPager= RootView.findViewById(R.id.viewPage);
        swipeRefreshLayout = RootView.findViewById(R.id.swipeRefreshLayout_home);
        list = RootView.findViewById(R.id.List);
        list1 = RootView.findViewById(R.id.list1);
        list1 = RootView.findViewById(R.id.list1);
        mange_dash = RootView.findViewById(R.id.mangeDashboard);

       nav_bar();
        Noti_bg.setVisibility( View.GONE );
    // update token
        update_fcm_token_api();
    // property api list
        property_App_landing_api();
        // new anncoement list
        news_annoce_App_landing_api();
        // complaint list
        complaint_app_landing_api();
        // event_app_landing_Api
        event_app_landing_Api();
    // good mornig / good night functionaLity
        good_morning_status_chaneged();
        // mangement item funtionality
        mangement_item();
        // if user guest so work this function
        guest();
        // change user name on ui
        username_changed_functionality();
        // action perform
        userProfile_Notification_viewMore_actions();

        // refresh Screen
        SwipeRefreshLayout();







        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(getContext(), Signup.class );
                startActivity( a );
            }
        } );

        mange_dash.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(getContext(), MangementDashboard.class );
                startActivity( a );
            }
        } );

     //   }

        return RootView;
    }

    private void SwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(guest != null || PreferenceData.getIsMangement( getContext() ) == true){
                    news_annoce_App_landing_api();
                    good_morning_status_chaneged();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else {
                    complaint_app_landing_api();
                  //  property_App_landing_api();
                    news_annoce_App_landing_api();
                    good_morning_status_chaneged();
                    update_fcm_token_api();
                    Notification_list_api();
                    swipeRefreshLayout.setRefreshing(false);
                }


            }
        } );

    }
    private void mangement_item(){
        if (PreferenceData.getIsMangement( getContext() ) == true){
            mange_dash.setVisibility( View.VISIBLE );
            pro_comp_hide.setVisibility( View.GONE );

        }
        else {
            mange_dash.setVisibility( View.GONE );
            pro_comp_hide.setVisibility( View.VISIBLE );
        }
    }
    private void property_App_landing_api(){
        // property api intregration
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //   progress.show();
        Call<UserPropertiesResponse> userPropertiesResponseCall = apiInterface.userPropertiesResponse(token);
        userPropertiesResponseCall.enqueue(new Callback<UserPropertiesResponse>() {
            @Override
            public void onResponse(Call<UserPropertiesResponse> call, Response<UserPropertiesResponse> response) {
                UserPropertiesResponse user = response.body();


                if (user != null) {

                    propertiesModels = new ArrayList<>();
                    if(  user.properties == null)
                    {
                        no_pro_txt.setVisibility( View.VISIBLE );
                        progress.dismiss();
                    }
                    else{
                        if (user.properties != null ||user.properties.size() !=  0 ) {
                            no_pro_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.properties.size(); i++) {
                                PropertiesModel model = new PropertiesModel();
                                model.setId( user.properties.get( i ).getId() );
                                model.setAddress( user.properties.get( i ).getAddress() );
                                model.setImage( user.properties.get( i ).getImage() );
                                model.setOwner( user.properties.get( i ).getOwner() );
                                model.setDetail( user.properties.get( i ).getDetail() );
                                propertiesModels.add( model );
                            }

                            UserPropertyAdapter userPropertyAdapter = new UserPropertyAdapter( getContext(), propertiesModels,R.layout.user_property_layout );

                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getContext() , LinearLayoutManager.HORIZONTAL,false );
                            userpropertyList.setLayoutManager( linearLayoutManager2 );
                            userpropertyList.setNestedScrollingEnabled(true);
                            // add pager behavior
                            PagerSnapHelper snapHelper = new PagerSnapHelper();
                            snapHelper.attachToRecyclerView(userpropertyList);

                            // pager indicator
                            userpropertyList.addItemDecoration(new LinePagerIndicatorDecoration());
                            userpropertyList.setAdapter( userPropertyAdapter );
                            if(getContext() !=null){
                                int check_val = userPropertyAdapter.getItemCount();
                                if(check_val > 1)
                                {
                                    initBottom1( userPropertyAdapter.getItemCount(),userpropertyList );

                                }


                            }

                        }
                        else {
                            no_pro_txt.setVisibility( View.VISIBLE );
                            System.out.println( "Failed" );
                        }
                    }
                    progress.dismiss();
                }
                else {
                    no_pro_txt.setVisibility( View.VISIBLE );
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserPropertiesResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }
    private void news_annoce_App_landing_api(){
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //   progress.show();

        // news Announcement api intregration

        Call<NewsAndAnnouncementsResponse> newsAndAnnouncementsResponseCall = apiInterface.newsAndAnnouncementResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<NewsAndAnnouncementsResponse>() {
            @Override
            public void onResponse(Call<NewsAndAnnouncementsResponse> call, Response<NewsAndAnnouncementsResponse> response) {
                NewsAndAnnouncementsResponse user = response.body();


                if (user != null) {

                    newsAnnouncementModels = new ArrayList<>();
                    if (user.newsAndAnnouncementModel == null || user.newsAndAnnouncementModel.size() == 0)
                    {
                        no_news_txt.setVisibility( View.VISIBLE );
                        progress.dismiss();
                    }
                    else {
                        if (user.newsAndAnnouncementModel.size() > 0 || user.newsAndAnnouncementModel != null) {
                            no_news_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.newsAndAnnouncementModel.size(); i++) {
                                NewsAnnouncementModel model = new NewsAnnouncementModel();
                                model.setDateofNews( user.newsAndAnnouncementModel.get( i ).DateofNews );
                                model.setTimeOfNews( user.newsAndAnnouncementModel.get( i ).TimeOfNews );
                                model.setNewsName( user.newsAndAnnouncementModel.get( i ).newsName );
                                model.setDescriptions( user.newsAndAnnouncementModel.get( i ).Descriptions );
                                model.setNotes( user.newsAndAnnouncementModel.get( i ).notes );
                                model.setImage( user.newsAndAnnouncementModel.get( i ).Image );
                                model.setId( user.newsAndAnnouncementModel.get( i ).id );
                                newsAnnouncementModels.add( model );
                            }

                            NewsAnnouncementAdapter newsAnnouncementAdapter = new NewsAnnouncementAdapter( getContext(), newsAnnouncementModels,R.layout.news_annocement_layout );

                            linearLayoutManager2 = new LinearLayoutManager( getContext() , LinearLayoutManager.HORIZONTAL,false );
                            newsaAnnoceList.setLayoutManager( linearLayoutManager2 );
                            newsaAnnoceList.setNestedScrollingEnabled(true);

                            newsaAnnoceList.setAdapter( newsAnnouncementAdapter );
                            if(getContext() !=null){
                                int chcek_val = newsAnnouncementAdapter.getItemCount();
                                if (chcek_val > 1){
                                    initBottom( newsAnnouncementAdapter.getItemCount(),newsaAnnoceList );

                                }


                            }



//                            MyCustomPagerAdapter myCustomPagerAdapter = new MyCustomPagerAdapter( getContext(),newsAnnouncementModels );
//                            viewPager.setAdapter(myCustomPagerAdapter);
//                            tooltipIndicator.setupViewPager(viewPager);




//                           recyclerIndicator.setSelectedDotColor(getContext().getResources().getColor( R.color.bg_light_blue_color ));
//                         //   recyclerIndicator.setSelectedDotColor(R.drawable.recangular );
//                            recyclerIndicator.setDotColor(getContext().getResources().getColor(R.color.color1 ));
                         //  recyclerIndicator.setBackground( getContext().getResources().getDrawable(R.drawable.dot_select ));
                          //  recyclerIndicator.setSelectedDotColor( R.drawable.dot_select );


                            //    recyclerIndicator.setBackground(getContext().getResources().getDrawable( R.drawable.recangular ));
                          //  recyclerIndicator.attachToRecyclerView(newsaAnnoceList);
                           if(check_menu_open != true && newsAnnouncementModels != null && newsAnnouncementAdapter.getItemCount() > 1){
                               final int speedScroll = 5000;
                               final Handler handler = new Handler();
                               final Runnable runnable = new Runnable() {
                                   int count = 0;
                                   boolean flag = true;
                                   @Override
                                   public void run() {
                                       if(count < newsAnnouncementAdapter.getItemCount()){
                                           if(count==newsAnnouncementAdapter.getItemCount()-1){
                                               flag = false;
                                           }else if(count == 0){
                                               flag = true;
                                           }
                                           if(flag) count++;
                                           else count--;

                                           newsaAnnoceList.smoothScrollToPosition(count);
                                           handler.postDelayed(this,slide_timer);
                                       }
                                   }
                               };
                               handler.postDelayed(runnable,slide_timer);
                           }

                        }
                        else {
                            no_news_txt.setVisibility( View.VISIBLE );
                            progress.dismiss();
                        }
                    }


                    progress.dismiss();
                } else {
                    no_news_txt.setVisibility( View.VISIBLE );
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<NewsAndAnnouncementsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

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
    private LinearLayout getBottomDot(int count){
        LinearLayout li = new LinearLayout( getContext() );
        li.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT ));
        li.setOrientation( LinearLayout.HORIZONTAL );
        li.setGravity( Gravity.RIGHT );

        for (int i = 0;i<count;i++)
        {
            TextView tv = new TextView( getContext() );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 35,15 );
            params.setMargins( 7,2,3,2 );
            tv.setLayoutParams( params );
            tv.setBackground( getActivity().getResources().getDrawable(R.drawable.indictor0 ) );
            li.addView( tv );
        }
        list.addView(   li );
        return li;
    }
    private void initBottom(int itemCount ,RecyclerView v){
        LinearLayout li = getBottomDot(itemCount);
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
    private void initBottom1(int itemCount ,RecyclerView v){
        LinearLayout li = getBottomDot1(itemCount);
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
    private LinearLayout getBottomDot1(int count){
        LinearLayout li = new LinearLayout( getContext() );
        li.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT ));
        li.setOrientation( LinearLayout.HORIZONTAL );
        li.setGravity( Gravity.RIGHT );

        for (int i = 0;i<count;i++)
        {
            TextView tv = new TextView( getContext() );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 35,15 );
            params.setMargins( 7,2,3,2 );
            tv.setLayoutParams( params );
            tv.setBackground( getActivity().getResources().getDrawable(R.drawable.indictor0 ) );
            li.addView( tv );
        }
        list1.addView(   li );
        return li;
    }
    private void complaint_app_landing_api(){
        // complains api intregration
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

        apiInterface = ApiUtils.postSignUpService();
        Call<UserComplainsResponse> userComplainsResponseCall = apiInterface.userComplainsResponse(token);
        userComplainsResponseCall.enqueue(new Callback<UserComplainsResponse>() {
            @Override
            public void onResponse(Call<UserComplainsResponse> call, Response<UserComplainsResponse> response) {
                UserComplainsResponse user = response.body();


                if (user != null) {

                    complainsModels = new ArrayList<>();
                    if(user.complainListModels == null || user.complainListModels.size() == 0)
                    {
                        no_comp_txt.setVisibility( View.VISIBLE );
                        progress.dismiss();
                    }
                    else {
                        if (user.complainListModels.size() > 0 || user.complainListModels != null) {
                            no_comp_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.complainListModels.size(); i++) {
                                ComplainsModel model = new ComplainsModel();
                                model.setId( user.complainListModels.get( i ).getComplainId() );
                                model.setName( user.complainListModels.get( i ).getComplainName());
                                model.setNumber( user.complainListModels.get( i ).getComplainNumber() );
                                model.setStatus( user.complainListModels.get( i ).getComplainStatus() );
                                model.setImage( user.complainListModels.get( i ).getImage() );
                                model.setPropertyname( user.complainListModels.get( i ).getPropertyName() );
                                complainsModels.add( model );
                            }

                            UserComplainAdapter userComplainAdapter = new UserComplainAdapter( getContext(), complainsModels,R.layout.complains_layout );
                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getContext() , LinearLayoutManager.VERTICAL,false );
                            complainsList.setLayoutManager( linearLayoutManager2 );
                            complainsList.setNestedScrollingEnabled(true);
                            complainsList.setAdapter( userComplainAdapter );
                        }
                        else {
                            complainsList.setVisibility( View.GONE );
                            no_comp_txt.setVisibility( View.VISIBLE );
                            progress.dismiss();
                        }
                    }

                    progress.dismiss();
                } else {
                    no_comp_txt.setVisibility( View.VISIBLE );
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserComplainsResponse> call, Throwable t) {
                no_comp_txt.setVisibility( View.VISIBLE );
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }
    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(getContext());

        apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {

                    notificationListModels = new ArrayList<>();
                    if (user.notificationsList != null) {

                     notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );
                        if(Noti_count.getText().toString().equals( "0" )){
                            Noti_bg.setVisibility( View.GONE );
                        }
                        else {
                            if(guest != null){
                                guest();
                            }
                            else {
                                Noti_bg.setVisibility( View.VISIBLE );
                            }

                        }

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
    private void update_fcm_token_api(){


        String uniqueID = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w( TAG, "Fetching FCM registration token failed", task.getException() );
                            return;
                        }

                        // Get new FCM registration token
                        String refreshedToken = task.getResult();
                        String t = refreshedToken;
                        updateFcmTokenRequest.setDeviceFCMToken( t );
                        updateFcmTokenRequest.setDeviceId( uniqueID );
                        updateFcmTokenRequest.setIOSorAndroid( false );
                       // String token = "";

                        String token = PreferenceData.getPrefUserToken(getContext());



                        Call<UpdateFcmTokenResponse> updateFcmTokenResponse = apiInterface.updateFcmTokenResponse(token,updateFcmTokenRequest);
                        updateFcmTokenResponse.enqueue(new Callback<UpdateFcmTokenResponse>() {
                            @Override
                            public void onResponse(Call<UpdateFcmTokenResponse> call, Response<UpdateFcmTokenResponse> response) {
                                UpdateFcmTokenResponse user = response.body();
                                if(user != null && user.status.equals("true"))
                                {
                                    System.out.println( user.message.toString());

                                }
                            }
                            @Override
                            public void onFailure(Call<UpdateFcmTokenResponse> call, Throwable t) {
                                System.out.println("Failed : " + t.getMessage());
                                call.cancel();
                            }
                        });
                    }
                });




    }

    private void guest_notification_api(){


        String uniqueID = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String   refreshedToken = task.getResult();

                        String t =  refreshedToken;
                        guestNotificationRequest.setDeviceFCMTokens(t  );

                    }
                });


        guestNotificationRequest.setDeviceIds(  uniqueID);
        guestNotificationRequest.setIOSorAndroid(  false);

        Call<GuestNotificationResponse> updateFcmTokenResponse = apiInterface.guestNotificationResponse(guestNotificationRequest);
        updateFcmTokenResponse.enqueue(new Callback<GuestNotificationResponse>() {
            @Override
            public void onResponse(Call<GuestNotificationResponse> call, Response<GuestNotificationResponse> response) {
                GuestNotificationResponse user = response.body();
                if(user != null && user.status.equals("true"))
                {
                    System.out.println( user.message.toString());
                 //   Toast.makeText( getContext(), "guest noti work", Toast.LENGTH_SHORT ).show();

                }
            }
            @Override
            public void onFailure(Call<GuestNotificationResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }
    private void good_morning_status_chaneged(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 5 && timeOfDay < 12){
            status.setText( "Good Morning" );
            //    Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 12 && timeOfDay < 17){
            status.setText( "Good Afternoon" );
            //   Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 17 && timeOfDay < 20){
            status.setText( "Good Evening" );
            //  Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 20 && timeOfDay < 24){
            status.setText( "Good Evening" );
            //  Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 0 && timeOfDay < 5){
            status.setText( "Good Evening" );
            //  Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
        }
    }
    private void guest(){
        if(guest != null){
            mange_dash.setVisibility( View.GONE );
            Noti_bg.setVisibility( View.GONE );
            pro_comp_hide.setVisibility( View.GONE );
            notification.setVisibility( View.GONE );
            userSetting.setVisibility( View.GONE );

            signup.setVisibility( View.VISIBLE );
          //  guest_notification_api();
            signup.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a  = new Intent(getContext(), Signup.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a );
                }
            } );
        }
        else {
            // notification count
            Notification_list_api();
        }
    }
    private void username_changed_functionality(){
        if(name_get != null)
        {
            Username.setText( "Hello," + " "+name_get );
        }
        else {
            if(PreferenceData.getPrefUserName(Username.getContext()).equals( "" )){
                Username.setText( "Hello, Guest" );
            }
            else  {
                Username.setText( "Hello," + " "+PreferenceData.getPrefUserName(Username.getContext()));

            }

        }
    }
    private void userProfile_Notification_viewMore_actions(){
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(getContext(), Notification.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        view_more.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(getContext(), EventDetails.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );

        userSetting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(), UserProfile.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
    }
    private void image_event(){
        // event api intregration
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

        apiInterface = ApiUtils.postSignUpService();
        Call<EventResponse> eventResponse = apiInterface.eventResponse(token);
        eventResponse.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse user = response.body();


                if (user != null) {

                    eventImagesModels = new ArrayList<>();
                    if(user.image.size() > 0 || user.image != null) {
                        no_event_txt.setVisibility( View.GONE );
                        if(user.image!= null){
                            for (int i = 0; i < user.image.size(); i++) {
                                EventImagesModel eventImagesModel = new EventImagesModel();
                                eventImagesModel.setId( i );
                                eventImagesModel.setImage(user.image.get(i));
                                eventImagesModels.add(eventImagesModel);
                            }

                        }

                        EventimageAdapter eventimageAdapter = new EventimageAdapter( getContext(), eventImagesModels,R.layout.event_layout );

                        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager( getContext() , LinearLayoutManager.HORIZONTAL,false );
                        eventImageslist.setLayoutManager( linearLayoutManager4 );
                        eventImageslist.setNestedScrollingEnabled(true);
                        eventImageslist.setAdapter( eventimageAdapter );
                        final int speedScroll = 5000;
                        final Handler handler = new Handler();
                        final Runnable runnable = new Runnable() {
                            int count = 0;
                            boolean flag = true;
                            @Override
                            public void run() {
                                if(count < eventimageAdapter.getItemCount()){
                                    if(count==eventimageAdapter.getItemCount()-1){
                                        flag = false;
                                    }else if(count == 0){
                                        flag = true;
                                    }
                                    if(flag) count++;
                                    else count--;

                                    eventImageslist.smoothScrollToPosition(count);
                                    handler.postDelayed(this,speedScroll);
                                }
                            }
                        };

                        handler.postDelayed(runnable,speedScroll);

                    }
                    else {
                        no_event_txt.setVisibility( View.VISIBLE );
                        progress.dismiss();
                    }
                    progress.dismiss();
                } else {
                    no_event_txt.setVisibility( View.VISIBLE );
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
    private void nav_bar(){}
    private void event_app_landing_Api(){

            String token = PreferenceData.getPrefUserToken(getContext());

            apiInterface = ApiUtils.postSignUpService();

            ProgressDialog progress = new ProgressDialog(getContext());
            progress.setMessage("Please wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        // progress.show();

            Call<EventDetailsResponse> eventDetailResponse = apiInterface.eventDetailResponse(token);
            eventDetailResponse.enqueue(new Callback<EventDetailsResponse>() {
                @Override
                public void onResponse(Call<EventDetailsResponse> call, Response<EventDetailsResponse> response) {
                    EventDetailsResponse user = response.body();


                    if (user != null) {

                        eventDetails = new ArrayList<>();
                        eventDetailsImageModels = new ArrayList<>();

                        if (user.Eventsmodel != null) {
                            no_event_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.Eventsmodel.size(); i++) {
                                EventDetailsModel model = new EventDetailsModel();
                                model.setEventName( user.Eventsmodel.get( i ).eventName );
                                model.setDescriptions( user.Eventsmodel.get( i ).descriptions );
                                model.setEventDate(user.Eventsmodel.get( i ).EventDate  );
                                model.setEventTime(user.Eventsmodel.get( i ).EventTime  );
                                model.setId( user.Eventsmodel.get( i ).id );
                                model.setImages( user.Eventsmodel.get( i ).image );
                                eventDetails.add( model );
                            }
                            EventApplandingAdapter eventimageAdapter = new EventApplandingAdapter( getContext(), eventDetails,R.layout.news_annocement_layout );

                            LinearLayoutManager    linearLayoutManager4 = new LinearLayoutManager( getContext() , LinearLayoutManager.HORIZONTAL,false );
                            eventImageslist.setLayoutManager( linearLayoutManager4 );
                            eventImageslist.setNestedScrollingEnabled(true);
                            eventImageslist.setAdapter( eventimageAdapter );
                            if(check_menu_open != true && eventDetails != null && eventimageAdapter.getItemCount() > 1) {
                                final int speedScroll = 2000;
                                final Handler handler = new Handler();
                                final Runnable runnable = new Runnable() {
                                    int count = 0;
                                    boolean flag = true;

                                    @Override
                                    public void run() {
                                        if (count < eventimageAdapter.getItemCount()) {
                                            if (count == eventimageAdapter.getItemCount() - 1) {
                                                flag = false;
                                            } else if (count == 0) {
                                                flag = true;
                                            }
                                            if (flag) count++;
                                            else count--;

                                            eventImageslist.smoothScrollToPosition( count );
                                            handler.postDelayed( this, slide_timer );
                                        }
                                    }
                                };

                                handler.postDelayed( runnable, slide_timer );
                            }


                        }
                        else {
                            if(user.Eventsmodel == null || user.Eventsmodel.size() == 0){
                                no_event_txt.setVisibility( View.VISIBLE );
                               view_more.setVisibility( View.GONE );
                            }
                            progress.dismiss();
                        }
                        progress.dismiss();
                    } else {
                        no_event_txt.setVisibility( View.VISIBLE );
                        System.out.println( "Failed" );
                    }

                }

                @Override
                public void onFailure(Call<EventDetailsResponse> call, Throwable t) {
                    System.out.println("Failed : " + t.getMessage());
                    call.cancel();
                }
            });

    }


class custom implements RootTransformation {

    @Override
    public void transform(float dragProgress, View rootView) {
    }
}

    @Override
    public void onResume() {
        super.onResume();
        Notification_list_api();
        complaint_app_landing_api();
      //  news_annoce_App_landing_api();
    }

    @Override
    public void onStart() {
        Notification_list_api();
        super.onStart();
    }

    @Override
    public void onPause() {
        Notification_list_api();
        super.onPause();
    }
}