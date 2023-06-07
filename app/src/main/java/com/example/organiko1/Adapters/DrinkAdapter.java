package com.example.organiko1.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.organiko1.Class.DrinkRec.DrinkREC;
import com.example.organiko1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>  {

/*Arraylist donde almaceno los datos que se van a mostrar en el RecylerView*/
private List<DrinkREC> datos;
private final OnItemClickListener listener;

public interface OnItemClickListener {
    void onItemClick(DrinkREC item);
}
    public DrinkAdapter(List<DrinkREC> drinkList , OnItemClickListener listener) {
        this.datos = drinkList;
        this.listener = listener;
    }

    public void setDatos(List<DrinkREC> drinkList){
        this.datos = drinkList;
    }

/* Incluyo el Viewholder en el Adapter */
public static class DrinkViewHolder
        extends RecyclerView.ViewHolder {

    /* Como atributos se incluyen los elementos que van a referenciar a los elementos de la vista*/
    private TextView name;
    private ImageView foto;



    /*constructor con parámetro de la vista*/
    public DrinkViewHolder(View itemView) {
        super(itemView);
        // Asocia los atributos a los widgets del layout de la vista
        name = (TextView)itemView.findViewById(R.id.nombrePlanta2);
        foto = (ImageView) itemView.findViewById(R.id.fotoPlanta);
    }

    /*Muestra los datos de coche en el item*/
    public void binddrink(final DrinkREC drinkREC, final OnItemClickListener listener) {
        name.setText(drinkREC.getStrDrink());
        Picasso.get().load(drinkREC.getStrDrinkThumb()).into(foto);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(drinkREC);
            }
        });

    }
}


    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        /*Crea la vista de un item y la "pinta"*/
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drink_vie, viewGroup, false);

        /* Crea un objeto de la clase CocheViewHolder, pasándole la vista anteriormente creada*/
        DrinkViewHolder notifiVH = new DrinkViewHolder(itemView);
        /* devuelve la vissta*/
        return notifiVH;
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder viewHolder, int pos) {
        DrinkREC notification = datos.get(pos);
        /* Llama a bindCoche, para que "pinte" los datos del coche que se le pasa como parámetro*/
        viewHolder.binddrink(notification, listener);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


}



