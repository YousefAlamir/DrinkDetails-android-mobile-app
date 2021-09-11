package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.opengl.Visibility;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.alamir.drinkdetail.utils.Helper;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.Locale;

/**
 * @Class_name:  SettingsActivity {@link SettingsActivity}
 * @Description: this class represents the settings activity
 * @Version:     0.0
 * @Created_by:  Yousef Alamir
 * @Application: Drink Details
 */


public class SettingsActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    private final int ID_HOME = 1;
    private final int ID_DISCOVER= 2;
    private final int ID_FAVORITE = 3;
    private final int ID_SETTINGS = 4;

    ConstraintLayout expandable, socialExpandable;
    ImageButton arrowBtn, socialArrowBtn;
    CardView langCardView, socialCardView;
    RadioButton englishRadBtn, arabicRadBtn;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_settings);

        expandable = findViewById(R.id.expandable);
        socialExpandable = findViewById(R.id.socialExpandable);
        arrowBtn = findViewById(R.id.arrowBtn);
        socialArrowBtn = findViewById(R.id.socialArrowBtn);
        langCardView = findViewById(R.id.langCardView);
        socialCardView = findViewById(R.id.socialCardView);
        englishRadBtn = findViewById(R.id.englishRadBtn);
        arabicRadBtn = findViewById(R.id.arabicRadBtn);

        //______________________________________________________for expandable constraint layout
        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandable.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(langCardView, new AutoTransition());
                    expandable.setVisibility(View.VISIBLE);
                    arrowBtn.setImageResource(R.drawable.arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition(langCardView, new AutoTransition());
                    expandable.setVisibility(View.GONE);
                    arrowBtn.setImageResource(R.drawable.arrow_down);
                }
            }
        });
        socialArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (socialExpandable.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(socialCardView, new AutoTransition());
                    socialExpandable.setVisibility(View.VISIBLE);
                    socialArrowBtn.setImageResource(R.drawable.arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition(socialCardView, new AutoTransition());
                    socialExpandable.setVisibility(View.GONE);
                    socialArrowBtn.setImageResource(R.drawable.arrow_down);
                }
            }
        });

        //______________________________________________________for languages localization
        englishRadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocal("en");
                englishRadBtn.setChecked(true);
                finish();
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
        arabicRadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocal("ar");
                arabicRadBtn.setChecked(true);
                finish();
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        if (locale.getLanguage() == "ar"){
            arabicRadBtn.setChecked(true);
        }else {
            englishRadBtn.setChecked(true);
        }

        //______________________________________________________for Bottom bar
        bottomNavigation = findViewById(R.id.meowBtmNav);
        prepareBottomNavBar();
    }

    /**
     * @method_name: prepareBottomNavBar
     * @Description: this method prepare {@link SettingsActivity} bottom bar in the bottom which contain Links to All Screens
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
                 if (model.getId() == ID_HOME){
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    overridePendingTransition(0,0);
                    finish();
                }else if (model.getId() == ID_DISCOVER){
                    Intent discover = new Intent(getApplicationContext(), DiscoverActivity.class);
                    startActivity(discover);
                    overridePendingTransition(0,0);
                    finish();
                }else if (model.getId() == ID_FAVORITE){
                    Intent favorite = new Intent(getApplicationContext(), FavoriteActivity.class);
                    startActivity(favorite);
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

            }
        });
        bottomNavigation.setCount(ID_FAVORITE,String.valueOf(FavoriteActivity.count));
        bottomNavigation.show(ID_SETTINGS, true);
    }

    /**
     * @method_name: setLocal
     * @Description: this method Change the localization and language of the application.
     *
     * @param:       lang {@link String}
     * @return:      none
     */
    private void setLocal(String lang) {
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Prefs", MODE_PRIVATE).edit();
        editor.putString("lang", lang);
        editor.apply();
    }

    /**
     * @method_name: loadLocal
     * @Description: this method Load the localization and language of the application.
     *
     * @param:       none
     * @return:      none
     */
    public void loadLocal(){
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        String lang = prefs.getString("lang", "en");
        setLocal(lang);
    }
}
