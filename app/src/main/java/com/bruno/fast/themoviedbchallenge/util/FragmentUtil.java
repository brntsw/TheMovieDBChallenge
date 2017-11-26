package com.bruno.fast.themoviedbchallenge.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bruno.fast.themoviedbchallenge.R;

/**
 * Created by brunopardini on 11/25/17.
 */

public class FragmentUtil {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String backstack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFrame, fragment, backstack).addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public static void removeFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    public static void removeAllFragments(FragmentManager fragmentManager){
        FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
        fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
