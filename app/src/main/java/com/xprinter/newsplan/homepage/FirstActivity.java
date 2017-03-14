package com.xprinter.newsplan.homepage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xprinter.newsplan.R;
import com.xprinter.newsplan.bookmarks.BookmarkFragment;

public class FirstActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MainFragment mainFragment;
    private BookmarkFragment bookmarkFragment;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    public static final String ACTION_BOOKMARKS = "com.charlie.zhihudaily.bookmarks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //初始化view、
        initView();



    }

    private void initView(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    //显示主fragment
    private void showMainFragment(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.show(mainFragment);
        transaction.hide(bookmarkFragment);
        transaction.commit();

        toolbar.setTitle(getResources().getString(R.string.app_name));
    }
    //显示收藏fragment
    private void showBookFragment(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.show(bookmarkFragment);
        transaction.hide(mainFragment);
        transaction.commit();

        toolbar.setTitle(getResources().getString(R.string.nav_bookmarks));
        if(bookmarkFragment.isAdded()){
            bookmarkFragment.notifyDataChanged();
        }

    }

    private void getBackFragment(Bundle savedInstanceState){
        if (savedInstanceState!=null){
            mainFragment=(MainFragment)getSupportFragmentManager().getFragment(savedInstanceState,"mainFragment");
            bookmarkFragment= (BookmarkFragment) getSupportFragmentManager().getFragment(savedInstanceState,"bookmarkFragment");
        }else {
            mainFragment=MainFragment.newInstance();
            bookmarkFragment=BookmarkFragment.newInstance();

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
