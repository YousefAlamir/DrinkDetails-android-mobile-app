package com.alamir.drinkdetail.model;

/**
 * @Class_name:  Ingredients
 * @Description: this class is model to represents the Ingredients table items
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class Ingredients {
    String name, amount;

    public Ingredients(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
