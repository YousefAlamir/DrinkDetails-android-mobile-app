package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Class_name:  Splash {@link Splash}
 * @Description: this is the first class will appear when we run this project ,and it represents Splash Screen which show the application name and logo.
 * @Version:     0.0
 * @Created_by:  Yousef Alamir
 * @Application: Drink Details
 */

public class Splash extends AppCompatActivity {

    // Declaring variables and objects:
    ImageView logo;
    Animation logoAnim, appNameAnim;
    ProgressBar progressBar;
    TextView appName;
    Timer timer;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_splash);

        // Initializing variables and objects:
        logo = findViewById(R.id.logo);
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
        logo.setAnimation(logoAnim);

        appName = findViewById(R.id.appName);
        appNameAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.app_name_animation);
        appName.setAnimation(appNameAnim);

        // setting up the star circular progress bar
        progressBar = findViewById(R.id.splashProgress);
        progressBar.setVisibility(View.VISIBLE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 2500);
        progressBar.setVisibility(View.INVISIBLE);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intro = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intro);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 3000);

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
