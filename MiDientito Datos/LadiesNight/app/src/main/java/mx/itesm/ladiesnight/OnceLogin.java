package mx.itesm.ladiesnight;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class OnceLogin extends Activity {
    public static final String PREFS_NAME = "LoginPrefs";
    private int counter = 0;
    //private String id;

    EditText id_paciente;
    private ProgressDialog progressDialog;
    ParseObject paciente = new ParseObject("Paciente");
    ShowcaseView sv;
    Button boton;
    ImageView image;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_once_login);

        id_paciente = (EditText) findViewById(R.id.txtusuario);
        boton = (Button) findViewById(R.id.login);
        image= (ImageView) findViewById(R.id.imageView);

        sv=new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.imageView)))
                .setStyle(R.style.CustomShowcaseTheme2)
                .setContentTitle("Bienvenido a MiDientito")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (counter) {
                            case 0:
                                sv.setShowcase(new ViewTarget(id_paciente), true);
                                sv.setContentTitle("Escribe aquí tu número de ingreso");
                                sv.setContentText("O solicita a tu dentista un identificador");
                                setAlpha(0.4f, boton, image);
                                break;

                            case 1:
                                setAlpha(1.0f, boton);
                                setAlpha(0.4f, id_paciente, image);
                                sv.setShowcase(new ViewTarget(boton), true);
                                sv.setContentTitle("Da click aquí");
                                sv.setContentText("¡¡ Y así entrarás a la aplicación !!");
                                sv.setButtonText("Cerrar");
                                break;
                            case 2:
                                sv.hide();
                                setAlpha(1.0f, id_paciente, boton, image);
                                break;
                        }
                        counter++;
                    }
                })
                .build();
        sv.setButtonText("Siguiente");

        progressDialog = new ProgressDialog(OnceLogin.this);
        progressDialog.setTitle("Buscando");
        progressDialog.setMessage("Espera un momento...");

        id_paciente = (EditText) findViewById(R.id.txtusuario);
        Button boton = (Button) findViewById(R.id.login);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id_paciente.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                    progressDialog.show();

                }
            }
        });
    }

    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }


    public void login() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Paciente");
        query.whereEqualTo("NumeroSeguro", id_paciente.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject scoreList, com.parse.ParseException e) {
                // TODO Auto-generated method stub
                progressDialog.dismiss();
                if (e == null) {
                    if (scoreList != null) {
                        Toast.makeText(OnceLogin.this, "Paciente encontrado", Toast.LENGTH_SHORT).show();
                        id = scoreList.getObjectId();

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("id", id);
                        editor.commit();

                        Intent intent = new Intent(OnceLogin.this, PacientePantalla1.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(OnceLogin.this, "No hay pacientes registrados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OnceLogin.this, "Paciente no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void execute(String request){
        System.out.println("Peticion en ejecucion: " + request);
    }

}