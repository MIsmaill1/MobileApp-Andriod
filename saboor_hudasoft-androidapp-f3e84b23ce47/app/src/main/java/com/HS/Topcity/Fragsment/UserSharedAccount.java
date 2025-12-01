package com.HS.Topcity.Fragsment;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.AddSharedAccount;
import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.Adapters.SharedAccountLisrAdapter;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.UserSharedAccounts.UserSharedAccountsResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.UserSharedAccountListModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserSharedAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSharedAccount extends Fragment {
    ImageView notification,user,back;
    LinearLayout add_user_btn;
    RecyclerView sharedAccountList;
    String token;
    ProgressDialog progress;
    ArrayList<UserSharedAccountListModel> userSharedAccountListModels;
    ApiInterface apiInterface;
    TextView Noti_count;
    LinearLayout Noti_bg;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserSharedAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserSharedAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static UserSharedAccount newInstance(String param1, String param2) {
        UserSharedAccount fragment = new UserSharedAccount();
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

        View v = inflater.inflate( R.layout.fragment_user_shared_account, container, false );
        notification = v.findViewById( R.id.notification_shared_Account );
        user = v.findViewById( R.id.user_icon_sharedAccount );
        back = v.findViewById( R.id.back_to_add_share_account_list );
        add_user_btn = v.findViewById( R.id.shared_Account_AddNewUser_Btn);
        sharedAccountList = v.findViewById( R.id.Shared_account_detail_list );
        Noti_count = v.findViewById( R.id.notification_Count );
        Noti_bg = v.findViewById( R.id.notification_Count_bg );
        Noti_count.setText( notication_count );

         token = PreferenceData.getPrefUserToken(container.getContext());
         progress = new ProgressDialog(container.getContext());

         // method call of api
         SharedAccountList_api();
         // hide share user list
        management_tenat_family();
        Noti_bg.setVisibility( View.GONE );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        else {
            Noti_bg.setVisibility( View.VISIBLE );
        }
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent(container.getContext(), Notification.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
//                Intent a  = new Intent(container.getContext(), UserProfile.class);
//                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity( a );

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
        add_user_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), AddSharedAccount.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        return v;
    }
    private void management_tenat_family(){
        if (PreferenceData.getIsTenat( getContext() ) == true || PreferenceData.getIsFamily( getContext() ) == true || PreferenceData.getIsMangement( getContext() ) == true ){

            sharedAccountList.setVisibility( View.GONE );
        }
        else {
            sharedAccountList.setVisibility( View.VISIBLE );
        }
    }
    private void SharedAccountList_api(){

        apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();



        Call<UserSharedAccountsResponse> newsAndAnnouncementsResponseCall = apiInterface.userSharedAccountsResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<UserSharedAccountsResponse>() {
            @Override
            public void onResponse(Call<UserSharedAccountsResponse> call, Response<UserSharedAccountsResponse> response) {
                UserSharedAccountsResponse user = response.body();


                if (user != null) {

                    userSharedAccountListModels = new ArrayList<>();
                    if (user.userInfosModels != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.userInfosModels.size(); i++) {
                            UserSharedAccountListModel model = new UserSharedAccountListModel();
                            model.setFullName( user.userInfosModels.get( i ).getFullName() );
                            model.setStateOfAccount( user.userInfosModels.get( i ).getStateOfAccount() );
                            model.setTypeAccountId( user.userInfosModels.get( i ).getTypeOfAccountId() );
                            model.setTypeOfAccount(user.userInfosModels.get( i ).getTypeOfAccount()  );
                            model.setPropertyId( user.userInfosModels.get( i ).getPropertyId() );
                            model.setImage( user.userInfosModels.get( i ).Image );
                            model.setId( user.userInfosModels.get( i ).getId() );
                            model.setPropertyName( user.userInfosModels.get( i ).getPropertyName() );
                            userSharedAccountListModels.add( model );
                        }

                        //  NewsAnnouncementAdapter newsAnnouncementAdapter = new NewsAnnouncementAdapter( container.getContext(), newsAnnouncementModels,R.layout.news_annocement_layout );

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getContext() , LinearLayoutManager.VERTICAL,false );
                        sharedAccountList.setLayoutManager( linearLayoutManager2 );
                        sharedAccountList.setNestedScrollingEnabled(true);
                        sharedAccountList.setAdapter( new SharedAccountLisrAdapter(getContext(), userSharedAccountListModels, R.layout.shared_account_user_list_layout));



                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<UserSharedAccountsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        SharedAccountList_api();
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