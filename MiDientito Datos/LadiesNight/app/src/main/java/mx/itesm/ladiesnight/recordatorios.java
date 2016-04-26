package mx.itesm.ladiesnight;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class recordatorios extends ListActivity {

    private ListView listView = null;
    String id;
    ProgressDialog mProgressDialog;

    private ArrayList<Reminder> reminders=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = (ListView) findViewById(R.id.listView);
        new RemoteDataTask().execute();

    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(recordatorios.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Recordatorios");
            // Set progressdialog message
            mProgressDialog.setMessage("Buscando...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            Intent intent = getIntent();
            id = intent.getStringExtra("id");



            ReminderListHandler reminderListHandler;
            reminderListHandler = new ReminderListHandler(new Reminder(), id);
            reminderListHandler.executeSearch();

            reminders =  reminderListHandler.getList();



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ReminderAdapter adapter=new ReminderAdapter(recordatorios.this, R.layout.renglon_layout,reminders); //contiene los datos y los controla
            setListAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


}

