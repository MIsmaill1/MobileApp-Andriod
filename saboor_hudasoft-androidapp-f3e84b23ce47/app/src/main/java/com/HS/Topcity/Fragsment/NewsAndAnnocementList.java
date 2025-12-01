package com.HS.Topcity.Fragsment;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.Signup;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.Adapters.NewsAnnouncementAllListAdapter;
import com.HS.Topcity.ApiModels.NewsAndAnnouncements.NewsAndAnnouncementsResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.NewsAnnouncementModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsAndAnnocementList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsAndAnnocementList extends Fragment {

    ApiInterface apiInterface;
    ArrayList<NewsAnnouncementModel> newsAnnouncementModels;
    RecyclerView newsaAnnoceAllList;
    TextView status,Username;
    ImageView notification,user,back;
    LinearLayout signup;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsAndAnnocementList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsAndAnnocementList.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsAndAnnocementList newInstance(String param1, String param2) {
        NewsAndAnnocementList fragment = new NewsAndAnnocementList();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate( R.layout.fragment_news_and_annocement_list, container, false );


        // ids
        newsaAnnoceAllList = v.findViewById( R.id.news_and_announcements_list );

        back = v.findViewById( R.id.back_tohome );
        user = v.findViewById( R.id.user_icon_home);
        notification = v.findViewById( R.id.notification_home );
        signup = v.findViewById( R.id.signup_btn_home );


        // guest
        guest();

        // api
        api();



        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new HomeFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();

            }
        } );
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(), Notification.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(), UserProfile.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );






        return v;
    }
    private void api(){
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // news Announcement api intregration

        Call<NewsAndAnnouncementsResponse> newsAndAnnouncementsResponseCall = apiInterface.newsAndAnnouncementResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<NewsAndAnnouncementsResponse>() {
            @Override
            public void onResponse(Call<NewsAndAnnouncementsResponse> call, Response<NewsAndAnnouncementsResponse> response) {
                NewsAndAnnouncementsResponse user = response.body();


                if (user != null) {

                    newsAnnouncementModels = new ArrayList<>();
                    if (user.newsAndAnnouncementModel != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.newsAndAnnouncementModel.size(); i++) {
                            NewsAnnouncementModel model = new NewsAnnouncementModel();
                            model.setDateofNews( user.newsAndAnnouncementModel.get( i ).DateofNews );
                            model.setNewsName( user.newsAndAnnouncementModel.get( i ).newsName );
                            model.setDescriptions( user.newsAndAnnouncementModel.get( i ).Descriptions );
                            model.setNotes( user.newsAndAnnouncementModel.get( i ).notes );
                            model.setTimeOfNews( user.newsAndAnnouncementModel.get( i ).TimeOfNews );
                            model.setImage( user.newsAndAnnouncementModel.get( i ).Image );
                            model.setId( user.newsAndAnnouncementModel.get( i ).id );
                            newsAnnouncementModels.add( model );
                        }

                        NewsAnnouncementAllListAdapter newsAnnouncementAdapter = new NewsAnnouncementAllListAdapter(getContext(), newsAnnouncementModels, R.layout.news_annocement_list_layout);

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL,false );
                        newsaAnnoceAllList.setLayoutManager( linearLayoutManager2 );
                        newsaAnnoceAllList.setNestedScrollingEnabled(true);
                        newsaAnnoceAllList.setAdapter( newsAnnouncementAdapter);




                    }
                    progress.dismiss();
                } else {
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
    private void guest(){
        if(guest != null){
            user.setVisibility( View.GONE );
            notification.setVisibility( View.GONE );
            signup.setVisibility( View.VISIBLE );
            signup.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a  = new Intent(getContext(), Signup.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a );
                }
            } );
        }
    }


}