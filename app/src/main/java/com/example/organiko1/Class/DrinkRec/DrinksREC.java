package com.example.organiko1.Class.DrinkRec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinksREC {
    @SerializedName("drinks")
    @Expose
    private List<DrinkREC> drinks;

    public List<DrinkREC> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkREC> drinks) {
        this.drinks = drinks;
    }
}
