package com.example.evgenia.tfsandroidchat;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evgenia.tfsandroidchat.aboutApp.AboutAppFrg;
import com.example.evgenia.tfsandroidchat.dialogs_list.DialogsFrg;
import com.example.evgenia.tfsandroidchat.login.LoginActivity;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnNavigationActionBar{
    private static final String TAG = "NavigationActivity";
    private static final int MENU_DIALOGS = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        toggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(toggle);

        navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        // пишем имя пользоваотеля
        String userLogin = getIntent().getExtras().getString(LoginActivity.KEY_LOGIN);
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_user_login)).setText(userLogin);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(MENU_DIALOGS).setChecked(true);
            onNavigationItemSelected(navigationView.getMenu().getItem(MENU_DIALOGS));
        }


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_dialog :
                addFragment(DialogsFrg.newInstance(getString(R.string.dialogs)), false);
                Toast.makeText(this, "dialog", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_about_pp:
                addFragment(AboutAppFrg.newInstance(getString(R.string.about_app)), false);
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_exit :
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        Log.d(TAG, "onBackPressed: getSupportFragmentManager().getBackStackEntryCount()= " + getSupportFragmentManager().getBackStackEntryCount());
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((getSupportFragmentManager().getBackStackEntryCount()) == 1){
            setDrawerToggleEnabled(true);
            super.onBackPressed();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: before");
        if(!toggle.onOptionsItemSelected(item)){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    /* =========== методы для управления навигацией фрагментов на активити==================*/

    @Override
    public void addFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction = fragmentTransaction.replace(R.id.fragment_container, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
            setDrawerToggleEnabled(false);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void setDrawerToggleEnabled(boolean enabled) {
        toggle.setDrawerIndicatorEnabled(enabled);
    }
}
