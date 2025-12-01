package com.HS.Topcity.Fragsment;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.HS.Topcity.Activity.CommunityService;
import com.HS.Topcity.Activity.Complaint;
import com.HS.Topcity.Activity.Dashboard;
import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesDesk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesDesk extends Fragment {

    LinearLayout complains_box,community_box;
    ImageView back,user,notification;
    TextView Noti_count;
    LinearLayout Noti_bg;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicesDesk() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesDesk.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesDesk newInstance(String param1, String param2) {
        ServicesDesk fragment = new ServicesDesk();
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
        View v = inflater.inflate( R.layout.fragment_services_desk, container, false );

        // fragment complains
        back = v.findViewById( R.id.back_to_ServiceDesk );
        community_box =  v.findViewById( R.id.comunity_service_box);
        complains_box = v.findViewById( R.id.complains_box );
        user = v.findViewById( R.id.user_icon_ServiceDesk );
        notification = v.findViewById( R.id.notification_Servicedesk );
        Noti_count = v.findViewById( R.id.notification_Count );
        Noti_bg = v.findViewById( R.id.notification_Count_bg );
        Noti_count.setText( notication_count );

        // noti_cont
        Notification_list_api();
        // management
        management();
        Noti_bg.setVisibility( View.GONE );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        else {
            Noti_bg.setVisibility( View.VISIBLE );
        }
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), Dashboard.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );

        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), UserProfile.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), Notification.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );

        community_box.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container  , new CommunityService()).addToBackStack( "servicedesk" ).commit();

                Intent a = new Intent(getContext(), CommunityService.class);
                startActivity( a );
            }
        } );

        complains_box.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(getContext(), Complaint.class);
                startActivity( a );
            }
        } );
        return v;
    }
    private void management(){
        if(PreferenceData.getIsMangement( getContext() ) == true){
            complains_box.setVisibility( View.GONE );

            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                    320 ,380);
            layoutParams1.setMargins( 20,0,0,0 );
            community_box.setLayoutParams(layoutParams1  );
//            community_box.setLayoutParams( parms );
        }
    }
    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(getContext());

    ApiInterface apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {


                    if (user.notificationsList != null) {

                        notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );

                        if(Noti_count.getText().toString().equals( "0" )){
                            Noti_bg.setVisibility( View.GONE );
                        }
                        else {
                            Noti_bg.setVisibility( View.VISIBLE );
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
        Notification_list_api();
        super.onResume();

    }
}