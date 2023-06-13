package com.example.organiko1.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organiko1.Class.OneDrink.Drink;
import com.example.organiko1.Class.OneDrink.Drinks;
import com.example.organiko1.R;
import com.example.organiko1.Service.DrinkService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viewDrinkActivity extends AppCompatActivity {


    private TextView nombrDrink;
    private TextView ingredientes;
    private ImageView foto ;
    private TextView pasos;
    private Drink bebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_view);

        nombrDrink = (TextView)findViewById(R.id.nombreTrago);
        ingredientes = (TextView)findViewById(R.id.ingredientes);
        foto = (ImageView) findViewById(R.id.foto);
        pasos = (TextView)findViewById(R.id.pasos);


         String id = getIntent().getStringExtra("id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DrinkService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DrinkService servicioPlantAPI = retrofit.create(DrinkService.class);


        Call<Drinks> callPlanta = servicioPlantAPI.getDrink(id);

        callPlanta.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                switch (response.code()) {
                    case 200:
                        Drinks respon = response.body();
                        if (respon != null && respon.getDrinks() != null && !respon.getDrinks().isEmpty()) {

                            bebe = respon.getDrinks().get(0);
                            Picasso.get().load(bebe.getStrDrinkThumb()).into(foto);
                            nombrDrink.setText(bebe.getStrDrink());
                            setIngredientes(ingredientes, bebe);
                            //pasos.setText(bebe.getStrInstructions());
                        }
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {

            }
        });

    }
    private void setIngredientes(TextView ingredientes, Drink bebe){
        if(bebe.getStrIngredient1() != null){
            ingredientes.setText(bebe.getStrIngredient1() );
        }
        if(bebe.getStrIngredient2()!= null){
            ingredientes.setText(bebe.getStrIngredient2() + ",");
        }
        if(bebe.getStrIngredient3()!= null){
            ingredientes.setText(bebe.getStrIngredient3()+ ",");
        }
        if(bebe.getStrIngredient4()!= null){
            ingredientes.setText(bebe.getStrIngredient4() + ",");
        }
        if(bebe.getStrIngredient5() != null){
            ingredientes.setText(bebe.getStrIngredient5() + ",");
        }
        if(bebe.getStrIngredient6() != null){
            ingredientes.setText(bebe.getStrIngredient6() + ",");
        }
        if(bebe.getStrIngredient7() != null){
            ingredientes.setText(bebe.getStrIngredient7() + ",");
        }
        if(bebe.getStrIngredient8() != null){
            ingredientes.setText(bebe.getStrIngredient8() + ",");
        }
        if(bebe.getStrIngredient9() != null){
            ingredientes.setText(bebe.getStrIngredient9() + ",");
        }
        if(bebe.getStrIngredient10() != null){
            ingredientes.setText(bebe.getStrIngredient10() + ",");
        }
        if(bebe.getStrIngredient11() != null){
            ingredientes.setText(bebe.getStrIngredient11() + ",");
        }
        if(bebe.getStrIngredient12() != null){
            ingredientes.setText(bebe.getStrIngredient12() + ",");
        }
        if(bebe.getStrIngredient13() != null){
            ingredientes.setText(bebe.getStrIngredient13() + ",");
        }
        if(bebe.getStrIngredient14()!= null){
            ingredientes.setText(bebe.getStrIngredient14() + ",");
        }
        if(bebe.getStrIngredient15() != null){
            ingredientes.setText(bebe.getStrIngredient15().toString());
        }

    }
}