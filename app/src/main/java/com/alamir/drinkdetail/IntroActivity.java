package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.alamir.drinkdetail.adapter.*;
import com.alamir.drinkdetail.model.IntroItem;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @Class_name:  IntroActivity {@link IntroActivity}
 * @Description: the class is responsible for the three intro screens ,and it is the second class in the running process.
 * @Version:     0.0
 * @Created_by:  Yousef Alamir
 * @Application: Drink Details
 */

public class IntroActivity extends AppCompatActivity {

    private ViewPager introViewPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button nextBtn, startBtn, skipBtn;
    int position = 0;
    Animation startAnim, circleAnim, polygonAnim;
    ImageView circle, polygon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (isSeen()){
            startMain();
        }
        setContentView(R.layout.activity_intro);

        tabIndicator = findViewById(R.id.tab_indicator);
        nextBtn = findViewById(R.id.nextBtn);
        startBtn = findViewById(R.id.start_btn);
        skipBtn = findViewById(R.id.skipBtn);
        introViewPager = findViewById(R.id.intro_view_pager);
        circle = findViewById(R.id.purple_circle);
        polygon = findViewById(R.id.pink_polygon);
        startAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.start_btn_anime);
        circleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.circle_anim);
        polygonAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.polydon_anim);


        final List<IntroItem> itemList = new ArrayList<>();
        itemList.add(new IntroItem(R.drawable.drinks, R.string.drinks, R.color.main_purple));
        itemList.add(new IntroItem(R.drawable.fav, R.string.fav, R.color.black));
        itemList.add(new IntroItem(R.drawable.ingredients, R.string.ingredients, R.color.gray));

        introViewPagerAdapter = new IntroViewPagerAdapter(this, itemList);
        introViewPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(introViewPager);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = introViewPager.getCurrentItem();
                if (position < itemList.size()){
                    position++;
                    introViewPager.setCurrentItem(position);
                }
                if (position == itemList.size()-1){
                    lastIntroScreen();
                    polygon.setAnimation(polygonAnim);
                    circle.setAnimation(circleAnim);
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle.setAnimation(circleAnim);
                polygon.setAnimation(polygonAnim);
                if (tab.getPosition() == itemList.size()-1){
                    lastIntroScreen();
                }
                if (tab.getPosition() < itemList.size()-1){
                    unHideNextBtn();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain();
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                introViewPager.setCurrentItem(itemList.size()-1);
            }
        });
    }

    /**
     * @method_name: unHideNextBtn
     * @Description: this method unhide the next and selector dots in the {@link IntroActivity}
     *
     * @param:       none
     * @return:      none
     */
    private void unHideNextBtn() {
        nextBtn.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        startBtn.setVisibility(View.INVISIBLE);
    }

    /**
     * @method_name: isSeen
     * @Description: this method checks if the user has already seen the intro screens in the {@link IntroActivity} or not.
     *
     * @param:       none
     * @return:      none
     */
    private boolean isSeen() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Prefs", MODE_PRIVATE);
        boolean isSeen = prefs.getBoolean("isSeen", false);
        return isSeen;
    }

    /**
     * @method_name: savePrefsData
     * @Description: this method save a boolean value to check if the user has seen the intro screens or not.
     *
     * @param:       none
     * @return:      none
     */
    private void savePrefsData() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSeen", true);
        editor.commit();
    }

    /**
     * @method_name: startMain
     * @Description: this method transfer the run process from {@link IntroActivity} to {@link MainActivity}.
     *
     * @param:       none
     * @return:      none
     */
    private void startMain() {
        Intent main = new Intent( getApplicationContext(), MainActivity.class);
        startActivity(main);
        savePrefsData();
        finish();
    }

    /**
     * @method_name: lastIntroScreen
     * @Description: this method hide the next button and the selector dots in the {@link IntroActivity} during the last intro screens.
     *
     * @param:       none
     * @return:      none
     */
    private void lastIntroScreen() {
        nextBtn.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        startBtn.setVisibility(View.VISIBLE);
        startBtn.setAnimation(startAnim);
    }
}
