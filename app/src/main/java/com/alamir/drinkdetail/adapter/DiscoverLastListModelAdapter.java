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
import androidx.recyclerview.widget.RecyclerView;

import com.alamir.drinkdetail.ProductScreen;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.model.DbModel;
import com.alamir.drinkdetail.model.DiscoverThirdListModel;

import java.util.List;

/**
 * @Class_name:  DiscoverForthListModelAdapter
 * @Description: this class gets the items from te database and put them into recycleView in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */
public class DiscoverLastListModelAdapter extends RecyclerView.Adapter<DiscoverLastListModelAdapter.MyViewHolder> {
        Context context;
        List<DbModel> discoverLastListModelList ;

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView img;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.product_model_for_discover_last_recycler_view_txt);
        img = itemView.findViewById(R.id.product_model_for_discover_last_recycler_view_img);
    }
}

    public DiscoverLastListModelAdapter(Context context, List<DbModel> discoverLastListModelList) {
    this.context = context;
        this.discoverLastListModelList = discoverLastListModelList;
    }

    @NonNull
    @Override
    public DiscoverLastListModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_model_for_discover_last_recycler_view, parent, false);
        return new DiscoverLastListModelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverLastListModelAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        DbModel model = discoverLastListModelList.get(position);
        holder.title.setText(model.getName());
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+discoverLastListModelList.get(position).getImg());
        holder.img.setImageURI(uri);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", String.valueOf(discoverLastListModelList.get(pos).getID()));
                bundle.putString("name", String.valueOf(discoverLastListModelList.get(pos).getName()));
                bundle.putString("desc", discoverLastListModelList.get(pos).getDesc());
                bundle.putString("prepare", discoverLastListModelList.get(pos).getPrepare());
                bundle.putString("type", discoverLastListModelList.get(pos).getType());
                bundle.putString("img", discoverLastListModelList.get(pos).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return discoverLastListModelList.size();
    }

}
