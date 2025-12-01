package com.HS.Topcity.FireBase;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.HS.Topcity.Common.SharedPrefredManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
       Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(refreshedToken);
        worktoken( refreshedToken );
    }

private void worktoken(String token) {
    SharedPrefredManager.getInstance( getApplicationContext() ).storeToken( token );
}
}
