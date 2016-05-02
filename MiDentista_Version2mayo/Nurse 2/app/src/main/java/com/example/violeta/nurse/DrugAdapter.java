package com.example.violeta.nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DrugAdapter extends ArrayAdapter<Drug> {
    private Context contexto;
    private ArrayList<Drug> datos;


    public DrugAdapter(Context contexto, int recurso, ArrayList<Drug> drugs){
        super(contexto, recurso, drugs); //para ArrayAdapter
        this.contexto=contexto;
        datos=drugs;
    }
    public DrugAdapter(Context context, ArrayList<Drug> drugs) {
        super(context, 0, drugs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //convertView es la lista que está reconstruyendo
        //Drug drugg = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(contexto); //contruyo el renglon
        View v = inflater.inflate(R.layout.renglon_layout2, null);
        TextView largeText= (TextView)v.findViewById(R.id.textView);
        TextView smallText= (TextView)v.findViewById(R.id.textView2);

        Drug drug = datos.get(position);

        largeText.setText(drug.nombre);
        smallText.setText(drug.descripcion);

        return v;
    }
}

