package com.HS.Topcity.Activity.Guest.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.HS.Topcity.Activity.Contact;
import com.HS.Topcity.Activity.Development;
import com.HS.Topcity.Activity.Download;
import com.HS.Topcity.Activity.EventDetails;
import com.HS.Topcity.Activity.FandQ;
import com.HS.Topcity.Activity.Procedures;
import com.HS.Topcity.Activity.ui.home.HomeFragment;
import com.HS.Topcity.Fragsment.Features;
import com.HS.Topcity.Fragsment.MasterPlan;
import com.HS.Topcity.Fragsment.NewsAndAnnocementList;
import com.HS.Topcity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class GuestDashboard extends AppCompatActivity {

    public static boolean check_menu_open = false;
    public static String guest;
    private static FragmentManager fragmentManager;
    public static Fragment selectedFragment;
    public static BottomNavigationView bottomNavigationView;

    private static final int POS_develop = 0;
    private static final int proce = 1;
    private static final int event = 2;
    private static final int down = 3;
    private static final int faqs = 4;
    private static final int contact = 5;

    boolean check = false;
    String chek_pos ="home";
    LinearLayout back;

    private String[] screenTitles;
    private int[]  screenIcons = {R.mipmap.developmentactivities_icon,R.mipmap.payment_detail_icon,R.mipmap.procedure_icon,
            R.mipmap.community_services,  R.mipmap.downloads_icon,  R.mipmap.faqs_icon,  R.mipmap.contact_icon1,};
    private SlidingRootNav slidingRootNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_guest_dashboard );
        getSupportActionBar().hide();
  //      getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        BottomNavigationView navView = findViewById( R.id.nav_view1);
        back = findViewById( R.id.back_to_login2 );
        guest = getIntent().getStringExtra( "Guest" );
        navView.setOnNavigationItemSelectedListener( navListener );

        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        // back button funtionality
        if(guest.equals( "Guest" ) && chek_pos.equals( "home" )){
            back.setVisibility( View.VISIBLE );
        }
        else {
            back.setVisibility( View.GONE );
        }
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        // menu funtionality
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.menu);
        toolbar.setCollapseIcon( R.mipmap.menu );


        toolbar.setOverflowIcon( ContextCompat.getDrawable(this, R.mipmap.menu));



        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)

                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withGravity( SlideGravity.RIGHT)
                .withMenuLocked( true )
                .withMenuLayout(R.layout.guest_menu_layout)
                .inject();

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_menu_open != true){
                    check_menu_open = true;
                    if(chek_pos.equals( "home" )){
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                 else if(chek_pos.equals( "news" )){
                        selectedFragment = new NewsAndAnnocementList();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                  else   if(chek_pos.equals( "master" )){
                        selectedFragment = new MasterPlan();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }
                 else  if(chek_pos.equals( "feature" )){
                        selectedFragment = new Features();
                        getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

                    }

                    slidingRootNav.openMenu();
                    slidingRootNav.setMenuLocked(true);
                }
                else {
                    close_menu();

                }


            }
        } );




        LinearLayout deve,produce,event,down,contact,faqs;
        deve = findViewById( R.id.develop );
        produce = findViewById( R.id.produces );
        event = findViewById( R.id.event );
        down = findViewById( R.id.donwload );
        faqs = findViewById( R.id.faqs );
        contact = findViewById( R.id.contact );
        RelativeLayout back = findViewById( R.id.back);

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                close_menu();

            }
        } );
        deve.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Development.class );
                startActivity( a );
            }
        } );
        produce.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Procedures.class );
                startActivity( a );
            }
        } );
        event.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), EventDetails.class );
                startActivity( a );
            }
        } );
        down.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Download.class );
                startActivity( a );
            }
        } );
        faqs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), FandQ.class );
                startActivity( a );
            }
        } );
        contact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Contact.class );
                startActivity( a );
            }
        } );


//        screenIcons = loadScreenIcons();

//        screenTitles = loadScreenTitles();
//
//        DrawerAdapter adapter = new DrawerAdapter( Arrays.asList(
//                createItemFor(POS_develop).setChecked(true),
//                createItemFor(payment),
//                createItemFor(proce),
//                createItemFor(share),
//                createItemFor(down),
//                createItemFor(faqs),
//                createItemFor(contact)));
//        adapter.setListener(this);
//
//        RecyclerView list = findViewById(R.id.list);
//        list.setNestedScrollingEnabled(false);
//        list.setLayoutManager(new LinearLayoutManager(this));
//        list.setAdapter(adapter);

        ImageView facebook,insta,Twiter,youtube;
        facebook = findViewById( R.id. fb_icon);
        insta = findViewById( R.id. insta_icon);
        Twiter = findViewById( R.id. twitter_icon);
        youtube = findViewById( R.id. youtube_icon);

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
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            // Fragment selectedFragment;


            selectedFragment = new HomeFragment();
            switch (item.getItemId()){

                case R.id.navigation_home1:
                        chek_pos = "home";
                    back.setVisibility( View.VISIBLE );
                    check_menu_open = false;
                    slidingRootNav.closeMenu();
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.navigation_announce1:
                    chek_pos = "news";
                    back.setVisibility( View.GONE );
                    check_menu_open = false;
                    slidingRootNav.closeMenu();
                    selectedFragment = new NewsAndAnnocementList();
                    break;
                case R.id.navigation_master1:
                    chek_pos = "master";
                    back.setVisibility( View.GONE );
                    check_menu_open = false;
                    slidingRootNav.closeMenu();
                    selectedFragment = new MasterPlan();
                    break;
                case R.id.navigation_feature1:
                    chek_pos = "feature";
                    back.setVisibility( View.GONE );
                    check_menu_open = false;
                    slidingRootNav.closeMenu();
                    selectedFragment = new Features();
                    break;


            }
            // NavController navController = Navigation.findNavController( Dashboard.this, R.id.nav_host_fragment_activity_dashboard );
            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

            return true;

        }
    };
    private void close_menu(){
        check_menu_open = false;
        if(chek_pos.equals( "home" )){
            selectedFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else if(chek_pos.equals( "news" )){
            selectedFragment = new NewsAndAnnocementList();

            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else   if(chek_pos.equals( "master" )){
            selectedFragment = new MasterPlan();

            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        else  if(chek_pos.equals( "feature" )){
            selectedFragment = new Features();

            getSupportFragmentManager().beginTransaction().replace( R.id.view_frag, selectedFragment ).commit();

        }
        slidingRootNav.closeMenu();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        guest = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}