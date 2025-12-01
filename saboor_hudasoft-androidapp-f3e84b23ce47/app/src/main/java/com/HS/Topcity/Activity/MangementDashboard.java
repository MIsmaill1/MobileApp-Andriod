package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Adapters.DashboardCountAdapter;
import com.HS.Topcity.Adapters.ManageDashboardItemAdapter;
import com.HS.Topcity.ApiModels.DashboardGraphValue.DashboardGraphValueRequest;
import com.HS.Topcity.ApiModels.DashboardGraphValue.DashboardGraphValueResponse;
import com.HS.Topcity.ApiModels.ManagementDashboardCount.ManagementDashboardCountResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.Models.DashboardCountModel;
import com.HS.Topcity.Models.ManageDashboardModel;
import com.HS.Topcity.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangementDashboard extends AppCompatActivity {
    TextView Noti_count;
    LinearLayout Noti_bg;
    ImageView  user, notification;
    RecyclerView recyclerView,status_list;
    TextView total_user,total_Complaint,today,week,month,date;
    LinearLayout back,today_bg,week_bg,month_bg;
    ArrayList<ManageDashboardModel> manageDashboardModel;
    ArrayList<DashboardCountModel> dashboardCountModels;
    BarChart barChart;
    public static int complaintid_Dash = 0;
    ArrayList<BarEntry> entries;
     ArrayList<String> xAxisLabel = new ArrayList<>();
    List<IBarDataSet> bars = new ArrayList<IBarDataSet>();
    int sort_id = 3 ;

    DashboardGraphValueRequest dashboardGraphValueRequest = new DashboardGraphValueRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mangement_dashboard );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
    //    getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        // ids
        id();
        // notification and user or back to privous screen funtionality
        noti_user_and_back();
        // Attach Adapter
        attach_adapter();
        // bar chart with api
        BarChart_with_api();
        // manage dashboard count api
        ManageDashboardCount();
        // notification count
        // notication count api
        Notification_list_api();
        Noti_count.setText( notication_count );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }

        // button
        if(sort_id == 1){
            today_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
            today.setTextColor(getColor( R.color.black  ) );
        }
       else if(sort_id == 2){
            week_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
            week.setTextColor(getColor( R.color.black  ) );
        }
       else if(sort_id == 3){
            month_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
            month.setTextColor(getColor( R.color.black  ) );
        }

        today_bg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_id = 1;
                today_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
                week_bg.setBackgroundColor( getColor( R.color.white )  );
                month_bg.setBackgroundColor( getColor( R.color.white )  );
                month.setTextColor(getColor( R.color.month_text_color  ) );
                today.setTextColor(getColor( R.color.black  ) );
                week.setTextColor(getColor( R.color.month_text_color  ) );

                BarChart_with_api();
            }
        } );
        week_bg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_id = 2;
                today_bg.setBackgroundColor( getColor( R.color.white )  );
                week_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
                month_bg.setBackgroundColor( getColor( R.color.white )  );
                month.setTextColor(getColor( R.color.month_text_color  ) );
                today.setTextColor(getColor( R.color.month_text_color  ) );
                week.setTextColor(getColor( R.color.black  ) );

                BarChart_with_api();
            }
        } );
        month_bg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_id = 3;
                today_bg.setBackgroundColor( getColor( R.color.white )  );
                week_bg.setBackgroundColor( getColor( R.color.white )  );
                month_bg.setBackground( getDrawable( R.drawable.button_outline_blue  ) );
                month.setTextColor(getColor( R.color.black  ) );
                today.setTextColor(getColor( R.color.month_text_color  ) );
                week.setTextColor(getColor( R.color.month_text_color  ) );

                BarChart_with_api();
            }
        } );


    }
    private void id() {
        back = findViewById( R.id.back_to_subComplaint );
        user = findViewById( R.id.user_icon_SubComplaint );
        notification = findViewById( R.id.notification_SubComplaint );
        Noti_count = findViewById( R.id.notification_Count );
        Noti_bg = findViewById( R.id.notification_Count_bg );
        recyclerView = findViewById( R.id.mangeDashboard_item );
        total_Complaint = findViewById( R.id.total_complaint );
        total_user = findViewById( R.id.total_user_no );
        today = findViewById( R.id.today_text );
        week = findViewById( R.id.week_text );
        month = findViewById( R.id.month_text );
        today_bg = findViewById( R.id.today );
        week_bg = findViewById( R.id.week );
        month_bg = findViewById( R.id.month );
        date = findViewById( R.id.date );
        barChart = (BarChart) findViewById(R.id.barchart);
        status_list =  findViewById( R.id.mangeDashboard_item_count );
    }
    public  void BarChart_with_api(){


        ProgressDialog progress = new ProgressDialog(getApplicationContext());

        ApiInterface apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
      //  progress.show();
        dashboardGraphValueRequest.setFromWhen( sort_id );
        dashboardGraphValueRequest.setComplainTypeId(complaintid_Dash );
        Call<DashboardGraphValueResponse> dashboardGraphValueResponseCall = apiInterface.dashboardGraphValueResponse(dashboardGraphValueRequest);
        dashboardGraphValueResponseCall.enqueue(new Callback<DashboardGraphValueResponse>() {
            @Override
            public void onResponse(Call<DashboardGraphValueResponse> call, Response<DashboardGraphValueResponse> response) {
                DashboardGraphValueResponse user = response.body();


                if (user != null) {

                    if(user.graphResponseValues != null){
                        date.setText( user.graphResponseValues.getDateString() );
                           if(user.graphResponseValues.graphValues != null) {
                               xAxisLabel =  new ArrayList<>();
                               bars = new ArrayList<>();
                               for (int i = 0; i < user.graphResponseValues.graphValues.size(); i++) {
                                   entries = new ArrayList<>();
                                String a =   String.valueOf( user.graphResponseValues.graphValues.get( i ).count );
                                   entries.add( new BarEntry(8 + i , user.graphResponseValues.graphValues.get( i ).count));
                                   xAxisLabel.add( user.graphResponseValues.graphValues.get( i ).title);
                                   BarDataSet dataset = new BarDataSet(entries,user.graphResponseValues.graphValues.get( i ).title );
                                   dataset.setValueTextSize(13f);
                             //      dataset.setLabel( user.graphResponseValues.graphValues.get( i ).title );
                                   dataset.setColor( Color.parseColor( "#" + user.graphResponseValues.graphValues.get( i ).getColorCode()) );
                                   bars.add(dataset);
                               }
                               BarData data = new BarData( bars);
                          //     ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);
                               XAxis xAxis = barChart.getXAxis();

                               xAxis.setDrawGridLines(false);
                           //    xAxis.setValueFormatter(xAxisFormatter);
                               barChart.getXAxis().setPosition( XAxis.XAxisPosition.BOTTOM );
                               barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
                               barChart.getDescription().setEnabled(false);
                               barChart.setData( data );
                            //   BarDataSet bardataset = new BarDataSet(entries, "Complaint");

                             //  BarData data = new BarData( bardataset);
                             //  barChart.setData(data); // set the data and list of labels into chart
//                               int[] PASTEL_COLOR = {
//                                       getColor( R.color.red ),getColor( R.color.dark_green ),getColor( R.color.bg_light_blue_color ),getColor( R.color.bg_mhendi_color )
//                               };
//                               bardataset.setBarBorderColor( R.color.bg_light_blue_color );
//                               bardataset.setColors( PASTEL_COLOR);
//                               bardataset.setValueTextSize(13f);
//                               barChart.getDescription().setEnabled(false);
//                               bardataset.setLabel( "Top-City Complaint Bar" );
//                               barChart.setBorderColor(  R.color.bg_light_blue_color );
//                               barChart.setGridBackgroundColor(  R.color.bg_light_blue_color );
                               barChart.animateY(2000);
                           }
                    }

                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<DashboardGraphValueResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });


//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(8f, 0));
//        entries.add(new BarEntry(9f, 0));
//        entries.add(new BarEntry(10f, 70));
//        entries.add(new BarEntry(11f, 122));
//        entries.add(new BarEntry(12f, 550));
//        entries.add(new BarEntry(13f, 422));
//        entries.add(new BarEntry(14f, 0));
//        entries.add(new BarEntry(15f, 0));
//        BarDataSet bardataset = new BarDataSet(entries, "Complaint");
//
//        BarData data = new BarData( bardataset);
//        barChart.setData(data); // set the data and list of labels into chart
//        int[] PASTEL_COLOR = {
//                getColor( R.color.red ),getColor( R.color.dark_green ), getColor( R.color.red ),getColor( R.color.dark_green ),getColor( R.color.bg_light_blue_color ),getColor( R.color.bg_mhendi_color )
//        };
//        bardataset.setBarBorderColor( R.color.bg_light_blue_color );
//        bardataset.setColors( PASTEL_COLOR);
//        bardataset.setValueTextSize(13f);
//        barChart.getDescription().setEnabled(false);
//        bardataset.setLabel( "Top-City Complaint Bar" );
//        barChart.setBorderColor(  R.color.bg_light_blue_color );
//        barChart.setGridBackgroundColor(  R.color.bg_light_blue_color );
//        barChart.animateY(5000);
    }
    private void ManageDashboardCount(){

        String token = PreferenceData.getPrefUserToken(getApplicationContext());
        ProgressDialog progress = new ProgressDialog(MangementDashboard.this);

     ApiInterface apiInterface = ApiUtils.postSignUpService();

        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
           progress.show();
        Call<ManagementDashboardCountResponse> managementDashboardCountResponse = apiInterface.managementDashboardCountResponse(token);
        managementDashboardCountResponse.enqueue(new Callback<ManagementDashboardCountResponse>() {
            @Override
            public void onResponse(Call<ManagementDashboardCountResponse> call, Response<ManagementDashboardCountResponse> response) {
                ManagementDashboardCountResponse user = response.body();


                if (user != null) {

                   dashboardCountModels = new ArrayList<>();
                    if (user.managementResponeModel != null) {

                        total_Complaint.setText( String.valueOf(user.managementResponeModel.complains  ) );
                        total_user.setText( String.valueOf(user.managementResponeModel.users  ) );
                        if(user.managementResponeModel.userandComplains != null){
                            for (int i = 0; i < user.managementResponeModel.userandComplains.size(); i++) {
                                DashboardCountModel model = new DashboardCountModel();
                                model.setCount( user.managementResponeModel.userandComplains.get( i ).getCount() );
                                model.setTitle( user.managementResponeModel.userandComplains.get( i ).getTitle() );
                                model.setColorCode( user.managementResponeModel.userandComplains.get( i ).getColorCode() );
                                dashboardCountModels.add( model );
                            }

                           DashboardCountAdapter dashboardCountAdapter = new DashboardCountAdapter( getApplicationContext(), dashboardCountModels,R.layout.dashboard_count_layout );

                            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
                            status_list.setLayoutManager( linearLayoutManager2 );
                            status_list.setNestedScrollingEnabled(false);
                            status_list.setAdapter( dashboardCountAdapter );
                            status_list.setLayoutManager( new GridLayoutManager( getBaseContext() , 2 ) );
                        }

                    }
                    progress.dismiss();
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<ManagementDashboardCountResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });

    }

    private void attach_adapter(){
        manageDashboardModel = new ArrayList<>();
        manageDashboardModel.add( new ManageDashboardModel(  0,R.mipmap.dash_white,R.mipmap.dash_blue,R.string.all) );
        manageDashboardModel.add( new ManageDashboardModel(  1,R.mipmap.gernal_white_icon,R.mipmap.gernal_blue_icon,R.string.General) );
        manageDashboardModel.add( new ManageDashboardModel(  2,R.mipmap.electricity_white_icon,R.mipmap.electricity_form_icon,R.string.Electricity) );
        manageDashboardModel.add( new ManageDashboardModel(  3,R.mipmap.maintance_icon_white,R.mipmap.maintance_icon_blue,R.string.Maintenance) );
        manageDashboardModel.add( new ManageDashboardModel(  4,R.mipmap.water_white_icon,R.mipmap.water_form_icon,R.string.Water) );
       // ManageDashboardItemAdapter manageDashboardItemAdapter = new ManageDashboardItemAdapter( getApplicationContext(),manageDashboardModel,R.layout.manage_dashboard_item_layout, ManageDashboardItemAdapter.OnItemClickListener );
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.HORIZONTAL,false );
        recyclerView.setLayoutManager( linearLayoutManager2 );
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter( new ManageDashboardItemAdapter( getApplicationContext(),manageDashboardModel,R.layout.manage_dashboard_item_layout,new ManageDashboardItemAdapter.OnItemClickListener() {
            @Override public void onItemClick(ManageDashboardModel item) {

                BarChart_with_api();
            }
        }));
    }
    private void noti_user_and_back() {
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( getApplicationContext(), Notification.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( getApplicationContext(), UserProfile.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );

    }
   public class DayAxisValueFormatter extends ValueFormatter {
        private final BarLineChartBase<?> chart;
        public DayAxisValueFormatter(BarLineChartBase<?> chart) {
            this.chart = chart;
        }
        @Override
        public String getFormattedValue(float value) {
            NumberFormat myFormat = NumberFormat.getInstance();
            Double d =  Double.parseDouble( String.valueOf( value ) );
            return xAxisLabel.get( Integer.parseInt(myFormat.format( d  )) - 2);
        }
    }

    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(getApplicationContext());

        ApiInterface apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {


                    if (user.notificationsList != null) {

                        notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );
                        if(Noti_count.getText().toString().equals( "0" )){
                            Noti_bg.setVisibility( View.GONE );
                        }

                    }
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        Notification_list_api();
        super.onResume();

    }
}