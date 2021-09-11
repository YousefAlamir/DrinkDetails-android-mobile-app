package com.alamir.drinkdetail.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alamir.drinkdetail.ProductScreen;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.model.DbModel;
import com.alamir.drinkdetail.model.DiscoverTopListModel;

import java.util.List;

/**
 * @Class_name:  DiscoverTopListModelAdapter
 * @Description: this class gets the items from te database and put them into recycleView in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class DiscoverTopListModelAdapter extends RecyclerView.Adapter<DiscoverTopListModelAdapter.MyViewHolder> {
    Context context;
    List<DbModel> discoverTopListModelList ;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_model_for_discover_top_listview_title);
            img = itemView.findViewById(R.id.product_model_for_discover_top_listview_img);
            cardView = itemView.findViewById(R.id.product_model_for_discover_top_listview_cardview);
        }
    }

    public DiscoverTopListModelAdapter(Context context, List<DbModel> discoverTopListModelList) {
        this.context = context;
        this.discoverTopListModelList = discoverTopListModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_model_for_discover_top_recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        DbModel model = discoverTopListModelList.get(position);
        holder.title.setText(model.getName());
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+discoverTopListModelList.get(position).getImg());
        holder.img.setImageURI(uri);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", String.valueOf(discoverTopListModelList.get(pos).getID()));
                bundle.putString("name", String.valueOf(discoverTopListModelList.get(pos).getName()));
                bundle.putString("desc", discoverTopListModelList.get(pos).getDesc());
                bundle.putString("prepare", discoverTopListModelList.get(pos).getPrepare());
                bundle.putString("type", discoverTopListModelList.get(pos).getType());
                bundle.putString("img", discoverTopListModelList.get(pos).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });

    }

    @Override
    public int getItemCount() {
        return discoverTopListModelList.size();
    }

}
