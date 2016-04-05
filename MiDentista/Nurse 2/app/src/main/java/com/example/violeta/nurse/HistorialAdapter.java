package com.example.violeta.nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HistorialAdapter extends ArrayAdapter<Hist> {
    private Context contexto;
    private ArrayList<Hist> datos;


    public HistorialAdapter(Context contexto, int recurso, ArrayList<Hist> historial){
        super(contexto, recurso, historial); //para ArrayAdapter
        this.contexto=contexto;
        datos=historial;
    }
    public HistorialAdapter(Context context, ArrayList<Hist> historial) {
        super(context, 0, historial);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //convertView es la lista que está reconstruyendo
        //Drug drugg = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(contexto); //contruyo el renglon
        View v = inflater.inflate(R.layout.renglon_layout2, null);
        TextView largeText= (TextView)v.findViewById(R.id.textView);
        TextView smallText= (TextView)v.findViewById(R.id.textView2);

        Hist historial = datos.get(position);

        largeText.setText(historial.nombre);
        smallText.setText(historial.descripcion);

        return v;
    }
}

