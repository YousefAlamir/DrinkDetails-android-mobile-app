package com.alamir.drinkdetail.database;

import android.provider.BaseColumns;



/**
 * @Class_name:  List
 * @Description: this class declare all the tables and columns name
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class List {


    private List(){
    }
    public static final class Prodactdetails implements BaseColumns {
        public static final String TABALE_NAME="Recipe";
        public static final String COL1="RecipeID";
        public static final String COL2="name";
        public static final String COL3="description";
        public static final String COL4="preparation";
        public static final String COL5="categories";

        public static final String TABALE_NAME2="Ingredients";
        public static final String COL20="name";
        public static final String COL21="name";
        public static final String COL22="quantity";
        public static final String COL23="recipeid";

        public static final String TABALE_NAME3="Favorite";
        public static final String COL31="boolFavorite";
        public static final String COL32="recipeid";
    }
}