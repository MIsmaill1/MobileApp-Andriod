package com.HS.Topcity.Fragsment.Development;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.HS.Topcity.Adapters.DevelopmentVideoAdapter;
import com.HS.Topcity.ApiModels.Development.DevelopmentResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFrag extends Fragment {
    ArrayList<String> video;
  public static  RecyclerView item_list;
    SwipeRefreshLayout swipeRefreshLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VideoFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFrag newInstance(String param1, String param2) {
        VideoFrag fragment = new VideoFrag();
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
        View v = inflater.inflate( R.layout.fragment_video, container, false );

        // ids
        item_list = v.findViewById( R.id.development_video_list );
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout_home);

        // video api call
//        if(check_video == false){
//            item_list.setVisibility( View.GONE );
//        }
//        if(check_video == true){
//
//        }
        video_api();
        // refresh data
        SwipeRefreshLayout();
        return v;
    }
    private void SwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                video_api();
                swipeRefreshLayout.setRefreshing(false);

            }
        } );

    }
    private void video_api(){

        ApiInterface apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog( getContext() );
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken( this.getContext() );


        Call<DevelopmentResponse> newsAndAnnouncementsResponseCall = apiInterface.developmentResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<DevelopmentResponse>() {
            @Override
            public void onResponse(Call<DevelopmentResponse> call, Response<DevelopmentResponse> response) {
                DevelopmentResponse user = response.body();


                if (user != null) {

                    if(user.developmentModel != null){
                        if(user.developmentModel.videos.size() != 0)
                        {
                            video = new ArrayList<>();
                            for (int i =0; i<user.developmentModel.videos.size();i++)
                            {
                                video.add( user.developmentModel.videos.get( i ) );
                            }
                            DevelopmentVideoAdapter developmentVideoAdapter = new DevelopmentVideoAdapter( getContext(), video, getActivity());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false );
                            item_list.setLayoutManager( linearLayoutManager );
                            item_list.setAdapter( developmentVideoAdapter );
//                           item_list.setAdapter( featuresItemAdapter );
                        }
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<DevelopmentResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }
}