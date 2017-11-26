package com.bruno.fast.themoviedbchallenge.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.ui.fragment.GenresListFragment;
import com.bruno.fast.themoviedbchallenge.util.FragmentUtil;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;

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

        toolbar.setTitle(getString(R.string.app_name));

        buildGenresListFragment();
    }
}
