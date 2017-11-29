package com.bruno.fast.themoviedbchallenge.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.ui.fragment.GenresListFragment;
import com.bruno.fast.themoviedbchallenge.util.FragmentUtil;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    private void buildGenresListFragment(){
        GenresListFragment fragment = GenresListFragment.newInstance(null);

        FragmentUtil.replaceFragment(getSupportFragmentManager(), fragment, GenresListFragment.TAG);
    }

    @Override
    void setup() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }

        buildGenresListFragment();
    }

    public void showToolbar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().show();
        }
    }

    public void hideToolbar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    public void showBackArrow(){
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void hideBackArrow(){
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        FragmentUtil.removeFragment(getSupportFragmentManager());

        if(getSupportFragmentManager().getBackStackEntryCount() == 2){
            hideBackArrow();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 1 || getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        }
    }
}
