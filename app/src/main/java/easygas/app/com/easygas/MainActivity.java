package easygas.app.com.easygas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import easygas.app.com.easygas.Fragment.aboutus;
import easygas.app.com.easygas.Fragment.fragment_add_order;
import easygas.app.com.easygas.Fragment.fragment_map;
import easygas.app.com.easygas.Fragment.fragment_mywallet;
import easygas.app.com.easygas.Fragment.fragment_order_list;
import easygas.app.com.easygas.Utils.Constant;
import easygas.app.com.easygas.Utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout drawerView;
    public Toolbar toolBar;
    public ImageView imageView;
    public TextView homeTv, activity_main_invisible, aboutusTv, orderlistTv, myWalletTv, logoutTv, tvTitle;
    public FragmentTransaction transaction;
    public Utils utils;
    public Fragment fragment = null;
    public FragmentManager fragmentManager;
    public LinearLayout navigationDrawerLinear, orderlist_layout, walletlayout, layout_invisible;
    public FrameLayout navigationDrawerFrame;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mainView;
    private ActionBarDrawerToggle mDrawerToggle;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationDrawerLinear = (LinearLayout) findViewById(R.id.activity_main_navigation_linear);
        navigationDrawerFrame = (FrameLayout) findViewById(R.id.activity_main_navigation_frame);
        utils = new Utils();

        layout_invisible = (LinearLayout) findViewById(R.id.layout_invisible);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (RelativeLayout) findViewById(R.id.drawerView);
        mainView = (RelativeLayout) findViewById(R.id.mainView);
        activity_main_invisible = (TextView) findViewById(R.id.activity_main_invisible);
        toolBar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        orderlist_layout = (LinearLayout) findViewById(R.id.orderlist_layout);
        walletlayout = (LinearLayout) findViewById(R.id.walletlayout);
        tvTitle = (TextView) toolBar.findViewById(R.id.tvTitle);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        user = getIntent().getStringExtra("user");

        Log.d("USERREAD", "" + Utils.ReadSharePrefrence(MainActivity.this, Constant.USERNAME));
        imageView = (ImageView) findViewById(R.id.imageView);
        homeTv = (TextView) findViewById(R.id.activity_main_home);
        aboutusTv = (TextView) findViewById(R.id.activity_main_aboutus);
        orderlistTv = (TextView) findViewById(R.id.activity_main_orderlist);
        myWalletTv = (TextView) findViewById(R.id.activity_main_mywallet);
        logoutTv = (TextView) findViewById(R.id.activity_main_logout);

        if (user.equalsIgnoreCase("user")) {
            layout_invisible.setVisibility(View.GONE);
            homeTv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            aboutusTv.setVisibility(View.VISIBLE);
            orderlistTv.setVisibility(View.VISIBLE);
            logoutTv.setVisibility(View.VISIBLE);
            myWalletTv.setVisibility(View.VISIBLE);
        } else {
            orderlist_layout.setVisibility(View.GONE);
            walletlayout.setVisibility(View.GONE);
            layout_invisible.setVisibility(View.GONE);
        }
        homeTv.setOnClickListener(this);
        imageView.setOnClickListener(this);
        aboutusTv.setOnClickListener(this);
        orderlistTv.setOnClickListener(this);
        logoutTv.setOnClickListener(this);
        myWalletTv.setOnClickListener(this);
        activity_main_invisible.setOnClickListener(this);
        // For open home screen
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
                mDrawerLayout.setScrimColor(ContextCompat.getColor(MainActivity.this, android.R.color.transparent));
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (user.equalsIgnoreCase("user")){
            activity_main_invisible.performClick();
            }
        else {
            homeTv.performClick();
        }
    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        String stackName = "";
        switch (v.getId()) {
            case R.id.imageView:
                mDrawerLayout.openDrawer(Gravity.LEFT); //Edit Gravity.START need API 14
                break;

            case R.id.activity_main_invisible:
                tvTitle.setText("User Required Quantity");
                fragment = new fragment_add_order();
                break;

            case R.id.activity_main_home:
                tvTitle.setText("Map");
                fragment = new fragment_map();
                break;


            case R.id.activity_main_orderlist:
                tvTitle.setText("Your Order");
                fragment = new fragment_order_list();
                stackName = "Order";
                break;
            case R.id.activity_main_aboutus:
                tvTitle.setText("About Us");
                fragment = new aboutus();
                break;
            case R.id.activity_main_mywallet:
                tvTitle.setText("My Wallet");
                fragment = new fragment_mywallet();
                break;
            case R.id.activity_main_logout:
                Utils.ClearaSharePrefrence(MainActivity.this);
                Intent in = new Intent(MainActivity.this, Login.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
        }

        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.activity_main_frame_layout, fragment);
            transaction.addToBackStack(stackName);
            transaction.commit();
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            super.onBackPressed();
            navigationDrawerLinear.setVisibility(View.VISIBLE);
            navigationDrawerFrame.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
            FragmentManager fm = getSupportFragmentManager();
            int lastFragmentCount = fm.getBackStackEntryCount() - 1;
            if (lastFragmentCount == -1) {
                this.finish();
            }
        }
    }

}
