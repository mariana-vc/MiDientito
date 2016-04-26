package mx.itesm.ladiesnight;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Medicamento extends ListActivity {

    private static final String TAG = "Http Connection";
    private ListView listView = null;
    private ArrayAdapter arrayAdapter = null;
    private List<Drug> id_meds = new ArrayList<Drug>();
    String id, periodo;
    List<ParseObject> ob;

    private ArrayList<Drug> drugs=null;
    DrugAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        drugs = new ArrayList<Drug>();

        ParseQuery query = new ParseQuery("Paciente_medicamento");
        query.whereEqualTo("id_p", id);//current user

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    //Toast.makeText(Medicamento.this, "Exito", Toast.LENGTH_SHORT).show();
                    for (ParseObject j : list) {
                        Drug d = new Drug(j.getString("id_m"), j.getString("periodo"));
                        id_meds.add(d);
                    }

                    for (Drug id_med : id_meds) {
                        periodo = id_med.getDescripcion();
                        try {
                            ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("Medicamento");
                            query2.whereEqualTo("objectId", id_med.getNombre());
                            query2.orderByAscending("nombre");
                            ob = query2.find();
                            for (ParseObject med : ob) {
                                Drug t = new Drug(med.getString("nombre"), periodo);
                                drugs.add(t);
                            }
                        } catch (ParseException ex) {
                            Log.e("Error", ex.getMessage());
                            ex.printStackTrace();
                        }
                        Log.i("DRUGS", String.valueOf(drugs.size()));
                        DrugAdapter adapter = new DrugAdapter(Medicamento.this, R.layout.renglon_layout2, drugs); //contiene los datos y los controla
                        setListAdapter(adapter);
                    }
                } else {
                    Toast.makeText(Medicamento.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void mapaMedicamentos(View v){
        Intent it= new Intent(this, Medicamentos.class);
        startActivity(it);
    }
}

