package com.example.organiko1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organiko1.Class.OneDrink.Drink;
import com.example.organiko1.Class.OneDrink.Drinks;
import com.example.organiko1.R;
import com.example.organiko1.Service.DrinkService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

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
    private Button atras;
    private CheckBox corazon;
    FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_view);

        String id = getIntent().getStringExtra("id");

        nombrDrink = (TextView)findViewById(R.id.nombreTrago);
        ingredientes = (TextView)findViewById(R.id.ingredientes);
        foto = (ImageView) findViewById(R.id.foto);
        pasos = (TextView)findViewById(R.id.pasos);
        atras = (Button)findViewById(R.id.buttonBack);
        corazon = (CheckBox) findViewById(R.id.checkBox);
        auth = FirebaseAuth.getInstance();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewDrinkActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });



        corazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser firebaseUser = auth.getCurrentUser();
                String userid = firebaseUser.getUid();

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("favs");

                HashMap<String, String> fav = new HashMap<>();

                fav.put("userid", userid);
                fav.put("tragoid", id);

                reference.child("Userid").setValue(fav).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("aaa2", "guardado");
                        Log.d("aaa2", "guardado");
                    }
                });

                }
        });



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
                            pasos.setText(bebe.getStrInstructions());
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
            ingredientes.setText(bebe.getStrMeasure1() + " " + bebe.getStrIngredient1() );
        }
        if(bebe.getStrIngredient2()!= null){
            ingredientes.append(  "," + bebe.getStrMeasure2() + " "+  bebe.getStrIngredient2() );

        }
        if(bebe.getStrIngredient3()!= null){
            ingredientes.append("," + bebe.getStrMeasure3() + " "+  bebe.getStrIngredient3());
        }
        if(bebe.getStrIngredient4()!= null){
            ingredientes.append("," + bebe.getStrMeasure4() + " "+ bebe.getStrIngredient4());
        }
        if(bebe.getStrIngredient5() != null){
            ingredientes.append("," + bebe.getStrMeasure5() + " "+ bebe.getStrIngredient5());
        }
        if(bebe.getStrIngredient6() != null){
            ingredientes.append("," + bebe.getStrMeasure6() + " "+ bebe.getStrIngredient6());
        }
        if(bebe.getStrIngredient7() != null){
            ingredientes.append("," + bebe.getStrMeasure7() + " "+ bebe.getStrIngredient7());
        }
        if(bebe.getStrIngredient8() != null){
            ingredientes.append("," + bebe.getStrMeasure8() + " "+ bebe.getStrIngredient8());
        }
        if(bebe.getStrIngredient9() != null){
            ingredientes.append("," + bebe.getStrMeasure9() + " "+ bebe.getStrIngredient9() );
        }
        if(bebe.getStrIngredient10() != null){
            ingredientes.append("," + bebe.getStrMeasure10() + " "+ bebe.getStrIngredient10() );
        }
        if(bebe.getStrIngredient11() != null){
            ingredientes.append("," + bebe.getStrMeasure11() + " "+ bebe.getStrIngredient11() );
        }
        if(bebe.getStrIngredient12() != null){
            ingredientes.append("," + bebe.getStrMeasure12() + " "+ bebe.getStrIngredient12() );
        }
        if(bebe.getStrIngredient13() != null){
            ingredientes.append("," + bebe.getStrMeasure13() + " "+ bebe.getStrIngredient13());
        }
        if(bebe.getStrIngredient14()!= null){
            ingredientes.append("," + bebe.getStrMeasure14() + " "+ bebe.getStrIngredient14() );
        }
        if(bebe.getStrIngredient15() != null){
            ingredientes.append("," + bebe.getStrMeasure15() + " "+ bebe.getStrIngredient15().toString());
        }

    }
}