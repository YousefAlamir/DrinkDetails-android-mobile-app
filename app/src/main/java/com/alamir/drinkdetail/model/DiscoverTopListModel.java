package com.alamir.drinkdetail.model;


/**
 * @Class_name:  DiscoverTopListModel
 * @Description: this class is model to represents the top list in the discover activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class DiscoverTopListModel {
    int img, bgColor;
    String title;

    public DiscoverTopListModel(int img, int bgColor, String title) {
        this.img = img;
        this.bgColor = bgColor;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
