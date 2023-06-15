package com.example.organiko1.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.organiko1.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TextView nombre;
    private ImageView foto;
    private List<DrinkREC> fav;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);


            binding = FragmentDashboardBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            fav = new ArrayList<>();

            nombre = root.findViewById(R.id.nombreUser);
            foto =  root.findViewById(R.id.fotoUser);

            RecyclerView recyclerView = root.findViewById(R.id.favoritos);

            final DrinkAdapter adaptador = new DrinkAdapter(fav, new DrinkAdapter.OnItemClickListener() {
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



            DrinkService servicioPlantAPI = retrofit.create(DrinkService.class);


      //      Call<DrinksREC> callPlantas = servicioPlantAPI();



        //    for(int i =0; i < )

         /*   callPlantas.enqueue(new Callback<DrinksREC>() {
                @Override
                public void onResponse(Call<DrinksREC> call, Response<DrinksREC> response) {
                    switch(response.code()) {
                        case 200:

                            DrinksREC resultado = response.body();
                            fav = resultado.getDrinks();

                            adaptador.setDatos(fav);
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
*/


        return root;
    }

}