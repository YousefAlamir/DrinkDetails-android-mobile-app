package com.alamir.drinkdetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.model.Ingredients;

import java.util.List;



/**
 * @Class_name:  IngredientsAdapter
 * @Description: this class gets all the ingredients of the product from the ingredient table in the database
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    List<Ingredients> ingredients ;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredientName);
            amount = itemView.findViewById(R.id.ingredientAmount);
        }
    }

    public IngredientsAdapter(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_view, parent, false);
        return new IngredientsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.MyViewHolder holder, int position) {
        Ingredients model = ingredients.get(position);
        holder.name.setText(model.getName());
        holder.amount.setText(model.getAmount());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

}
