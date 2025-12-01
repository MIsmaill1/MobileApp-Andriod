package com.HS.Topcity.Common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefredManager {
    static final String PREF_fireBase_TOKEN = "Firebase_token";
    static final String PREF_name = "Fcm_sharedpref";

    private static Context context;
    private  static SharedPrefredManager mInstance;

    private SharedPrefredManager(Context context){
        context = context;
    }

    public   static  synchronized  SharedPrefredManager getInstance(Context context){
        if(mInstance == null)
            mInstance = new SharedPrefredManager( context );
        return mInstance;
    }
    public boolean storeToken(String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences( PREF_name,Context.MODE_PRIVATE );
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString( PREF_fireBase_TOKEN,token );
        editor.apply();
        return true;
    }
    public String getToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences( PREF_name,Context.MODE_PRIVATE );
        return sharedPreferences.getString( PREF_fireBase_TOKEN,null);
    }
}
