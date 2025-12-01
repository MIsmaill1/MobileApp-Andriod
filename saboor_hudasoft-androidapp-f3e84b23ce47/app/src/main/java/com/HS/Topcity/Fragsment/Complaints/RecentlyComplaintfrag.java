package com.HS.Topcity.Fragsment.Complaints;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.UserComplainAdapter;
import com.HS.Topcity.ApiModels.RecentlyAddedComplaint.RecentlyAddedComplaintResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplainsModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentlyComplaintfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentlyComplaintfrag extends Fragment {

    RecyclerView recyclerView;
    LinearLayout no_comp_txt;
    ArrayList<ComplainsModel> complainsModels;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecentlyComplaintfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecentlyComplaintfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RecentlyComplaintfrag newInstance(String param1, String param2) {
        RecentlyComplaintfrag fragment = new RecentlyComplaintfrag();
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

        View v = inflater.inflate( R.layout.fragment_recently_complaintfrag, container, false );

        recyclerView = v.findViewById( R.id.recently_complaints_list );
        no_comp_txt = v.findViewById( R.id.no_Recentlycomplaint_text );

        // api
        api();

        return v;
    }
    private void api(){
        // complains api intregration
        String token = PreferenceData.getPrefUserToken(getContext());
        ProgressDialog progress = new ProgressDialog(getContext());

    ApiInterface apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //   progress.show();
        Call<RecentlyAddedComplaintResponse> userComplainsResponseCall = apiInterface.recentlyAddedComplaintResponse(token);
        userComplainsResponseCall.enqueue(new Callback<RecentlyAddedComplaintResponse>() {
            @Override
            public void onResponse(Call<RecentlyAddedComplaintResponse> call, Response<RecentlyAddedComplaintResponse> response) {
                RecentlyAddedComplaintResponse user = response.body();


                if (user != null) {

                    complainsModels = new ArrayList<>();
                    if(user.complainList == null || user.complainList.size() == 0 )
                    {
                        no_comp_txt.setVisibility( View.VISIBLE );
                        progress.dismiss();
                    }
                    else {
                        if (user.complainList.size() > 0 || user.complainList != null) {
                            no_comp_txt.setVisibility( View.GONE );
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                            for (int i = 0; i < user.complainList.size(); i++) {
                                ComplainsModel model = new ComplainsModel();
                                model.setId( user.complainList.get( i ).getComplainId() );
                                model.setName( user.complainList.get( i ).getComplainName());
                                model.setNumber( user.complainList.get( i ).getComplainNumber() );
                                model.setStatus( user.complainList.get( i ).getComplainStatus() );
                                model.setImage( user.complainList.get( i ).getImage() );
                                model.setPropertyname( user.complainList.get( i ).getPropertyName() );
                                complainsModels.add( model );
                            }

                            UserComplainAdapter userComplainAdapter = new UserComplainAdapter( getContext(), complainsModels,R.layout.complains_layout );
                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getContext() , LinearLayoutManager.VERTICAL,false );
                            recyclerView.setLayoutManager( linearLayoutManager2 );
                            recyclerView.setNestedScrollingEnabled(true);
                            recyclerView.setAdapter( userComplainAdapter );
                        }
                        else {
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
            public void onFailure(Call<RecentlyAddedComplaintResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        api();
    }
}