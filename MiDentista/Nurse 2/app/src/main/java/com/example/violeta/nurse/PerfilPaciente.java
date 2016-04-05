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
    String id="123";
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

        id_paciente = (EditText) findViewById(R.id.et_dni);

        String nombre= "Mariana";
        et1.setText(nombre);

        String edad = "22";
        et2.setText(edad);

        String numSeg = "123";
        et3.setText(numSeg);

        String tipoSangre = "O+";
        et4.setText(tipoSangre);

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