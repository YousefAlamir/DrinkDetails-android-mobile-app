package com.alamir.drinkdetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.graphics.drawable.DrawableWrapper;

import com.alamir.drinkdetail.DiscoverActivity;
import com.alamir.drinkdetail.MainActivity;
import com.alamir.drinkdetail.ProductScreen;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.DbModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @Class_name:  DBAdapter
 * @Description: this class gets the items from the database and put tem into the main gridView
 * @Version:     0.0
 * @Created_by:  asim althibani
 * @Application: Drink Details
 */
public class DBAdapter extends BaseAdapter {
    Context context;
    List<DbModel> mainProductModels ;
    List<DbModel> mainProductModelsForSearch ;
    DataBase db;

    public DBAdapter(Context context, List<DbModel> mainProductModels) {
        this.context = context;
        this.mainProductModels = mainProductModels;
        this.mainProductModelsForSearch = mainProductModels;
    }

    @Override
    public int getCount() {
        return mainProductModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mainProductModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.product_model_for_main, null);

        final ImageView productImg = view.findViewById(R.id.product_model_for_main_img);
        TextView productName = view.findViewById(R.id.product_model_for_main_name);
        final ImageButton favoriteBtn = view.findViewById(R.id.product_model_for_main_fav);
        final ImageButton shareBtn = view.findViewById(R.id.product_model_for_main_share);

        db = new DataBase(context);
        if (db.checkFavoriteList(mainProductModels.get(position).getID()) >= 1){
            favoriteBtn.setImageResource(R.drawable.favorite_red);
        }
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.checkFavoriteList(mainProductModels.get(position).getID()) >= 1) {
                    db.deleteFavoriteItem(mainProductModels.get(position).getID());
                    favoriteBtn.setImageResource(R.drawable.favorite_black);
                    notifyDataSetChanged();
                } else {
                    db.addToDataBase(1, Integer.valueOf(mainProductModels.get(position).getID()));
                    favoriteBtn.setImageResource(R.drawable.favorite_red);
                    notifyDataSetChanged();
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                BitmapDrawable drawable = (BitmapDrawable)productImg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                File f = new File(context.getExternalCacheDir()+"/"+context.getResources().getString(R.string.app_name)+".png");
                Intent shareInt;
                try {
                        FileOutputStream outputStream= new FileOutputStream(f);
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                        outputStream.flush();
                        outputStream.close();
                        shareInt = new Intent(Intent.ACTION_SEND);
                        shareInt.setType("image/*");
                        shareInt.setType("text/plan");
                        shareInt.putExtra(Intent.EXTRA_TEXT,"Name: " + mainProductModels.get(position).getName()+ "\n"
                                + "Description: " + mainProductModels.get(position).getDesc() + "\n\n"
                                + "Preparing way: \n" + mainProductModels.get(position).getPrepare() + "\n");
                        shareInt.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                        shareInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }catch (Exception e){throw new RuntimeException(e);}
                context.startActivity(Intent.createChooser(shareInt,"Share Product: "));
            }
        });
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+mainProductModels.get(position).getImg());
        productImg.setImageURI(uri);
        productName.setText(mainProductModels.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", String.valueOf(mainProductModels.get(position).getID()));
                bundle.putString("name", String.valueOf(mainProductModels.get(position).getName()));
                bundle.putString("desc", mainProductModels.get(position).getDesc());
                bundle.putString("prepare", mainProductModels.get(position).getPrepare());
                bundle.putString("type", mainProductModels.get(position).getType());
                bundle.putString("img", mainProductModels.get(position).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });
        return view;
    }


    /**
     * @method_name: getFilter
     * @Description: get the filter list for the search {@link DBAdapter} .
     *
     * @param:       Filter filter
     * @return:      none
     */
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null | charSequence.length() == 0){
                    filterResults.count = mainProductModelsForSearch.size();
                    filterResults.values = mainProductModelsForSearch;

                }else{
                    String searchChr = charSequence.toString().toLowerCase();

                    List<DbModel> resultData = new ArrayList<>();

                    for (DbModel products: mainProductModelsForSearch){
                        if (products.getName().toLowerCase().contains(searchChr)){
                            resultData.add(products);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                mainProductModels = (List<DbModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
