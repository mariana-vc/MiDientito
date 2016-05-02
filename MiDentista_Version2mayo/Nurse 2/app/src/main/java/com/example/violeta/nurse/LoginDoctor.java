package com.example.violeta.nurse;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Build;
import android.preference.EditTextPreference;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class LoginDoctor extends AppCompatActivity {
    EditText usuario,password;
    private ProgressDialog progressDialog;
    Button registrarse;

    EditText id_paciente;


    ShowcaseView sv;
    Button boton, boton2;
    ImageView image;
    String id;
    private int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);


        id_paciente = (EditText) findViewById(R.id.txtusuario);
        password = (EditText) findViewById(R.id.txtpassword);
        boton = (Button) findViewById(R.id.login);
        image= (ImageView) findViewById(R.id.imageView);
        boton2=(Button) findViewById(R.id.registrarse);

        progressDialog = new ProgressDialog(LoginDoctor.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Espera un momento...");

        usuario = (EditText) findViewById(R.id.txtusuario);
        password = (EditText) findViewById(R.id.txtpassword);


        Button boton = (Button) findViewById(R.id.login);


        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    execute("no hay valor");

                }else{
                    progressDialog.show();
                    login();
                }
            }
        });

    }
    public void login(){
        ParseUser.logInInBackground(usuario.getText().toString(), password.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    progressDialog.dismiss();
                    Intent i = new Intent(LoginDoctor.this, MenuOpciones.class);
                    startActivity(i);
                } else {
                    progressDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(LoginDoctor.this,"Algo salió mal, el usuario no existe",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void execute(String request){
        System.out.println("Peticion en ejecucion: " + request);
    }

}
