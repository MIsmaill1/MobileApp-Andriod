package com.HS.Topcity.Activity;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.check_menu_open;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Activity.ui.home.HomeFragment;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.CustomClasses.MenuBar.DrawerAdapter;
import com.HS.Topcity.CustomClasses.MenuBar.DrawerItem;
import com.HS.Topcity.CustomClasses.MenuBar.SimpleItem;
import com.HS.Topcity.Fragsment.Features;
import com.HS.Topcity.Fragsment.MasterPlan;
import com.HS.Topcity.Fragsment.ServicesDesk;
import com.HS.Topcity.Fragsment.UserSharedAccount;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener  {
    private static final int POS_develop = 0;
    private static final int payment = 1;
    private static final int proce = 2;
    private static final int share = 3;
    private static final int down = 4;
    private static final int faqs = 5;
    private static final int contact = 6;

    BottomNavigationView navView;
    private String[] screenTitles;
    private int[]  screenIcons = {R.mipmap.developmentactivities_icon,R.mipmap.payment_detail_icon,R.mipmap.procedure_icon,
    R.mipmap.community_services,  R.mipmap.downloads_icon,  R.mipmap.faqs_icon,  R.mipmap.contact_icon1,};
    private SlidingRootNav slidingRootNav;
  //  private ActivityDashboardBinding binding;
    private static FragmentManager fragmentManager;
    public static Fragment selectedFragment;
    public static BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;

    String check_frag ;
    String chek_pos="home";
    ImageView facebook,insta,Twiter,youtube;
    LinearLayout backs;
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

   //     binding = ActivityDashboardBinding.inflate( getLayoutInflater() );
       // setContentView( binding.getRoot() );
        setContentView( R.layout.activity_dashboard );
        // hide action bar
        getSupportActionBar().hide();
        // app window full screeen
     //   getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        // ids
        ids();

        ShortcutBadger.with(getApplicationContext()).count( 5 );
        // notifciation count api
        Notification_list_api();

       // ShortcutBadger.a(getApplicationContext()).count(badgeCount); //for 1.1.3


        navView.setOnNavigationItemSelectedListener( navListener );

        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        // floating button funtionality
        floting_btn_Action();




        toolbar.setNavigationIcon(R.mipmap.menu);
        toolbar.setCollapseIcon( R.mipmap.menu );


        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.menu));


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withGravity( SlideGravity.RIGHT)
                .withMenuLocked( true )
                .withMenuLayout(R.layout.drawable_menu)
                .inject();


//        screenIcons = loadScreenIcons();

        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter( Arrays.asList(
                createItemFor(POS_develop).setChecked(true),
                createItemFor(payment),
                createItemFor(proce),
                createItemFor(share),
                createItemFor(down),
        createItemFor(faqs),
        createItemFor(contact)));
        adapter.setListener(this);





        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_menu_open != true){
                    check_menu_open = true;
                    if(chek_pos.equals( "home" )){
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else if(chek_pos.equals( "shared" )){
                        selectedFragment = new UserSharedAccount();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else   if(chek_pos.equals( "masterPlan" )){
                        selectedFragment = new MasterPlan();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else  if(chek_pos.equals( "feature" )){
                        selectedFragment = new Features();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else  if(chek_pos.equals( "Servicedesk" )){
                        selectedFragment = new ServicesDesk();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    slidingRootNav.openMenu();
                 //   slidingRootNav.setMenuLocked(true);
                }
                else {
                    check_menu_open = false;
                    if(chek_pos.equals( "home" )){
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else if(chek_pos.equals( "shared" )){
                        selectedFragment = new UserSharedAccount();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else   if(chek_pos.equals( "masterPlan" )){
                        selectedFragment = new MasterPlan();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else  if(chek_pos.equals( "feature" )){
                        selectedFragment = new Features();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    else  if(chek_pos.equals( "Servicedesk" )){
                        selectedFragment = new ServicesDesk();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                    slidingRootNav.closeMenu();


                }


            }
        } );

        // attach recycle view adapter menu items
        list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);




        // facebook or twitter and instagram or youtube funtionality
        facebook = findViewById( R.id. fb_icon);
        insta = findViewById( R.id. insta_icon);
        Twiter = findViewById( R.id. twitter_icon);
        youtube = findViewById( R.id. youtube_icon);
       backs = findViewById( R.id.close_menu);
      RelativeLayout back = findViewById( R.id.back);

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menu_closed();

            }
        } );
        backs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menu_closed();

            }
        } );
        facebook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/topcity1/"));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            }
        } );
        insta.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/thetopcity1?utm_medium=copy_link"));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            }
        } );
        Twiter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/thetopcity1?s=21"));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            }
        } );
        youtube.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCX1r3kC2TfNEykqf2-pok6w"));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            }
        } );



       // adapter.setSelected(POS_develop);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //DEFAULT
//        AppBarConfiguration appBarConfiguration;
//
//             appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.id.navigation_home, R.id.nav_features, R.id.nav_ServiceDesk,R.id.nav_MasterPlan,R.id.nav_More )
//                    .build();
//        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment_activity_dashboard );
//        NavigationUI.setupActionBarWithNavController( this, navController, appBarConfiguration );
//        NavigationUI.setupWithNavController( binding.navView, navController );
        //    END


//        navView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        (new HomeFragment(), FRAGMENT_HOME);
//                        return true;
//                    case R.id.nav_features:
//                        viewFragment(new OneFragment(), FRAGMENT_OTHER);
//                        return true;
//                    case R.id.nav_ServiceDesk:
//                        viewFragment(new TwoFragment(), FRAGMENT_OTHER);
//                        return true;
//                }
//                return false;
//            }
//        } );
    }
    private void ids(){
        navView = findViewById( R.id.nav_view );
        floatingActionButton = findViewById( R.id.floatingMap );
        toolbar = findViewById(R.id.toolbar);

    }
    private void floting_btn_Action(){
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new ServicesDesk();
                navView.setSelectedItemId( R.id.nav_ServiceDesk );
                getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

            }
        } );

    }



    @Override
    public void onItemSelected(int position) {
//        if (position == POS_LOGOUT) {
//            finish();
//        }
        slidingRootNav.closeMenu();
//        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
//        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
//                .withIconTint(color(R.color.cardview_dark_background))
                .withTextTint(color(R.color.black))
//                .withSelectedIconTint(color(R.color.black))
                .withSelectedTextTint(color(R.color.black));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            // Fragment selectedFragment;


                selectedFragment = new HomeFragment();

//            if(PreferenceData.getIsMangement( getApplicationContext() ) == false){
//                if(item.getItemId() == )
//            }
            switch (item.getItemId()){

                case R.id.navigation_home:
                    check_frag = "home";
                    chek_pos = "home";
                    slidingRootNav.closeMenu();
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.nav_features:
                    check_frag = "feature";
                    chek_pos = "feature";
                    slidingRootNav.closeMenu();
                    selectedFragment = new Features();
                    break;

                case R.id.nav_ServiceDesk:
                    check_frag = "Servicedesk";
                    chek_pos = "Servicedesk";
                    slidingRootNav.closeMenu();
                    selectedFragment = new ServicesDesk();
                    break;
                case R.id.nav_MasterPlan:
                    check_frag = "masterPlan";
                    chek_pos = "masterPlan";
                    slidingRootNav.closeMenu();
                    selectedFragment = new MasterPlan();
                    break;
                case R.id.nav_More:
                    check_frag = "Shared";
                    chek_pos = "Shared";
                    slidingRootNav.closeMenu();
                    selectedFragment = new UserSharedAccount();
                    break;

            }
           // NavController navController = Navigation.findNavController( Dashboard.this, R.id.nav_host_fragment_activity_dashboard );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

            return true;

        }
    };

    @Override
    public void onBackPressed() {
     //   super.onBackPressed();

        if(check_frag !=null && check_frag.equals( "Shared" ))  {
            selectedFragment = new HomeFragment();
            navView.setSelectedItemId( R.id.navigation_home );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "feature" ))  {
            selectedFragment = new HomeFragment();
            navView.setSelectedItemId( R.id.navigation_home );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "Servicedesk" ))  {
            selectedFragment = new HomeFragment();
            navView.setSelectedItemId( R.id.navigation_home );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "masterPlan" ))  {
            selectedFragment = new HomeFragment();
            navView.setSelectedItemId( R.id.navigation_home );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "home" ))  {
            finishAffinity();
        }
        else {
            finishAffinity();
        }


    }


    @Override
    protected void onResume() {

        menu_closed();
//        Notification_list_api();
//        if(notication_count != null && notication_count != "0"){
//
//            ShortcutBadger.isBadgeCounterSupported( getApplicationContext() );
//            ShortcutBadger.applyCount(getApplicationContext(), Integer.parseInt(  notication_count)); //for 1.1.4+
//        }
        super.onResume();

        if(check_frag !=null && check_frag.equals( "Shared" ))  {
            selectedFragment = new UserSharedAccount();
            navView.setSelectedItemId( R.id.nav_More );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "feature" ))  {
            selectedFragment = new Features();
            navView.setSelectedItemId( R.id.nav_features );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "Servicedesk" ))  {
            selectedFragment = new ServicesDesk();
            navView.setSelectedItemId( R.id.nav_ServiceDesk );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(check_frag !=null && check_frag.equals( "masterPlan" ))  {
            selectedFragment = new MasterPlan();
            navView.setSelectedItemId( R.id.nav_MasterPlan );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
//        else  if(check_frag !=null && check_frag.equals( "home" ))  {
//            selectedFragment = new HomeFragment();
//            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();
//
//        }
        else {
        super.onResume();
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
//                        if(notication_count != null && notication_count != "0"){
                     //   new IconBadgeNumManager().setIconBadgeNum(getApplication(),, Integer.parseInt(  notication_count));
                            //ShortcutBadger.isBadgeCounterSupported( getApplicationContext() );
                     //       ShortcutBadger.applyCount(getApplicationContext(),5); //for 1.1.4+


                      //  }

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
private void menu_closed(){
    check_menu_open = false;
    if(slidingRootNav.isMenuOpened() == true){
        slidingRootNav.closeMenu();

        if(chek_pos.equals( "home" )){
            selectedFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else if(chek_pos.equals( "shared" )){
            selectedFragment = new UserSharedAccount();
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else   if(chek_pos.equals( "masterPlan" )){
            selectedFragment = new MasterPlan();
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(chek_pos.equals( "feature" )){
            selectedFragment = new Features();
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(chek_pos.equals( "Servicedesk" )){
            selectedFragment = new ServicesDesk();
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();
        }
    }


}
}