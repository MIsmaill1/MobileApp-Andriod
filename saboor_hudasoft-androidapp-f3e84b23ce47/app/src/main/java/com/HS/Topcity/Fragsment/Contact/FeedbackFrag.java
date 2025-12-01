package com.HS.Topcity.Fragsment.Contact;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.HS.Topcity.ApiModels.FeedBack.FeedbackRequest;
import com.HS.Topcity.ApiModels.FeedBack.FeedbackResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.FeedbackSuccessfully;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFrag extends Fragment {

    EditText name,email,phone,subject,msg;
    LinearLayout submit;

    FeedbackRequest feedbackRequest = new FeedbackRequest();
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\*\\&\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFrag newInstance(String param1, String param2) {
        FeedbackFrag fragment = new FeedbackFrag();
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
        View v = inflater.inflate( R.layout.fragment_feedback, container, false );

        name=v.findViewById( R.id.feedback_name );
        email=v.findViewById( R.id.feedback_email );
        phone=v.findViewById( R.id.feedback_phone );
        subject=v.findViewById( R.id.feedback_subject );
        msg =v.findViewById( R.id.feedback_msg);
       submit=v.findViewById( R.id.feeback_submit );


       submit.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(name.getText().toString().equals( "" )){
                   name.setError( "Please fill this field" );
               }
               if(email.getText().toString().equals( "" )){
                   email.setError( "Please fill this field" );
               }
               else {
                   if(!email.getText().toString().trim().matches(emailPattern)){
                       email.setError( "Invalid Email" );
                   }
               }

               if(phone.getText().toString().equals( "" )){
                   phone.setError( "Please fill this field" );
               }
               else {
                   if(phone.getText().length() > 11 || phone.getText().length() < 11  ){
                       phone.setError( "Please Enter Correct Phone Number" );
                   }
               }


               if(subject.getText().toString().equals( "" )){
                   subject.setError( "Please fill this field" );
               }
               if(msg.getText().toString().equals( "" )){
                   msg.setError( "Please fill this field" );
               }

               if(name.getText().toString() != ""  && email.getText().toString() != ""  && phone.getText().toString() != ""  && subject.getText().toString() != "" && msg.getText().toString() != ""
                       && phone.getText().length() == 11 && email.getText().toString().trim().matches(emailPattern)  ){
                   Feedback_api();
               }


//               if(name.getText().toString().equals( "" ) || email.getText().toString().equals( "" ) || phone.getText().toString().equals( "" ) || subject.getText().toString().equals( "" ) || msg.getText().toString().equals( "" ) ){
//                   AlertDialog.Builder builder1 = new AlertDialog.Builder( getContext());
//                   builder1.setMessage("Missig Field");
//                   builder1.setTitle( "Alert" );
//                   builder1.setCancelable(false);
//                   builder1.setPositiveButton(
//                           "OK",
//                           new DialogInterface.OnClickListener() {
//                               public void onClick(DialogInterface dialog, int id) {
//                                   // complaint with draw api call
//                                   dialog.cancel();
//                               }
//                           });
//                   builder1.show();
//               }
//               else {
//
//
//               }

           }
       } );


        return v;
    }

    private void Feedback_api(){

        ApiInterface apiInterface = ApiUtils.postSignUpService();

        ProgressDialog progress = new ProgressDialog( getContext() );
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken( this.getContext() );

        feedbackRequest.setName( name.getText().toString() );
        feedbackRequest.setEmail( email.getText().toString() );
        feedbackRequest.setPhoneNumber( phone.getText().toString() );
        feedbackRequest.setSubject( subject.getText().toString() );
        feedbackRequest.setMessage( msg.getText().toString() );

        Call<FeedbackResponse> FeedbackResponse = apiInterface.feedbackResponse(token,feedbackRequest);
        FeedbackResponse.enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                FeedbackResponse user = response.body();


                if (user != null) {

                    if (user.status != false) {

                        Intent a = new Intent(getContext(), FeedbackSuccessfully.class );
                        startActivity( a );
                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        name.setText( "" );
        email.setText( "" );
        phone.setText( "" );
        subject.setText( "" );
        msg.setText( "" );
        super.onResume();

    }
}