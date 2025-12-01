package com.HS.Topcity.Interfaces;

import com.HS.Topcity.ApiModels.Complains.UserComplainsResponse;
import com.HS.Topcity.ApiModels.ComplaintCreate.ComplaintCreateResponse;
import com.HS.Topcity.ApiModels.ComplaintDetail.ComplaintDetailRequest;
import com.HS.Topcity.ApiModels.ComplaintDetail.ComplaintDetailResponse;
import com.HS.Topcity.ApiModels.ComplaintType.ComplaintTypeResponse;
import com.HS.Topcity.ApiModels.ComplaintWithDraw.ComplaintWithDrawRequest;
import com.HS.Topcity.ApiModels.ComplaintWithDraw.ComplaintWithDrawResponse;
import com.HS.Topcity.ApiModels.ContactUs.ContactUsResponse;
import com.HS.Topcity.ApiModels.CreateSharedAccounts.CreateSharedAccountsRequest;
import com.HS.Topcity.ApiModels.CreateSharedAccounts.CreateSharedAccountsResponse;
import com.HS.Topcity.ApiModels.DashboardGraphValue.DashboardGraphValueRequest;
import com.HS.Topcity.ApiModels.DashboardGraphValue.DashboardGraphValueResponse;
import com.HS.Topcity.ApiModels.Development.DevelopmentResponse;
import com.HS.Topcity.ApiModels.Event.EventResponse;
import com.HS.Topcity.ApiModels.EventDetails.EventDetailsResponse;
import com.HS.Topcity.ApiModels.Features.FeaturesResponse;
import com.HS.Topcity.ApiModels.FeedBack.FeedbackRequest;
import com.HS.Topcity.ApiModels.FeedBack.FeedbackResponse;
import com.HS.Topcity.ApiModels.FingerprintLogin.FingerprintLoginRequest;
import com.HS.Topcity.ApiModels.FingerprintLogin.FingerprintLoginResponse;
import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordRequest;
import com.HS.Topcity.ApiModels.ForgetPassword.ForgetPasswordResponse;
import com.HS.Topcity.ApiModels.GuestNotification.GuestNotificationRequest;
import com.HS.Topcity.ApiModels.GuestNotification.GuestNotificationResponse;
import com.HS.Topcity.ApiModels.Login.LoginRequest;
import com.HS.Topcity.ApiModels.Login.LoginResponse;
import com.HS.Topcity.ApiModels.Logout.LogoutRequest;
import com.HS.Topcity.ApiModels.Logout.LogoutResponse;
import com.HS.Topcity.ApiModels.ManagementDashboardCount.ManagementDashboardCountResponse;
import com.HS.Topcity.ApiModels.Masterplans.MasterplansResponse;
import com.HS.Topcity.ApiModels.NewsAndAnnouncements.NewsAndAnnouncementsResponse;
import com.HS.Topcity.ApiModels.Notifiation.NotificationRequest;
import com.HS.Topcity.ApiModels.Notifiation.NotificationResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.ApiModels.PaymentDetail.PaymentDetailRequest;
import com.HS.Topcity.ApiModels.PaymentDetail.PaymentDetailResponse;
import com.HS.Topcity.ApiModels.ProfileSetting.UserInfoDetailsResponse;
import com.HS.Topcity.ApiModels.PropertyDetail.PropertyDetailsRequest;
import com.HS.Topcity.ApiModels.PropertyDetail.PropertyDetailsResponse;
import com.HS.Topcity.ApiModels.PropertySharedAccountDetail.PropertySharedAccountDetailsRequest;
import com.HS.Topcity.ApiModels.PropertySharedAccountDetail.PropertySharedAccountDetailsResponse;
import com.HS.Topcity.ApiModels.PropertyimageUpload.PropertyimageUploadResponse;
import com.HS.Topcity.ApiModels.RecentlyAddedComplaint.RecentlyAddedComplaintResponse;
import com.HS.Topcity.ApiModels.SubFeature.SubFeatureRequest;
import com.HS.Topcity.ApiModels.SubFeature.SubFeatureResponse;
import com.HS.Topcity.ApiModels.UpdateFCMToken.UpdateFcmTokenRequest;
import com.HS.Topcity.ApiModels.UpdateFCMToken.UpdateFcmTokenResponse;
import com.HS.Topcity.ApiModels.UpdatePass.UpdatePassRequest;
import com.HS.Topcity.ApiModels.UpdatePass.UpdatePassResponse;
import com.HS.Topcity.ApiModels.UpdateUserProfile.UpdateUserInfoRequest;
import com.HS.Topcity.ApiModels.UpdateUserProfile.UpdateUserInfoResponse;
import com.HS.Topcity.ApiModels.UploadProfileImage.UploadProfileImageResponse;
import com.HS.Topcity.ApiModels.UserNewPassword.UpdateUserPasswordRequest;
import com.HS.Topcity.ApiModels.UserNewPassword.UpdateUserPasswordResponse;
import com.HS.Topcity.ApiModels.UserProperties.UserPropertiesResponse;
import com.HS.Topcity.ApiModels.UserRegister.RegisterRequest;
import com.HS.Topcity.ApiModels.UserRegister.RegisterResponse;
import com.HS.Topcity.ApiModels.UserSharedAccountDetail.UserSharedAccountDetalRequest;
import com.HS.Topcity.ApiModels.UserSharedAccountDetail.UserSharedAccountDetalResponse;
import com.HS.Topcity.ApiModels.UserSharedAccountRemove.UserSharedAccountRemoveRequest;
import com.HS.Topcity.ApiModels.UserSharedAccountRemove.UserSharedAccountRemoveResponse;
import com.HS.Topcity.ApiModels.UserSharedAccounts.UserSharedAccountsResponse;
import com.HS.Topcity.ApiModels.ValidateRegister.ValidateRegisterRequest;
import com.HS.Topcity.ApiModels.ValidateRegister.ValidateRegisterResponse;
import com.HS.Topcity.ApiModels.VerifyOTP.VerifyOtpRequest;
import com.HS.Topcity.ApiModels.VerifyOTP.VerifyOtpResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    // sign api
    @POST("api/User/LogIn")
    Call<LoginResponse> LOGIN_RESPONSE_CALL(@Body LoginRequest loginRequestModel);

    // cnic register
    @POST("api/User/ValidateRegister")
    Call<ValidateRegisterResponse> validateRegisterResponse(@Body ValidateRegisterRequest validateRegisterRequest);

    // signup api

    @POST("api/User/Register")
    Call<RegisterResponse> registerResponse(@Body RegisterRequest registerRequest);

    // verify otp

    @POST("api/User/VerifyOTP")
    Call<VerifyOtpResponse> verifyOtpResponse(@Body VerifyOtpRequest verifyOtpRequest);

    // forget pass

    @POST("api/User/ForgetPassword")
    Call<ForgetPasswordResponse> forgetPasswordResponse (@Body ForgetPasswordRequest forgetPasswordRequest);


    // user profile details
    @GET("api/UserInfo/UserInfoDetails")
    Call<UserInfoDetailsResponse> userInfoDetailsResponse (@Header("Authorization") String authToken);

    // userinfo update
    @POST("api/UserInfo/UpdateUserInfo")
    Call<UpdateUserInfoResponse> updateUserInfoResponse(@Header("Authorization") String authToken,@Body UpdateUserInfoRequest updateUserInfoRequest);


    // Update password

    @POST("api/User/UpdateUserPassword")
    Call<UpdateUserPasswordResponse> updateUserPasswordResponse (@Body UpdateUserPasswordRequest updateUserPasswordRequest);

    // new and announcement
    @GET("api/AppLandingPage/NewsAndAnnouncements")
    Call<NewsAndAnnouncementsResponse> newsAndAnnouncementResponse(@Header("Authorization") String authToken);

    // property
    @GET("api/AppLandingPage/UserProperties")
    Call<UserPropertiesResponse> userPropertiesResponse(@Header("Authorization") String authToken);

    // complains
    @GET("api/AppLandingPage/UserComplains")
    Call<UserComplainsResponse> userComplainsResponse(@Header("Authorization") String authToken);

    // events
    @GET("api/AppLandingPage/Events")
    Call<EventResponse> eventResponse(@Header("Authorization") String authToken);

    // events details
    @GET("api/AppLandingPage/EventsDetails")
    Call<EventDetailsResponse> eventDetailResponse(@Header("Authorization") String authToken);

    // features item
    @GET("api/Feature/Features")
    Call<FeaturesResponse> featuresResponse(@Header("Authorization") String authToken);

    // master plan
  @GET("api/Community/MasterPlan")
    Call<MasterplansResponse> masterplanResponse(@Header("Authorization") String authToken);

//  notification

    @POST("api/Notifications/NotificationsDetails")
    Call<NotificationResponse>  notificationResponse(@Header("Authorization") String authToken,@Body NotificationRequest notificationRequest);

    // create Share Account
    @POST("api/UserInfo/CreateSharedAccounts")
    Call<CreateSharedAccountsResponse>  createSharedAccountsResponse(@Header("Authorization") String authToken, @Body CreateSharedAccountsRequest createSharedAccountsRequest);

    // user shared account list
    @GET("api/UserInfo/UserSharedAccounts")
    Call<UserSharedAccountsResponse> userSharedAccountsResponse(@Header("Authorization") String authToken);


   // Property/PropertyDetails
    @POST("api/Property/PropertyDetails")
    Call<PropertyDetailsResponse>  propertyDetailsResponse(@Header("Authorization") String authToken, @Body PropertyDetailsRequest propertyDetailsRequest);

    // Property/PropertySharedAccountDetails
    @POST("api/Property/PropertySharedAccountDetails")
    Call<PropertySharedAccountDetailsResponse>  propertySharedAccountDetailsResponse(@Header("Authorization") String authToken, @Body PropertySharedAccountDetailsRequest propertySharedAccountDetailsRequest);

    // feature / sub feature
    @POST("api/Feature/SubFeatures")
    Call<SubFeatureResponse>  SUB_FEATURE_RESPONSE_CALL(@Header("Authorization") String authToken, @Body SubFeatureRequest subFeatureRequest);

    // api/Complaint/ComplaintDetails
    @POST("api/Complaint/ComplaintDetails")
    Call<ComplaintDetailResponse>  complaintDetailResponse(@Header("Authorization") String authToken, @Body ComplaintDetailRequest complaintDetailRequest);

    // api/Complaint/WithdrawComplaint
    @POST("api/Complaint/WithdrawComplaint")
    Call<ComplaintWithDrawResponse>  COMPLAINT_WITH_DRAW_RESPONSE_CALL(@Header("Authorization") String authToken, @Body ComplaintWithDrawRequest complaintWithDrawRequest);

    // /api/Complaint/CreateComplaint
    @Multipart
    @POST("api/Complaint/CreateComplaint")
    Call<ComplaintCreateResponse>  COMPLAINT_CREATE_RESPONSE_CALL(@Header("Authorization") String authToken, @Part("ComplaintTypeId") RequestBody ComplaintTypeId, @Part("PropertyId")RequestBody PropertyId, @Part("ComplaintSubTypeId")RequestBody ComplaintSubTypeId, @Part("ComplaintSubject")RequestBody ComplaintSubject, @Part("ComplaintDetails")RequestBody ComplaintDetails, @Part("ImageURLs")RequestBody image);

    //  Call<ComplaintCreateResponse>  COMPLAINT_CREATE_RESPONSE_CALL(@Header("Authorization") String authToken, @Part("ComplaintTypeId")MultipartBody.Part ComplaintTypeId, @Part("PropertyId")MultipartBody.Part PropertyId, @Part("ComplaintSubTypeId")MultipartBody.Part ComplaintSubTypeId, @Part("ComplaintSubject")MultipartBody.Part ComplaintSubject, @Part("ComplaintDetails")MultipartBody.Part ComplaintDetails, @Part MultipartBody.Part image);

    // api/UserInfo/SharedAccountDetails
    @POST("api/UserInfo/SharedAccountDetails")
    Call<UserSharedAccountDetalResponse>  user_shared_account_detail_response(@Header("Authorization") String authToken, @Body UserSharedAccountDetalRequest userSharedAccountDetalRequest);

    // api/UserInfo/SharedAccountDetails
    @POST("api/UserInfo/SharedAccountRemove")
    Call<UserSharedAccountRemoveResponse> userSharedAccountRemoveResponse (@Header("Authorization") String authToken, @Body UserSharedAccountRemoveRequest userSharedAccountRemoveRequest);

    // api/Complaint/ComplaintType
    @GET("api/Complaint/ComplaintType")
    Call<ComplaintTypeResponse> complaintTypeResponse(@Header("Authorization") String authToken);

    // api/Complaint/RecentlyAddedComplaints
    @GET("api/Complaint/RecentlyAddedComplaints")
    Call<RecentlyAddedComplaintResponse> recentlyAddedComplaintResponse(@Header("Authorization") String authToken);

    // api/ContactUs/ContactUs
    @GET("api/ContactUs/ContactUs")
    Call<ContactUsResponse> contactUsResponse (@Header("Authorization") String authToken);

    // api/UserInfo/SharedAccountDetails
    @POST("api/ContactUs/FeedBack")
    Call<FeedbackResponse> feedbackResponse (@Header("Authorization") String authToken, @Body FeedbackRequest feedbackRequest);

    // api/Development/Development
    @GET("api/Development/Development")
    Call<DevelopmentResponse> developmentResponse (@Header("Authorization") String authToken);

    // /api/Complaint/CreateComplaint
//    @Multipart
//    @POST("api/UserInfo/UpdateProfileImage")
//    Call<UploadProfileImageResponse> uploadProfileImageResponse(@Header("Authorization") String authToken, @Part MultipartBody.Part image);

    // /api/profie image
    @Multipart
    @POST("api/UserInfo/UpdateProfileImage")
    Call<UploadProfileImageResponse> uploadProfileImageResponse(@Header("Authorization") String authToken, @Part("ImageURL")RequestBody ImageURL);

    // notificationList
    @GET("api/Notifications/NotificationsList")
    Call<NotificationListResponse> notificationListResponse(@Header("Authorization") String authToken);

// api/User/UpdateFCMToken

    @POST("api/User/UpdateFCMToken")
    Call<UpdateFcmTokenResponse> updateFcmTokenResponse (@Header("Authorization") String authToken,@Body UpdateFcmTokenRequest updateFcmTokenRequest);

  //  / api/User/UpdateFCMToken

    @POST("api/User/LoginForFingerPrint")
    Call<FingerprintLoginResponse> fingerprintLoginResponse (@Body FingerprintLoginRequest fingerprintLoginRequest);

        // /api/propertyimages
    @Multipart
    @POST("api/Property/PropertyImage")
    Call<PropertyimageUploadResponse> propertyimageUploadResponse(@Header("Authorization") String authToken, @Part("PropertyId") RequestBody PropertyId, @Part("ImageURLs") RequestBody jsonArray);

    // api/User/GuestUser

    @POST("api/User/GuestUser")
    Call<GuestNotificationResponse> guestNotificationResponse (@Body GuestNotificationRequest guestNotificationRequest);
    // Update password

    @POST("api/UserInfo/UpdatePassword")
    Call<UpdatePassResponse> updatePassResponse (@Header("Authorization") String authToken,@Body UpdatePassRequest updatePassRequest);

    //payment Detail recipe

    @POST("api/ContactUs/PaymentDetail")
    Call<PaymentDetailResponse> paymentDetailResponse (@Header("Authorization") String authToken, @Body PaymentDetailRequest paymentDetailRequest);

    //logout

    @POST("api/User/Logout")
    Call<LogoutResponse> logoutResponse (@Header("Authorization") String authToken, @Body LogoutRequest logoutRequest);

    // api/ManagementDashbaord/UserandComplainsCount
    @GET("api/ManagementDashbaord/UserandComplainsCount")
    Call<ManagementDashboardCountResponse> managementDashboardCountResponse  (@Header("Authorization") String authToken);


// api/GraphValues

    @POST("api/ManagementDashbaord/GraphValues")
    Call<DashboardGraphValueResponse> dashboardGraphValueResponse (@Body DashboardGraphValueRequest dashboardGraphValueRequest);

}
