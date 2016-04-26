package com.example.violeta.nurse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PerfilPaciente extends AppCompatActivity {
    String id;
     EditText et1, et2, et3, et4;
    EditText id_paciente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_paciente);
        et1 = (EditText) findViewById(R.id.nombre);
        et2 = (EditText) findViewById(R.id.edad);
        et3 = (EditText) findViewById(R.id.et_dni);
        et4 = (EditText) findViewById(R.id.peso);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        id_paciente = (EditText) findViewById(R.id.et_dni);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Paciente");
        query.whereEqualTo("objectId", id);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : list) {
                        //parseObject.getString Jalas los datos de cada columna
                        String nombre = parseObject.getString("Nombre");
                        et1.setText(nombre);

                        String edad = parseObject.getString("Edad");
                        et2.setText(edad);

                        String numSeg = parseObject.getString("NumeroSeguro");
                        et3.setText(numSeg);

                        String tipoSangre = parseObject.getString("TipoSanguineo");
                        et4.setText(tipoSangre);


                    }


                }
            }
        });

    }


    public void receta(View v){
        Intent it= new Intent(this, HacerReceta.class);
        it.putExtra("id", id);
        startActivity(it);
    }

    public void historial(View v){
        Intent it= new Intent(this, Historial.class);
        startActivity(it);
    }


}