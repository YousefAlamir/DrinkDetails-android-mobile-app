package com.alamir.drinkdetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.alamir.drinkdetail.ProductScreen;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.database.DataBase;
import com.alamir.drinkdetail.model.DbModel;
import com.alamir.drinkdetail.model.FavoriteProductModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


/**
 * @Class_name:  FavoriteProductModelAdapter
 * @Description: this class gets the items from te database and put them into listView
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */
public class FavoriteProductModelAdapter extends BaseAdapter {
    Context context;
    List<DbModel> favoriteProductModelList;
    DataBase db;
    public FavoriteProductModelAdapter(Context context, List<DbModel> favoriteProductModelList) {
        this.context = context;
        this.favoriteProductModelList = favoriteProductModelList;
    }

    @Override
    public int getCount() {
        return favoriteProductModelList.size();
    }


    @Override
    public Object getItem(int position) {
        return favoriteProductModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.product_model_for_favorite, null);

        final ImageView productImg = view.findViewById(R.id.product_model_for_favorite_img);
        TextView productName = view.findViewById(R.id.product_model_for_favorite_title);
        TextView productDesc = view.findViewById(R.id.product_model_for_favorite_desc);
        ImageButton favoriteBtn = view.findViewById(R.id.product_model_for_favorite_fav);
        ImageButton shareBtn = view.findViewById(R.id.product_model_for_favorite_share);


        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+favoriteProductModelList.get(position).getImg());
        productImg.setImageURI(uri);
        productName.setText(favoriteProductModelList.get(position).getName());
        productDesc.setText(favoriteProductModelList.get(position).getDesc());


        db = new DataBase(context);
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteFavoriteItem(favoriteProductModelList.get(position).getID());
                notifyDataSetChanged();
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
                    shareInt.putExtra(Intent.EXTRA_TEXT,"*Name:* " + favoriteProductModelList.get(position).getName()+ "\n"
                            + "*Description:* " + favoriteProductModelList.get(position).getDesc() + "\n\n"
                            + "*Preparing way:* \n" + favoriteProductModelList.get(position).getPrepare() + "\n");
                    shareInt.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                    shareInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }catch (Exception e){throw new RuntimeException(e);}
                context.startActivity(Intent.createChooser(shareInt,"Share Product: "));
            }
        });
        final int pos = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();

                bundle.putString("ID", String.valueOf(favoriteProductModelList.get(pos).getID()));
                bundle.putString("name", String.valueOf(favoriteProductModelList.get(pos).getName()));
                bundle.putString("desc", favoriteProductModelList.get(pos).getDesc());
                bundle.putString("prepare", favoriteProductModelList.get(pos).getPrepare());
                bundle.putString("type", favoriteProductModelList.get(pos).getType());
                bundle.putString("img", favoriteProductModelList.get(pos).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });
        return view;
    }
    public void shareProduct(int pos){


    }
}
