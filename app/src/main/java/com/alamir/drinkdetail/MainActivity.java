package com.alamir.drinkdetail;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.alamir.drinkdetail.adapter.DBAdapter;
import com.alamir.drinkdetail.adapter.MainTabViewPagerAdapter;
import com.alamir.drinkdetail.fragments.AllFragment;
import com.alamir.drinkdetail.fragments.CocktailFragment;
import com.alamir.drinkdetail.fragments.CocoaFragment;
import com.alamir.drinkdetail.fragments.ShakesFragment;
import com.alamir.drinkdetail.fragments.SoftDrinksFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.tabs.TabLayout;

/**
 * @Class_name:  MainActivity {@link MainActivity}
 * @Description: this is the main class in this project ,and it represents Home Activity in the app.
 * @Version:     0.0
 * @Created_by:  Yousef Alamir
 * @Application: Drink Details
 */

public class MainActivity extends AppCompatActivity {

    // Declaring variables and objects:
    TabLayout mainTabLayout;
    ViewPager mainViewPager;
    MainTabViewPagerAdapter mainTabViewPagerAdapter;
    FavoriteActivity fav;
    SearchView mainSearchView;
    DBAdapter dbAdapter;

    MeowBottomNavigation bottomNavigation;
    private final int ID_HOME = 1;
    private final int ID_DISCOVER= 2;
    private final int ID_FAVORITE = 3;
    private final int ID_SETTINGS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing variables and objects
        bottomNavigation = findViewById(R.id.meowBtmNav);
        mainTabLayout = findViewById(R.id.tabLayoutMain);
        mainViewPager = findViewById(R.id.mainViewPager);
        mainSearchView = findViewById(R.id.mainSearchView);
        mainTabViewPagerAdapter = new MainTabViewPagerAdapter(getSupportFragmentManager(),0);
        dbAdapter = new DBAdapter(this, new AllFragment().dbModels);
        fav = new FavoriteActivity();

        mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dbAdapter.getFilter().filter(newText);
                return true;
            }

        });

        setUpTabs();
        prepareBottomNavBar();
    }

    /**
     * @method_name: setUpTabs
     * @Description: this method prepare {@link MainActivity} tabs in the top which contain All categories
     *               like: All, SoftDrinks, Shakes, Cocoa and Cocktail category ,and set them up with their fragments.
     *
     * @param:       none
     * @return:      none
     */
    private void setUpTabs() {
        mainTabViewPagerAdapter.addFragment(new AllFragment(), getString(R.string.all));
        mainTabViewPagerAdapter.addFragment(new SoftDrinksFragment(), getString(R.string.soft_drinks));
        mainTabViewPagerAdapter.addFragment(new ShakesFragment(), getString(R.string.shakes));
        mainTabViewPagerAdapter.addFragment(new CocoaFragment(), getString(R.string.cocoa));
        mainTabViewPagerAdapter.addFragment(new CocktailFragment(), getString(R.string.cocktail));
        mainViewPager.setAdapter(mainTabViewPagerAdapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }

    /**
     * @method_name: prepareBottomNavBar
     * @Description: this method prepare {@link MainActivity} bottom bar in the bottom which contain Links to All Screens
     *               of Application like: Home, Discover, Favorite and Settings Screen.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareBottomNavBar() {
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_DISCOVER,R.drawable.discover));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_FAVORITE,R.drawable.favorite_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SETTINGS,R.drawable.settings));
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model model) {
                if (model.getId() == ID_DISCOVER){
                    Intent discover = new Intent(getApplicationContext(), DiscoverActivity.class);
                    startActivity(discover);
                    overridePendingTransition(0,0);
                    finish();
                }else if (model.getId() == ID_FAVORITE){
                    Intent favorite = new Intent(getApplicationContext(), FavoriteActivity.class);
                    startActivity(favorite);
                    overridePendingTransition(0,0);
                    finish();
                }else if (model.getId() == ID_SETTINGS){
                    Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(settings);
                    overridePendingTransition(0,0);
                    finish();
                }
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model model) {

            }
        });
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model model) {
                String name;
                switch (model.getId()){
                    case ID_HOME: name = "Home";
                        break;
                    case ID_DISCOVER: name = "Discover";
                        break;
                    case ID_FAVORITE: name = "Favorite";
                        break;
                    case ID_SETTINGS: name = "Settings";
                        break;
                    default: name = "main";
                }

            }
        });
        bottomNavigation.setCount(ID_FAVORITE,String.valueOf(FavoriteActivity.count));
        bottomNavigation.show(ID_HOME, true);
    }
}
