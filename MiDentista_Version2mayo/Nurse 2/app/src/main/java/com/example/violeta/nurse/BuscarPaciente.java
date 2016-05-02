package com.example.violeta.nurse;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import android.app.ProgressDialog;

import java.util.List;


public class BuscarPaciente extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginPrefs";

    EditText usuario,password;
    private ProgressDialog progressDialog;
    String id="123";
    EditText id_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_paciente);

        id_paciente = (EditText) findViewById(R.id.segSoc);

        progressDialog = new ProgressDialog(BuscarPaciente.this);
        progressDialog.setTitle("Buscando");
        progressDialog.setMessage("Espera un momento...");

        Button boton = (Button) findViewById(R.id.hecho);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_paciente.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    encontrarPaciente();
                    progressDialog.show();
                }
            }
        });

    }
//---------------------------------------------------------------------------------------------------------------------->
    public void encontrarPaciente() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Paciente");
        query.whereEqualTo("NumeroSeguro", id_paciente.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject scoreList, com.parse.ParseException e) {
                // TODO Auto-generated method stub
                progressDialog.dismiss();
                if (e == null) {
                    if (scoreList != null) {
                        Toast.makeText(BuscarPaciente.this, "Paciente encontrado", Toast.LENGTH_SHORT).show();
                        id = scoreList.getObjectId();

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("id", id);
                        editor.commit();

                        Intent intent = new Intent(BuscarPaciente.this, PerfilPaciente.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BuscarPaciente.this, "No hay pacientes registrados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuscarPaciente.this, "Paciente no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}


