package com.xprinter.newsplan.homepage;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
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
import com.xprinter.newsplan.bookmarks.BookmarkPresenter;
import com.xprinter.newsplan.service.CacheService;

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
        initViews();

        //回复fragment的状态
        getBackFragment(savedInstanceState);

        //实例化boormarkprenter
        new BookmarkPresenter();//todo:这个类还没有写

        //默认显示首页内容
        showMainFragment();

        //启动服务
        startService(new Intent(this, CacheService.class));





    }

    private void initViews(){
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
        if (!mainFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment,mainFragment,"mainFragment")
                    .commit();
        }
        if (!bookmarkFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment,bookmarkFragment,"bookmarkFragment")
                    .commit();
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

        if (id == R.id.nav_home) {
            showMainFragment();
        } else if (id == R.id.nav_bookmarks) {
            showBookFragment();

        } else if (id == R.id.nav_change_theme) {
            //改变主题

        } else if (id == R.id.nav_settings) {
            //设置

        } else if (id == R.id.nav_about) {
            //关于

        }

        return true;
    }

    @Override
    protected void onDestroy() {
        ActivityManager manager= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service:manager.getRunningServices(Integer.MAX_VALUE)){
            if (CacheService.class.getName().equals(service.service.getClassName())){
                stopService(new Intent(this,CacheService.class));
            }
        }
        super.onDestroy();
    }

    //退出时保存状态
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if(mainFragment.isAdded()){
            getSupportFragmentManager().putFragment(outState,"mainFragment",mainFragment);
        }
        if (bookmarkFragment.isAdded()){
            getSupportFragmentManager().putFragment(outState,"bookmarkFragment",bookmarkFragment);
        }
    }
}
