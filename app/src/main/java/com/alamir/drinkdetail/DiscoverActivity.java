package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.Toast;

import com.alamir.drinkdetail.adapter.DiscoverForthListModelAdapter;
import com.alamir.drinkdetail.adapter.DiscoverLastListModelAdapter;
import com.alamir.drinkdetail.adapter.DiscoverSecondListModelAdapter;
import com.alamir.drinkdetail.adapter.DiscoverThirdListModelAdapter;
import com.alamir.drinkdetail.adapter.DiscoverTopListModelAdapter;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.DbModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Class_name:  DiscoverActivity
 * @Description: this class represents the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class DiscoverActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    private final int ID_HOME = 1;
    private final int ID_DISCOVER= 2;
    private final int ID_FAVORITE = 3;
    private final int ID_SETTINGS = 4;

    RecyclerView discoverTopRecyclerView, discoverThirdRecyclerView, discoverForthRecyclerView, discoverLastRecyclerView;
    GridView discoverSecondGridView;

    DataBase db;

    LinearLayoutManager topManager, thirdManager, forthManager, lastManager;

    DiscoverTopListModelAdapter discoverTopListModelAdapter;
    DiscoverSecondListModelAdapter discoverSecondListModelAdapter;
    DiscoverThirdListModelAdapter discoverThirdListModelAdapter;
    DiscoverForthListModelAdapter discoverForthListModelAdapter;
    DiscoverLastListModelAdapter discoverLastListModelAdapter;

    List<DbModel> dbModelsForTop, dbModelsForSecond, dbModelsForThird, dbModelsForForth, dbModelsForLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        discoverTopRecyclerView = findViewById(R.id.discoverTopRecyclerView);
        discoverSecondGridView = findViewById(R.id.discoverSecondGridView);
        discoverThirdRecyclerView = findViewById(R.id.discoverThirdRecyclerView);
        discoverForthRecyclerView = findViewById(R.id.discoverForthRecyclerView);
        discoverLastRecyclerView = findViewById(R.id.discoverLastRecyclerView);

        bottomNavigation = findViewById(R.id.meowBtmNav);

        topManager = new LinearLayoutManager(getApplicationContext());
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        discoverTopRecyclerView.setLayoutManager(topManager);
        discoverTopRecyclerView.setItemAnimator(new DefaultItemAnimator());

        thirdManager = new LinearLayoutManager(getApplicationContext());
        thirdManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        discoverThirdRecyclerView.setLayoutManager(thirdManager);
        discoverThirdRecyclerView.setItemAnimator(new DefaultItemAnimator());

        forthManager = new LinearLayoutManager(getApplicationContext());
        forthManager.setOrientation(LinearLayoutManager.VERTICAL);
        discoverForthRecyclerView.setLayoutManager(forthManager);
        discoverForthRecyclerView.setItemAnimator(new DefaultItemAnimator());

        lastManager = new LinearLayoutManager(getApplicationContext());
        lastManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        discoverLastRecyclerView.setLayoutManager(lastManager);
        discoverLastRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //______________________________________________________________________ DB
        db =new DataBase(getApplicationContext());
        //______________________________________________________________________ DB

        prepareDiscoverTopListView();
        prepareDiscoverSecondListView();
        prepareDiscoverThirdListView();
        prepareDiscoverForthListView();
        prepareDiscoverLastListView();
        prepareBottomNavBar();
    }

    /**
     * @method_name: prepareDiscoverTopListView
     * @Description: this method prepare {@link DiscoverActivity} first list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareDiscoverTopListView() {
        dbModelsForTop = new ArrayList<>();
        Cursor cursor = db.selectAllRandomly();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModelsForTop.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
        discoverTopListModelAdapter = new DiscoverTopListModelAdapter(this, dbModelsForTop);
        discoverTopRecyclerView.setAdapter(discoverTopListModelAdapter);
        discoverTopListModelAdapter.notifyDataSetChanged();
    }

    /**
     * @method_name: prepareDiscoverSecondListView
     * @Description: this method prepare {@link DiscoverActivity} second list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareDiscoverSecondListView() {

        dbModelsForSecond = new ArrayList<>();
        Cursor cursor = db.isCocktail();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModelsForSecond.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
        List<DbModel> dbModelsForSecond2 = new ArrayList<>();
        for ( DbModel item : dbModelsForSecond) {
            Collections.shuffle(dbModelsForSecond);
            dbModelsForSecond2.add(item);
            if (dbModelsForSecond2.size() == 4 ) break;
        }

        discoverSecondListModelAdapter = new DiscoverSecondListModelAdapter(DiscoverActivity.this, dbModelsForSecond2);
        discoverSecondGridView.setAdapter(discoverSecondListModelAdapter);
        discoverSecondGridView.setNumColumns(2);
        discoverSecondGridView.setHorizontalSpacing(12);
        discoverSecondGridView.setVerticalSpacing(12);
        discoverSecondGridView.setGravity(Gravity.CENTER);

    }

    /**
     * @method_name: prepareDiscoverThirdListView
     * @Description: this method prepare {@link DiscoverActivity} third list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareDiscoverThirdListView() {
        dbModelsForThird = new ArrayList<>();
        Cursor cursor = db.isMilkshake();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModelsForThird.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
        List<DbModel> dbModelsForThird2 = new ArrayList<>();
        for ( DbModel item : dbModelsForThird) {
            Collections.shuffle(dbModelsForSecond);
            dbModelsForThird2.add(item);
            if (dbModelsForThird2.size() == 15 ) break;
        }

        discoverThirdListModelAdapter = new DiscoverThirdListModelAdapter(this, dbModelsForThird2);
        discoverThirdRecyclerView.setAdapter(discoverThirdListModelAdapter);
        discoverThirdListModelAdapter.notifyDataSetChanged();
    }

    /**
     * @method_name: prepareDiscoverForthListView
     * @Description: this method prepare {@link DiscoverActivity} forth list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareDiscoverForthListView() {
        dbModelsForForth= new ArrayList<>();
        Cursor cursor = db.isCocoa();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModelsForForth.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
        List<DbModel> dbModelsForForth2 = new ArrayList<>();
        for ( DbModel item : dbModelsForForth) {
            Collections.shuffle(dbModelsForForth);
            dbModelsForForth2.add(item);
            if (dbModelsForForth2.size() == 3 ) break;
        }

        discoverForthListModelAdapter = new DiscoverForthListModelAdapter(this, dbModelsForForth2);
        discoverForthRecyclerView.setAdapter(discoverForthListModelAdapter);
        discoverForthListModelAdapter.notifyDataSetChanged();
    }

    /**
     * @method_name: prepareDiscoverLastListView
     * @Description: this method prepare {@link DiscoverActivity} fifth list of items.
     *
     * @param:       none
     * @return:      none
     */
    private void prepareDiscoverLastListView() {
        dbModelsForLast = new ArrayList<>();
        Cursor cursor = db.isSoftDrink();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModelsForLast.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
        List<DbModel> dbModelsForLast2 = new ArrayList<>();
        for ( DbModel item : dbModelsForLast) {
            Collections.shuffle(dbModelsForLast);
            dbModelsForLast2.add(item);
            if (dbModelsForLast2.size() == 20 ) break;
        }

        discoverLastListModelAdapter= new DiscoverLastListModelAdapter(this, dbModelsForLast2);
        discoverLastRecyclerView.setAdapter(discoverLastListModelAdapter);
        discoverLastListModelAdapter.notifyDataSetChanged();
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
                if (model.getId() == ID_HOME){
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
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
        bottomNavigation.setCount(ID_FAVORITE, String.valueOf(FavoriteActivity.count));
        bottomNavigation.show(ID_DISCOVER, true);
    }
}
