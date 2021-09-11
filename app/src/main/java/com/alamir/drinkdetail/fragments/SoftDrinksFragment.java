package com.alamir.drinkdetail.fragments;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.adapter.DBAdapter;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.DbModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @Class_name:  SoftDrinksFragment
 * @Description: this class represents the `SoftDrinks` tap in te main activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class SoftDrinksFragment extends Fragment {

    GridView mainProductGridViewSs;
    DBAdapter dbAdapter;
    DataBase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soft_drinks, container, false);

        //______________________________________________________________________ DB
        db =new DataBase(getActivity());
        //______________________________________________________________________ DB

        mainProductGridViewSs = view.findViewById(R.id.mainProductGridViewSs);
        storeDataInArray();
        return view;
    }



    /**
     * @method_name: storeDataInArray
     * @Description: tis method get items from the database into a cursor then store them in an arrayList{@link SoftDrinksFragment} .
     *
     * @param:       none
     * @return:      none
     */

    private void storeDataInArray() {

        List<DbModel> dbModels = new ArrayList<>();
        Cursor cursor = db.isSoftDrink();
        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(),"no thing",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                dbModels.add(new DbModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }

        dbAdapter = new DBAdapter( getActivity(), dbModels);
        mainProductGridViewSs.setAdapter(dbAdapter);
        mainProductGridViewSs.setNumColumns(2);
        mainProductGridViewSs.setHorizontalSpacing(8);
        mainProductGridViewSs.setVerticalSpacing(8);
        mainProductGridViewSs.setGravity(Gravity.CENTER);

    }
}
