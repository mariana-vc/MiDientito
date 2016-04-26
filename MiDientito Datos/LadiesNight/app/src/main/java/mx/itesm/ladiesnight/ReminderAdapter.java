package mx.itesm.ladiesnight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarianaItzel on 27/08/2015.
 */
public class ReminderAdapter extends ArrayAdapter<Reminder> {
    private Context contexto;
    private ArrayList<Reminder> datos;


    public ReminderAdapter(Context contexto, int recurso, ArrayList<Reminder> tracks){
        super(contexto, recurso, tracks); //para ArrayAdapter
        this.contexto=contexto;
        datos=tracks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //convertView es la lista que está reconstruyendo

        LayoutInflater inflater = LayoutInflater.from(contexto); //contruyo el renglon
        View v = inflater.inflate(R.layout.renglon_layout, null);
        TextView largeText= (TextView)v.findViewById(R.id.textView);
        TextView smallText= (TextView)v.findViewById(R.id.textView2);

        Reminder reminder = datos.get(position);

        largeText.setText(reminder.nombre);
        smallText.setText(reminder.fecha);

        return v;
    }
}
