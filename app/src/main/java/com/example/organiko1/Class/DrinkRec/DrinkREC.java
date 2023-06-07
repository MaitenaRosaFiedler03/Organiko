package com.example.organiko1.Class.DrinkRec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinkREC {

    @SerializedName("strDrink")
    @Expose
    private String strDrink;
    @SerializedName("strDrinkThumb")
    @Expose
    private String strDrinkThumb;
    @SerializedName("idDrink")
    @Expose
    private String idDrink;

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
}
