package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.alamir.drinkdetail.adapter.DBAdapter;
import com.alamir.drinkdetail.adapter.FavoriteProductModelAdapter;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.DbModel;
import com.alamir.drinkdetail.model.FavoriteProductModel;
import com.alamir.drinkdetail.utils.Helper;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class_name:  FavoriteActivity
 * @Description: this class represents the favorite activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class FavoriteActivity extends AppCompatActivity {

    GridView favoriteProductGridView;
    FavoriteProductModelAdapter favoriteProductModelAdapter;
    MeowBottomNavigation bottomNavigation;
    private final int ID_HOME = 1;
    private final int ID_DISCOVER= 2;
    private final int ID_FAVORITE = 3;
    private final int ID_SETTINGS = 4;
    public static int count = 0;

    SearchView searchView;

    DataBase db;
    List<DbModel> favDbModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteProductGridView = findViewById(R.id.favoriteProductGridView);
        bottomNavigation = findViewById(R.id.meowBtmNav);
        searchView = findViewById(R.id.favSearchView);

        //______________________________________________________________________ DB
        db = new DataBase(getApplicationContext());
        //______________________________________________________________________ DB


        prepareFavoriteProductGridView();
        prepareBottomNavBar();
    }


    /**
     * @method_name: prepareFavoriteProductGridView
     * @Description: this method prepare {@link FavoriteActivity} list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareFavoriteProductGridView() {
        favDbModels = new ArrayList<>();
        Cursor cursor = db.getAllFavoriteListItems();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"there is no thing to view!\n" +
                    "Add something to your favorite \nby clicking the heart button of any drink.",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                favDbModels.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }

        count = favDbModels.size();
        favoriteProductModelAdapter = new FavoriteProductModelAdapter(this, favDbModels);
        favoriteProductGridView.setGravity(Gravity.CENTER);
        favoriteProductGridView.setVerticalSpacing(8);
        favoriteProductGridView.setHorizontalSpacing(8);
        favoriteProductGridView.setNumColumns(1);
        favoriteProductGridView.setAdapter(favoriteProductModelAdapter);
    }

    /**
     * @method_name: prepareBottomNavBar
     * @Description: this method prepare {@link FavoriteActivity} bottom bar in the bottom which contain Links to All Screens
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

            }
        });
        bottomNavigation.setCount(ID_FAVORITE, String.valueOf(count));
        bottomNavigation.show(ID_FAVORITE, true);
    }
}
