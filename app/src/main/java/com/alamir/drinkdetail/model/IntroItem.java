package com.alamir.drinkdetail.model;



/**
 * @Class_name:  IntroItem
 * @Description: this class is model to represents the screen of the intro activity
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class IntroItem {
    int img;
    int desc;
    int color;

    public IntroItem(int img, int desc, int color) {
        this.img = img;
        this.desc = desc;
        this.color = color;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
