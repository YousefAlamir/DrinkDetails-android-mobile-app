package com.alamir.drinkdetail.model;

/**
 * @Class_name:  DiscoverThirdListModel
 * @Description: this class is model to represents the third list in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class DiscoverThirdListModel {
    int img;
    String name;

    public DiscoverThirdListModel(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
