package mx.itesm.ladiesnight;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Splash extends AppCompatActivity {

    private final int SPLASH= 2000;
    private PendingIntent pendingIntent;
    public static final String MY_PREFS_NAME = "LoginPrefs";
    Calendar cal;
    Date fecha;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cal = Calendar.getInstance();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);
        id = prefs.getString("id", null);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(Splash.this, OnceLogin.class);
                startActivity(it);
            }
        }, SPLASH);*/

        ////////////////////////////////
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);

        if (isFirstRun)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent it = new Intent(Splash.this, OnceLogin.class);
                    startActivity(it);
                }
            }, SPLASH);

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent it = new Intent(Splash.this,  OnceLogin.class);
                    it.putExtra("id", id);
                    startActivity(it);
                }
            }, SPLASH);
        }

        if (id != null) {
            //////////////////////////////////////
            ParseQuery query = new ParseQuery("Recordatorio");
            query.whereEqualTo("id_p", id);//current user
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    // TODO Auto-generated method stub
                    if (e == null) {
                        for (ParseObject j : list) {
                            fecha = j.getDate("fecha");
                            cal.setTime(fecha);
                            int month = cal.get(Calendar.MONTH);
                            int year = cal.get(Calendar.YEAR);
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            int hour = cal.get(Calendar.HOUR_OF_DAY);
                            int min = cal.get(Calendar.MINUTE);

                            Log.i("MES", String.valueOf(month));

                            Calendar calendar = Calendar.getInstance();

                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.DAY_OF_MONTH, day);

                            calendar.set(Calendar.HOUR_OF_DAY, hour);
                            calendar.set(Calendar.MINUTE, min);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.AM_PM, Calendar.PM);

                            Intent myIntent = new Intent(Splash.this, MyReceiver.class);
                            pendingIntent = PendingIntent.getBroadcast(Splash.this, 0, myIntent, 0);

                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

                        }
                    } else {
                        Log.i("aqui", "NO HAY RECORDATORIOS");
                        Calendar calendar = Calendar.getInstance();

                        calendar.set(Calendar.MONTH, 10);
                        calendar.set(Calendar.YEAR, 2015);
                        calendar.set(Calendar.DAY_OF_MONTH, 22);

                        calendar.set(Calendar.HOUR_OF_DAY, 21);
                        calendar.set(Calendar.MINUTE, 15);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.AM_PM, Calendar.PM);

                        Intent myIntent = new Intent(Splash.this, MyReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(Splash.this, 0, myIntent, 0);
                    }
                }
            });

            ///////////////////////////////////////////
        } else {
            Log.e("SIGUE", "NO ENCONTRO ID EN PREFS");
        }

    }


}
