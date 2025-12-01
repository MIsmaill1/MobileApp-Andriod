package com.HS.Topcity.Fragsment;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.Dashboard;
import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.Signup;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.Adapters.FeaturesItemAdapter;
import com.HS.Topcity.ApiModels.Features.FeaturesResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.Feature_itemModel;
import com.HS.Topcity.R;
import com.universalvideoview.UniversalVideoView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Features#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Features extends Fragment {

    ApiInterface apiInterface;
    ArrayList<Feature_itemModel> feature_itemModels;
    RecyclerView item_list;
    CardView cardView;
    ImageView notification,user,back;
    LinearLayout signup;
    UniversalVideoView mVideoView;
    TextView Noti_count;
    LinearLayout Noti_bg;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Features() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Features.
     */
    // TODO: Rename and change types and number of parameters
    public static Features newInstance(String param1, String param2) {
        Features fragment = new Features();
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

        View v = inflater.inflate( R.layout.fragment_features, container, false );

        // ids


        mVideoView = v.findViewById( R.id.videoView );

        mVideoView.setVideoURI( Uri.parse( "android.resource://" + getContext().getPackageName() + "/" + R.raw.feature_video ) );
        mVideoView.start();
        mVideoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping( true );
            }
        } );

        cardView = v.findViewById( R.id.features_video );
        //   cardView.setBackgroundResource( R.drawable.card_view_bottom_round );

        item_list = v.findViewById( R.id.features_item );
         back = v.findViewById( R.id.backtodashboard1 );
         notification = v.findViewById( R.id.feature_notification );
         user = v.findViewById( R.id.feature_userprofile );
        signup = v.findViewById( R.id.signup_btn_home );
        Noti_count = v.findViewById( R.id.notification_Count );
        Noti_bg = v.findViewById( R.id.notification_Count_bg );
        Noti_count.setText( notication_count );

        Noti_bg.setVisibility( View.GONE );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        else {
            Noti_bg.setVisibility( View.VISIBLE );
        }
         // guest
        guest();
        //
        Notification_list_api();

        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( container.getContext(), Notification.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( container.getContext(), Dashboard.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );
        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( container.getContext(), UserProfile.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );
        VideoView simpleVideoView = (VideoView) v.findViewById( R.id.video_view );

        simpleVideoView.setVideoURI( Uri.parse( "android.resource://" + getContext().getPackageName() + "/" + R.raw.feature_video ) );
        simpleVideoView.start();

    feature_item_api();

        return v;
    }
    private void feature_item_api(){
        apiInterface = ApiUtils.postSignUpService();
        ProgressDialog progress = new ProgressDialog( getContext() );
        progress.setMessage( "Please wait while loading..." );
        progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken( this.getContext() );
        Call<FeaturesResponse> featuresResponse = apiInterface.featuresResponse( token );
        featuresResponse.enqueue( new Callback<FeaturesResponse>() {
            @Override
            public void onResponse(Call<FeaturesResponse> call, Response<FeaturesResponse> response) {
                FeaturesResponse user = response.body();
                if (user != null) {
                    feature_itemModels = new ArrayList<>();
                    if (user.featureModels != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.featureModels.size(); i++) {
                            Feature_itemModel featureItemModel = new Feature_itemModel();
                            featureItemModel.setId( user.featureModels.get( i ).getFeatureId() );
                            featureItemModel.setName( user.featureModels.get( i ).getFeatureName() );
                       featureItemModel.setImage( user.featureModels.get( i ).getFeatureImage() );

                            feature_itemModels.add( featureItemModel );

                        }
                        FeaturesItemAdapter featuresItemAdapter = new FeaturesItemAdapter(getContext(), feature_itemModels, R.layout.feature_item_layout );
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false );
                        item_list.setLayoutManager( linearLayoutManager );
                        item_list.setLayoutManager( new GridLayoutManager( getActivity().getBaseContext(), 2 ) );
                        item_list.setAdapter( featuresItemAdapter );
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }
            }

            @Override
            public void onFailure(Call<FeaturesResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
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


                    if (user.notificationsList != null) {

                        notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );
                        Noti_bg.setVisibility( View.GONE );
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
    @Override
    public void onResume() {
        mVideoView.resume();
        super.onResume();
        Notification_list_api();
    }

    @Override
    public void onPause() {
        mVideoView.suspend();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mVideoView.stopPlayback();
        super.onDestroy();
    }

    private void guest(){
        if(guest != null){
            Noti_bg.setVisibility( View.GONE );
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