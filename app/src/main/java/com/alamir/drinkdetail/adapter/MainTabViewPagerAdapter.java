package com.alamir.drinkdetail.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * @Class_name:  MainTabViewPagerAdapter
 * @Description: this class get all the fragments names and show them on the app bar
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class MainTabViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentsList;
    List<String> fragmentTitlesList;

    public MainTabViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragmentsList = new ArrayList<>();
        fragmentTitlesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitlesList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentsList.add(fragment);
        fragmentTitlesList.add(title);
    }
}
