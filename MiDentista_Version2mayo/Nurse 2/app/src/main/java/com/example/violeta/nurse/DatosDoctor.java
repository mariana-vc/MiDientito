package com.example.violeta.nurse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class DatosDoctor extends AppCompatActivity {
    EditText usuario, password, confirmarPassword, correo, telefono;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_doctor);

        progressDialog = new ProgressDialog(DatosDoctor.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Espera un momento...");

        usuario = (EditText) findViewById(R.id.txtusuario);
        password = (EditText) findViewById(R.id.txtpassword);


        confirmarPassword = (EditText) findViewById(R.id.ConfirmarPassword);
        correo = (EditText) findViewById(R.id.correo);

        telefono = (EditText) findViewById(R.id.Telefono);


        Button boton = (Button) findViewById(R.id.registrarse);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || confirmarPassword.getText().toString().equals("")
                        || correo.getText().toString().equals("")
                        || telefono.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (!password.getText().toString().
                            equals(confirmarPassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Los passwords no coinciden", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.show();
                        registro();
                    }
                }
            }
        });

    }


    public void registro() {
        ParseUser user = new ParseUser();
        user.get("objectId");
        user.setUsername(usuario.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(correo.getText().toString());
        user.put("Telefono", telefono.getText().toString());


        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    Toast.makeText(DatosDoctor.this, "Gracias por registrarte", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DatosDoctor.this, LoginDoctor.class);
                    startActivity(i);
                } else {
                    if (e.getCode() == 202) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                DatosDoctor.this,
                                "Este usuario ya existe. \n Por favor escoge otro.",
                                Toast.LENGTH_LONG).show();
                    } else if (e.getCode() == 203) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                DatosDoctor.this,
                                "Este Correo ya existe. \n Por favor escoge otro.",
                                Toast.LENGTH_LONG).show();
                    } else if (e.getCode() == 100) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                DatosDoctor.this,
                                "Hubo un error de conexión \n Por favor intenta más tarde.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(DatosDoctor.this, "Algo salió mal, por favor intenta de nuevo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}





