package com.HS.Topcity.Fragsment.Contact;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.HS.Topcity.Activity.Complaint;
import com.HS.Topcity.ApiModels.ContactUs.ContactUsResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFrag extends Fragment implements OnMapReadyCallback {

    TextView phone,email,address_site,address_down,compplaint;
    MapView mapView;
    LinearLayout call,Email;
    FrameLayout map_frag;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    public static final String MAPView_Bundle_key = "MapViewBundleKey";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactUsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFrag newInstance(String param1, String param2) {
        ContactUsFrag fragment = new ContactUsFrag();
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
        View v = inflater.inflate( R.layout.fragment_contact_us, container, false );

        phone = v.findViewById( R.id.contactUs_phone );
        email = v.findViewById( R.id.contactUs_email);
        address_site = v.findViewById( R.id.contactUs_address_siteoffice);
        address_down = v.findViewById( R.id.contactUs_address_downOffice);
        mapView = v.findViewById( R.id.mapView );
        compplaint = v.findViewById( R.id.complaint_screen );
        call =v.findViewById( R.id.feedback_call );
        Email =v.findViewById( R.id.feedback_email_go );
        map_frag = v.findViewById( R.id.map_frag1 );

        call.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData( Uri.parse("tel:"+phone.getText().toString()));//change the number
                startActivity(callIntent);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//                {
//                    if (ContextCompat.checkSelfPermission( getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//
//                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                        callIntent.setData( Uri.parse("tel:"+phone.getText().toString()));//change the number
//                        startActivity(callIntent);
//                    }
//                    else
//                    {
//                        // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
//                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 11);
//                    }
//                }
//                else
//                {
//                    // if version is below m then write code here,
//                }

            }
        } );
        Email.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + email.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        } );
        compplaint.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(), Complaint.class );
                startActivity( a );
            }
        } );

        //initGoogleMap(savedInstanceState);

//        double latlong = 33.63483;
//        double longitude = 72.91968;

        Contatct_api();
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag1);
  //      mapFragment.getMapAsync( this );

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        try {
            mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map_frag1);
            mapFragment.getMapAsync(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

            {
                if (ContextCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mapFragment.getMapAsync(this);
                }
                else
                {
                    // Toast.makeText( getApplicationContext(), "Please Allow the camera", Toast.LENGTH_SHORT ).show();
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
                }
            }
            else
            {
                // if version is below m then write code here,
            }

        }
        catch (Exception e){
            System.out.println(e);
        }


        return v;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 11)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                mapFragment.getMapAsync(this);
            }
            else
            {
                Toast.makeText(getContext(), "Please Allow the Location permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void Contatct_api(){

     ApiInterface apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog( getContext() );
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken( this.getContext() );


        Call<ContactUsResponse> newsAndAnnouncementsResponseCall = apiInterface.contactUsResponse(token);
        newsAndAnnouncementsResponseCall.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                ContactUsResponse user = response.body();


                if (user != null) {

                    if (user.contactUs != null) {
                        phone.setText( user.contactUs.mobileNumber );
                        email.setText( user.contactUs.email );
                        address_site.setText( user.contactUs.address );
                        address_down.setText("TopCity-1, Office 7-11, 1st Floor, Sajid Sharif Plaza, G-11 Markaz Islamabad, Pakistan");
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

      //  LatLng sydney = new LatLng(33.58289313261719,  72.86725820851436);
       LatLng sydney = new LatLng(33.63483, 72.91968);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        LatLng sydney = new LatLng(-33.582935328275816, 72.86734203069057);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(sydney);
        markerOptions.title("TopCity-1 Location");
      Marker map=  mMap.addMarker(markerOptions);
      map.showInfoWindow();
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.addMarker(new MarkerOptions().position(sydney));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(sydney, 10));

    }
}