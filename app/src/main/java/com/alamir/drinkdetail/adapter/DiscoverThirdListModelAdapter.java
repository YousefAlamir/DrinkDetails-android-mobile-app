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
 * @Class_name:  DiscoverThirdListModelAdapter
 * @Description: this class gets the items from te database and put them into recycleView in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class DiscoverThirdListModelAdapter extends RecyclerView.Adapter<DiscoverThirdListModelAdapter.MyViewHolder> {
    Context context;
    List<DbModel> discoverThirdListModelList ;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_model_for_discover_third_listview_title);
            img = itemView.findViewById(R.id.product_model_for_discover_third_listview_img);
        }
    }

    public DiscoverThirdListModelAdapter(Context context, List<DbModel> discoverThirdListModelList) {
        this.context = context;
        this.discoverThirdListModelList = discoverThirdListModelList;
    }

    @NonNull
    @Override
    public DiscoverThirdListModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_model_for_discover_third_view, parent, false);
        return new DiscoverThirdListModelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverThirdListModelAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        DbModel model = discoverThirdListModelList.get(position);
        holder.title.setText(model.getName());
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+discoverThirdListModelList.get(position).getImg());
        holder.img.setImageURI(uri);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productScreen = new Intent(context, ProductScreen.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", String.valueOf(discoverThirdListModelList.get(pos).getID()));
                bundle.putString("name", String.valueOf(discoverThirdListModelList.get(pos).getName()));
                bundle.putString("desc", discoverThirdListModelList.get(pos).getDesc());
                bundle.putString("prepare", discoverThirdListModelList.get(pos).getPrepare());
                bundle.putString("type", discoverThirdListModelList.get(pos).getType());
                bundle.putString("img", discoverThirdListModelList.get(pos).getImg());
                productScreen.putExtras(bundle);
                context.startActivity(productScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return discoverThirdListModelList.size();
    }

}
