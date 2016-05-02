package com.example.violeta.nurse;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial extends ListActivity  {

    String id;
    private ListView listView = null;
    ArrayList<Hist> historial=new ArrayList<Hist>();

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_historial);
        //listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        new RemoteDataTask().execute();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Historial.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Historial");
            // Set progressdialog message
            mProgressDialog.setMessage("Buscando...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            HistorialListHandler historialListHandler;
            historialListHandler = new HistorialListHandler(new Hist(),id);
            historialListHandler.executeSearch(id);
            historial =  historialListHandler.getList();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            HistorialAdapter adapter = new HistorialAdapter(Historial.this, R.layout.renglon_layout2, historial); //contiene los datos y los controla
            setListAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
