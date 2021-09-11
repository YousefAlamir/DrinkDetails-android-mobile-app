package com.alamir.drinkdetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.alamir.drinkdetail.R;
import com.alamir.drinkdetail.model.IntroItem;

import java.util.List;


/**
 * @Class_name:  IntroViewPagerAdapter
 * @Description: this class getts all the fragments and put tem to the main viewPager
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class IntroViewPagerAdapter extends PagerAdapter {

    Context myContext;
    List<IntroItem> myIntroItems;

    public IntroViewPagerAdapter(Context myContext, List<IntroItem> myIntroItems) {
        this.myContext = myContext;
        this.myIntroItems = myIntroItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.intro_item, null);

        ImageView img = view.findViewById(R.id.intro_img);
        TextView desc = view.findViewById(R.id.intro_desc);

        img.setImageResource(myIntroItems.get(position).getImg());
        desc.setText(myIntroItems.get(position).getDesc());
        desc.setTextColor(myIntroItems.get(position).getColor());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return myIntroItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
