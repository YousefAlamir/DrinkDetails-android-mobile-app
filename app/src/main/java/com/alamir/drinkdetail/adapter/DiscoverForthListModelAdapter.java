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
import com.alamir.drinkdetail.model.DiscoverForthListModel;
import java.util.List;


/**
 * @Class_name:  DiscoverForthListModelAdapter
 * @Description: this class gets the items from te database and put them into recycleView in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */
public class DiscoverForthListModelAdapter extends RecyclerView.Adapter<DiscoverForthListModelAdapter.MyViewHolder> {
    Context context;
    List<DbModel> discoverForthListModels;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView desc;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_model_for_discover_forth_recycler_view_name);
            desc = itemView.findViewById(R.id.product_model_for_discover_forth_recycler_view_desc);
            img = itemView.findViewById(R.id.product_model_for_discover_forth_recycler_view_img);
        }
    }

    public DiscoverForthListModelAdapter(Context context, List<DbModel> discoverForthListModels) {
        this.context = context;
        this.discoverForthListModels = discoverForthListModels;
    }

    @NonNull
    @Override
    public DiscoverForthListModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_model_for_discover_forth_recycler_view, parent, false);
        return new DiscoverForthListModelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverForthListModelAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        DbModel model = discoverForthListModels.get(position);
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());
        Uri uri= Uri.parse("android.resource://com.alamir.drinkdetail/drawable/"+discoverForthListModels.get(position).getImg());
        holder.img.setImageURI(uri);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView(pos);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView(pos);
            }
        });
        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView(pos);
            }
        });
    }

    private void getView(int pos) {
        Intent productScreen = new Intent(context, ProductScreen.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID", String.valueOf(discoverForthListModels.get(pos).getID()));
        bundle.putString("name", String.valueOf(discoverForthListModels.get(pos).getName()));
        bundle.putString("desc", discoverForthListModels.get(pos).getDesc());
        bundle.putString("prepare", discoverForthListModels.get(pos).getPrepare());
        bundle.putString("type", discoverForthListModels.get(pos).getType());
        bundle.putString("img", discoverForthListModels.get(pos).getImg());
        productScreen.putExtras(bundle);
        context.startActivity(productScreen);
    }

    @Override
    public int getItemCount() {
        return discoverForthListModels.size();
    }

}
