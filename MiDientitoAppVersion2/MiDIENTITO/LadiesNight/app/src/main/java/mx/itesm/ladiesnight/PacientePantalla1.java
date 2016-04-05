package mx.itesm.ladiesnight;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;

import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

public class PacientePantalla1 extends Activity {

    public ImageButton makeCall;
    private SensorManager sensorManager;
    private TextView count;

    private long timestamp;
    private TextView textViewStepCounter;
    private Thread detectorTimeStampUpdaterThread;
    private Handler handler;
    private boolean isRunning = true;
    private TextView textViewStepDetector;
    String id;
    Animation animTranslate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_pantalla1);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        animTranslate= AnimationUtils.loadAnimation(this, R.anim.anim_translate);


        makeCall = (ImageButton) findViewById(R.id.emergencia);
        makeCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                arg0.startAnimation(animTranslate);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Paciente");
               /* query.whereEqualTo("objectId", id);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject list, com.parse.ParseException e) {
                        // TODO Auto-generated method stub

                        if (e == null) {
                            if (list != null) {
                                Toast.makeText(PacientePantalla1.this, "Numero encontrado", Toast.LENGTH_SHORT).show();

                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                String numero=list.getString("ContactoDeEmergencia");
                                callIntent.setData(Uri.parse("tel:" + numero));
                                startActivity(callIntent);

                            } else {
                                Toast.makeText(PacientePantalla1.this, "No tiene numero de dentista", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PacientePantalla1.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paciente_pantalla1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /////////////////////////////////ONCLICK BUTTONS////////////////////////////////

    public void medicamentos(View v){
        v.startAnimation(animTranslate);
        Intent it= new Intent(this, Medicamento.class);
        it.putExtra("id", id);
        startActivity(it);
    }
    public void recordatorio(View v){
        v.startAnimation(animTranslate);
        Intent it= new Intent(this, recordatorios.class);
        it.putExtra("id", id);
        startActivity(it);
    }
    public void unity(View v){
        v.startAnimation(animTranslate);
        Intent it= new Intent(this, Historial.class);
        startActivity(it);
    }


}
