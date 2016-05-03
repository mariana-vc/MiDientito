package com.example.violeta.nurse;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import android.app.ProgressDialog;
import android.text.InputFilter;

import java.util.ArrayList;


public class AgregarPaciente extends AppCompatActivity {
    EditText nombre, segSoc,edad, peso, estatura, telemergencia;
    RadioGroup radioGroup;
    RadioButton masc, fem;
    Spinner spinner, hemofilia, donador;
    CheckBox alergia,alergia2,alergia3;
    Button hecho;
    String seleccionado;
    private ProgressDialog progressDialog;
    ArrayList<Paciente> pacientes= new ArrayList<Paciente>();

    View rootView;
    String genero ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);
        nombre = (EditText)findViewById(R.id.name);
        segSoc = (EditText)findViewById(R.id.segSoc);
        edad = (EditText)findViewById(R.id.edad);
        peso = (EditText)findViewById(R.id.peso);
        estatura = (EditText)findViewById(R.id.estatura);
        telemergencia = (EditText)findViewById(R.id.telemergencia);
        //---------------------------------------------------------------------------------------->
        edad.setFilters(new InputFilter[]{new InputFilterMinMax("0", "120")});
        peso.setFilters(new InputFilter[]{new InputFilterMinMax("0", "400")});
        estatura.setFilters(new InputFilter[]{new InputFilterMinMax("20", "200")});

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        masc = (RadioButton)findViewById(R.id.masc);
        fem = (RadioButton)findViewById(R.id.fem);
        spinner = (Spinner)findViewById(R.id.spinner);
        hemofilia = (Spinner)findViewById(R.id.hemofilia);
        donador = (Spinner)findViewById(R.id.donador);
        alergia = (CheckBox)findViewById(R.id.alergia);
        alergia2 = (CheckBox)findViewById(R.id.alergia2);
        alergia3 = (CheckBox)findViewById(R.id.alergia3);
        hecho = (Button)findViewById(R.id.hecho);
        seleccionado ="";

        progressDialog = new ProgressDialog(AgregarPaciente.this);
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espera un momento...");


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (masc.isChecked()) {
                    genero = "Masculino";
                }
                if (fem.isChecked()) {
                    genero = "Femenino";
                }
            }
        });

        hecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alergia.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Alergia 1", Toast.LENGTH_SHORT).show();
                }
                if (alergia2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Alergia 2", Toast.LENGTH_SHORT).show();
                }
                if (alergia3.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Alergia 3", Toast.LENGTH_SHORT).show();
                }
                // ------------------------------------------------------------------------------------------>
                //Intent i = new Intent(AgregarPaciente.this, MenuOpciones.class);
                //startActivity(i);
            }
        });


        Button boton = (Button) findViewById(R.id.hecho);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equals("")
                        || segSoc.getText().toString().equals("")
                        || edad.getText().toString().equals("")
                        || peso.getText().toString().equals("")
                        || estatura.getText().toString().equals("")
                        || telemergencia.getText().toString().equals("")
                        || genero.equals("")
                        || seleccionado.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }  else {
                    progressDialog.show();
                    registro();
                }
            }

        });
    }

    public void registro() {


        ParseObject paciente= new ParseObject("Paciente");
        //paciente.("objectId");
        paciente.put("Nombre", nombre.getText().toString());
        paciente.put("NumeroSeguro", segSoc.getText().toString());
        paciente.put("Edad", edad.getText().toString());
        paciente.put("Peso", peso.getText().toString());
        paciente.put("Estatura", estatura.getText().toString());
        paciente.put("ContactoDeEmergencia", telemergencia.getText().toString());
        paciente.put("Sexo", genero);
        paciente.put("TipoSanguineo", seleccionado);
        paciente.put("Hemofilia", seleccionado);
        paciente.put("Donador", seleccionado);
        paciente.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    Toast.makeText(AgregarPaciente.this, "Gracias por registrarte", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AgregarPaciente.this, MenuOpciones.class);
                    startActivity(i);
                } else {
                    Toast.makeText(AgregarPaciente.this, "Noooo", Toast.LENGTH_LONG).show();
                }
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------->
        /*Paciente p1=new Paciente(nombre.getText().toString(), segSoc.getText().toString(), edad.getText().toString(), genero, seleccionado);
        pacientes.add(p1);

        progressDialog.dismiss();
        Toast.makeText(AgregarPaciente.this, "Paciente registrado", Toast.LENGTH_LONG).show();

        Intent i = new Intent(AgregarPaciente.this, MenuOpciones.class);
        startActivity(i);*/

    }



}






       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);
        nombre = (EditText)findViewById(R.id.name);
        segSoc = (EditText)findViewById(R.id.segSoc);
        edad = (EditText)findViewById(R.id.edad);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        masc = (RadioButton)findViewById(R.id.masc);
        fem = (RadioButton)findViewById(R.id.fem);
        spinner = (Spinner)findViewById(R.id.spinner);
        alergia = (CheckBox)findViewById(R.id.alergia);
        alergia2 = (CheckBox)findViewById(R.id.alergia2);
        alergia3 = (CheckBox)findViewById(R.id.alergia3);
        hecho = (Button)findViewById(R.id.hecho);
        seleccionado ="";

        progressDialog = new ProgressDialog(AgregarPaciente.this);
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espera un momento...");


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (masc.isChecked()) {
                    Log.i("Masc: ", "Masculino is checked");
                    genero = "Masculino";
                }
                if (fem.isChecked()) {
                    Log.i("Fem: ", "Fem is checked");
                    genero = "Femenino";
                }
            }
        });

        hecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AgregarPaciente.this, MenuOpciones.class);
                startActivity(i);
            }
        });


        Button boton = (Button) findViewById(R.id.hecho);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Paciente agregado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AgregarPaciente.this, MenuOpciones.class);
                startActivity(i);
            }

        });




    }





}*/
