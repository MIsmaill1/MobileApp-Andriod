package com.HS.Topcity.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceData {

    static final String PREF_LOGGEDIN_USER_EMAIL = "logged_in_email";
    static final String PREF_LOGGEDIN_USER_CNIC = "logged_in_CNIC";
    static final String PREF_USER_LOGGEDIN_STATUS = "logged_in_status";
    static final String PREF_USER_FIGER_ENABLE_STATUS = "logged_in_enable_finger_status";
    static final String PREF_USER_NAME = "logged_in_username";
    static final String TOKEN_START_TIME = "token_start_time";
    static final String TOKEN_EXPIRE_TIME = "token_expire_time";
    static final String PREF_USER_IMAGE_URL = "logged_in_user_image_url";
    static final String PREF_USER_TOKEN = "user_token";
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static final String IS_MANGEMENT = "Is_Management";
    public static final String IS_TENAT = "IS_TENAT";
    public static final String IS_Family = "IS_Family";
    public static final int ERROR_DIALOG_REQUEST = 9001;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9002;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9003;
//    static final String PREF_fireBase_TOKEN = "Firebase_token";
//    static final String PREF_name = "Fcm_sharedpref";




    public static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }



    // getter setter of user token
    public static void setPrefUserToken(Context ctx, String usertoken){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_TOKEN, usertoken);
        editor.commit();
    }
    public static String getPrefUserToken(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_TOKEN, "");
    }

//    public static void setPREF_fireBase_TOKEN(Context ctx, String usertoken){
//        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
//        editor.putString(PREF_fireBase_TOKEN, usertoken);
//        editor.commit();
//    }
//    public static String getPREF_fireBase_TOKEN(Context ctx){
//        return getSharedPreferences(ctx).getString(PREF_fireBase_TOKEN, "");
//    }






    // getter setter of useremail
    public static void setLoggedInUserEmail(Context ctx, String email){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LOGGEDIN_USER_EMAIL, email);
        editor.commit();
    }
    public static String getLoggedInEmailUser(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_USER_EMAIL, "");
    }
    // getter setter of useremail
    public static void setPrefLoggedinUserCnic(Context ctx, String email){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LOGGEDIN_USER_CNIC, email);
        editor.commit();
    }
    public static String getPrefLoggedinUserCnic(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_USER_CNIC, "");
    }

    // getter setter of username
    public static void setPrefUserName(Context ctx, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }
    public static String getPrefUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }


    // getter setter of user Image
    public static void setPrefUserImageUrl(Context ctx, String imageurl) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_IMAGE_URL, imageurl);
        editor.commit();
    }
    public static String getPrefUserImageUrl(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_IMAGE_URL, "");
    }


    // getter setter of user status
    public static void setUserLoggedInStatus(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_USER_LOGGEDIN_STATUS, status);
        editor.commit();
    }
    public static boolean getUserLoggedInStatus(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_USER_LOGGEDIN_STATUS, false);
    }

    public static void setPrefUserFigerEnableStatus(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_USER_FIGER_ENABLE_STATUS, status);
        editor.commit();
    }
    public static boolean getPrefUserFigerEnableStatus (Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_USER_FIGER_ENABLE_STATUS, false);
    }

    public static void setIsMangement(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(IS_MANGEMENT, status);
        editor.commit();
    }
    public static boolean getIsMangement (Context ctx) {
        return getSharedPreferences(ctx).getBoolean(IS_MANGEMENT, false);
    }
    public static void setIsTenat(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(IS_TENAT, status);
        editor.commit();
    }
    public static boolean getIsTenat (Context ctx) {
        return getSharedPreferences(ctx).getBoolean(IS_TENAT, false);
    }
    public static void setIsFamily(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(IS_Family, status);
        editor.commit();
    }
    public static boolean getIsFamily (Context ctx) {
        return getSharedPreferences(ctx).getBoolean(IS_Family, false);
    }

    public static void clearLoggedInEmailAddress(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_LOGGEDIN_USER_EMAIL);
        editor.remove(PREF_USER_LOGGEDIN_STATUS);
        editor.remove(PREF_USER_NAME);
        editor.remove(PREF_USER_IMAGE_URL);
        editor.remove(PREF_USER_TOKEN);
        editor.commit();
    }

}
