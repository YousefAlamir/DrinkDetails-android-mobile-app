package com.alamir.drinkdetail.model;

/**
 * @Class_name:  DiscoverForthListModel
 * @Description: this class is model to represents the fourth list in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */

public class DiscoverForthListModel {
    int img;
    String name, desc;

    public DiscoverForthListModel(int img, String name, String desc) {
        this.img = img;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
