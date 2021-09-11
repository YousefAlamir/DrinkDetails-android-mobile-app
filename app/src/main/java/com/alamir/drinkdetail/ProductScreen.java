package com.alamir.drinkdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamir.drinkdetail.adapter.IngredientsAdapter;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.Ingredients;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * @Class_name:  ProductScreen {@link ProductScreen}
 * @Description: this class represents the activity that show product details
 * @Version:     0.0
 * @Created_by:  Yousef Alamir
 * @Application: Drink Details
 */

public class ProductScreen extends AppCompatActivity {

    ImageView productImg, favHeart, shareBtn;
    TextView productName, productDesc, preparingWay;
    DataBase db;
    RecyclerView ingredientsRecyclerView;
    IngredientsAdapter ingredientsAdapter;
    List<Ingredients> ingredientsList;
    String ID, name, img, desc, prepare;
    String ingredientsTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);

        productImg = findViewById(R.id.productScreenImg);
        favHeart = findViewById(R.id.productScreenFav);
        shareBtn = findViewById(R.id.productScreenShare);
        productName = findViewById(R.id.productScreenName);
        productDesc = findViewById(R.id.productScreenDesc);
        preparingWay = findViewById(R.id.productScreenPrepare);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        db = new DataBase(getApplicationContext());

        Intent productScreen = getIntent();
        ID = productScreen.getStringExtra("ID");
        name = productScreen.getStringExtra("name");
        img = productScreen.getStringExtra("img");
        desc = productScreen.getStringExtra("desc");
        prepare = productScreen.getStringExtra("prepare");

        if (db.checkFavoriteList(ID) >= 1){
            favHeart.setImageResource(R.drawable.favorite_red);
        }
        favHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.checkFavoriteList(ID) >= 1) {
                    db.deleteFavoriteItem(ID);
                    favHeart.setImageResource(R.drawable.favorite_black);
                } else {
                    db.addToDataBase(1, Integer.valueOf(ID));
                    favHeart.setImageResource(R.drawable.favorite_red);
                }
            }
        });

        setIngredients();
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+img);
        productImg.setImageURI(uri);
        productName.setText(name);
        productDesc.setText(desc);
        preparingWay.setText(prepare);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProduct();
            }
        });

    }

    /**
     * @method_name: setIngredients
     * @Description: this method prepare the list of ingredients in {@link ProductScreen}.
     *
     * @param:       none
     * @return:      none
     */
    private void setIngredients() {
        ingredientsList = new ArrayList<>();
        Cursor ingredientsCursor = db.selectIngredientsByItemId(ID);
        if (ingredientsCursor.getCount() == 0){
            Toast.makeText(this, "There is no Ingredients", Toast.LENGTH_LONG).show();
        }else {
            while (ingredientsCursor.moveToNext()){
                ingredientsList.add(new Ingredients(ingredientsCursor.getString(0), ingredientsCursor.getString(1)));
            }
        }
        ingredientsAdapter = new IngredientsAdapter(ingredientsList);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        for (Ingredients item : ingredientsList){
            ingredientsTxt += item.getName()+"->\t"+item.getAmount()+"\n";
        }
    }


    /**
     * @method_name: shareProduct
     * @Description: this method works to share the products and their details  {@link ProductScreen} .
     *
     * @param:       none
     * @return:      none
     */
    public void shareProduct(){

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        BitmapDrawable drawable = (BitmapDrawable)productImg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File f = new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Intent shareInt;
        try {

            FileOutputStream outputStream= new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
            shareInt = new Intent(Intent.ACTION_SEND);
            shareInt.setType("image/*");
            shareInt.setType("text/plan");
            shareInt.putExtra(Intent.EXTRA_TEXT,"*Name:* " + name+ "\n"
                    + "*Description:* " + desc + "\n\n"
                    + "*Ingredients:* \n" + ingredientsTxt + "\n"
                    + "*Preparing way:* \n" + prepare + "\n");
            shareInt.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            shareInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        }catch (Exception e){throw new RuntimeException(e);}
        startActivity(Intent.createChooser(shareInt,"Share Product: "));
    }
}
