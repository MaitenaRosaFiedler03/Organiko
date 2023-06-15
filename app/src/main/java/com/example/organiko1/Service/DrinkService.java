package com.example.organiko1.Service;

import com.example.organiko1.Class.DrinkRec.DrinksREC;
import com.example.organiko1.Class.OneDrink.Drinks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinkService {

    final String url = "https://www.thecocktaildb.com/api/json/v1/1/";

    @GET("filter.php?a=Alcoholic")
    Call<DrinksREC> getAlcoholDrinks();

    @GET("lookup.php")
    Call<Drinks> getDrink(@Query("i") String i);

    @GET("search.php?")
    Call<DrinksREC> getFavs(@Query("s") String i);


}
