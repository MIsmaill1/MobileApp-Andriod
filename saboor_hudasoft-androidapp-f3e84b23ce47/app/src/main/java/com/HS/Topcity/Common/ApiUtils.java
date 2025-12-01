package com.HS.Topcity.Common;


import com.HS.Topcity.Interfaces.ApiInterface;

public class ApiUtils {

  // public static final String BASE_URL = "http://203.99.63.178:9090/";
//  public static final String BASE_URL = "http://125.209.64.253:8070/";
//    public static final String BASE_URL = "http://125.209.64.253:8060/";
   public static final String BASE_URL = "https://topcity-1-api.azurewebsites.net/" ;
    public static ApiInterface postSignUpService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }

}