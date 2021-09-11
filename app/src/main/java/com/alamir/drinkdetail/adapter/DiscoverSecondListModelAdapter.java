package com.alamir.drinkdetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamir.drinkdetail.ProductScreen;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.model.DbModel;

import java.util.List;


/**
 * @Class_name:  DiscoverSecondListModelAdapter
 * @Description: this class gets the items from te database and put them into recycleView in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class DiscoverSecondListModelAdapter extends BaseAdapter {
    Context context;
    List<DbModel> discoverSecondListModels ;

    public DiscoverSecondListModelAdapter(Context context, List<DbModel> discoverThirdListModels) {
        this.context = context;
        this.discoverSecondListModels = discoverThirdListModels;
    }

    @Override
    public int getCount() {
        return discoverSecondListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return discoverSecondListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.product_model_for_discover_second_grid_view, null);

        ImageView productImg = view.findViewById(R.id.product_model_for_discover_second_grid_view_img);
        TextView productName = view.findViewById(R.id.product_model_for_discover_second_grid_view_name);

        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+discoverSecondListModels.get(position).getImg());
        productImg.setImageURI(uri);
        productName.setText(discoverSecondListModels.get(position).getName());

        final int pos = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();

                bundle.putString("ID", String.valueOf(discoverSecondListModels.get(pos).getID()));
                bundle.putString("name", String.valueOf(discoverSecondListModels.get(pos).getName()));
                bundle.putString("desc", discoverSecondListModels.get(pos).getDesc());
                bundle.putString("prepare", discoverSecondListModels.get(pos).getPrepare());
                bundle.putString("type", discoverSecondListModels.get(pos).getType());
                bundle.putString("img", discoverSecondListModels.get(pos).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });
        return view;
    }
}