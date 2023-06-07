package com.example.organiko1.Class.OneDrink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drinks {
    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks;

   public List<Drink> getDrinks() {
      return drinks;
   }

   public void setDrinks(List<Drink> drinks) {
      this.drinks = drinks;
   }
}
