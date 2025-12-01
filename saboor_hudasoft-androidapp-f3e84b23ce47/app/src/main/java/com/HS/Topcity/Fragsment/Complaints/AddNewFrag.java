package com.HS.Topcity.Fragsment.Complaints;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.AddNewComplainAdapter;
import com.HS.Topcity.ApiModels.ComplaintType.ComplaintTypeResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.ComplainSubTypesModels;
import com.HS.Topcity.Models.ComplaintTypeModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewFrag extends Fragment {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    ArrayList<ComplainSubTypesModels> complainSubTypesModels;
    ArrayList<ComplaintTypeModel> complaintTypeModels;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewFrag newInstance(String param1, String param2) {
        AddNewFrag fragment = new AddNewFrag();
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
        View v = inflater.inflate( R.layout.fragment_add_new, container, false);
        recyclerView = v.findViewById( R.id.add_new_complaints_list );
        api();

        return  v;
    }

    private void api(){
        apiInterface = ApiUtils.postSignUpService();
        ProgressDialog progress = new ProgressDialog( getContext() );
        progress.setMessage( "Please wait while loading..." );
        progress.setCancelable( false ); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken( getContext());
        Call<ComplaintTypeResponse> complaintTypeResponse = apiInterface.complaintTypeResponse( token );
        complaintTypeResponse.enqueue( new Callback<ComplaintTypeResponse>() {
            @Override
            public void onResponse(Call<ComplaintTypeResponse> call, Response<ComplaintTypeResponse> response) {
                ComplaintTypeResponse user = response.body();
                if (user != null) {
                    complainSubTypesModels = new ArrayList<>();
                    complaintTypeModels = new ArrayList<>();
                    if (user.complaintType != null) {
//                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                        for (int i = 0; i < user.complaintType.size(); i++) {
                            ComplaintTypeModel complaintTypeModel = new ComplaintTypeModel();
                            complaintTypeModel.setComplaintTypeId( user.complaintType.get( i ).getComplaintTypeId() );
                            complaintTypeModel.setComplaintTypeName( user.complaintType.get( i ).getComplaintTypeName() );
                            complaintTypeModel.setComplaintTypeImage( user.complaintType.get( i ).getComplaintTypeImage() );
                            complainSubTypesModels = new ArrayList<>();
                            for(int a = 0; a < user.complaintType.get( i ).complainSubTypesModels.size(); a++)
                            {
                                ComplainSubTypesModels complainSubTypesModel = new ComplainSubTypesModels();
                                complainSubTypesModel.setComplaintSubTypeId( user.complaintType.get( i ).complainSubTypesModels.get( a ).complaintSubTypeId );
                                complainSubTypesModel.setComplaintSubTypeName( user.complaintType.get( i ).complainSubTypesModels.get( a ).complaintSubTypeName );
                                complainSubTypesModels.add(complainSubTypesModel);

                            }
                            complaintTypeModel.setComplainSubTypesModels( complainSubTypesModels );
                            complaintTypeModels.add( complaintTypeModel );

                        }

                        AddNewComplainAdapter featuresItemAdapter = new AddNewComplainAdapter( getContext(), complaintTypeModels,complainSubTypesModels, R.layout.feature_item_layout );
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );
                        recyclerView.setLayoutManager( linearLayoutManager );
                        recyclerView.setLayoutManager( new GridLayoutManager(getActivity().getBaseContext(), 2 ) );
                        recyclerView.setAdapter( featuresItemAdapter );
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }
            }

            @Override
            public void onFailure(Call<ComplaintTypeResponse> call, Throwable t) {
                System.out.println( "Failed : " + t.getMessage() );
                call.cancel();
            }
        } );
    }
}