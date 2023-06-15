package com.example.organiko1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;

import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organiko1.Activity.viewDrinkActivity;
import com.example.organiko1.Adapters.DrinkAdapter;
import com.example.organiko1.Class.DrinkRec.DrinkREC;
import com.example.organiko1.Class.DrinkRec.DrinksREC;
import com.example.organiko1.R;
import com.example.organiko1.Service.DrinkService;
import com.example.organiko1.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<DrinkREC> bebidas;
    private EditText busqueda;
    private RecyclerView recyclerView;
    private ArrayList<DrinkREC> drinkArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final TextView textView = binding.texto1;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView1);
        this.bebidas = new ArrayList<>();
        this.drinkArrayList = new ArrayList<>();

        final DrinkAdapter adaptador = new DrinkAdapter(drinkArrayList, new DrinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DrinkREC j) {
                Intent intent  = new Intent(getContext(), viewDrinkActivity.class );

                intent.putExtra("id", j.getIdDrink());

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));


        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DrinkService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        binding.busqueda.setText("");

        DrinkService servicioPlantAPI = retrofit.create(DrinkService.class);

        Call<DrinksREC> callPlantas = servicioPlantAPI.getAlcoholDrinks();

        callPlantas.enqueue(new Callback<DrinksREC>() {
            @Override
            public void onResponse(Call<DrinksREC> call, Response<DrinksREC> response) {
                switch(response.code()) {
                    case 200:
                        DrinksREC resultado = response.body();
                        bebidas = resultado.getDrinks();

                        adaptador.setDatos(bebidas);
                        adaptador.notifyDataSetChanged();

                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<DrinksREC> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "Error de carga. Compruebe su conexión", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        binding.buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DrinksREC> callPlantas;

                if(binding.busqueda.getText().toString().isEmpty()){
                    callPlantas = servicioPlantAPI.getAlcoholDrinks();
                }
                else{
                    callPlantas= servicioPlantAPI.getFavs(binding.busqueda.getText().toString());
                }


                callPlantas.enqueue(new Callback<DrinksREC>() {
                    @Override
                    public void onResponse(Call<DrinksREC> call, Response<DrinksREC> response) {
                        switch(response.code()) {
                            case 200:
                                DrinksREC resultado = response.body();
                                bebidas = resultado.getDrinks();

                                adaptador.setDatos(bebidas);
                                adaptador.notifyDataSetChanged();

                                break;
                            case 401:
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<DrinksREC> call, Throwable t) {
                        Toast toast = Toast.makeText(getContext(), "Error de carga. Compruebe su conexión", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void busqueda(EditText busqueda){

    }
}