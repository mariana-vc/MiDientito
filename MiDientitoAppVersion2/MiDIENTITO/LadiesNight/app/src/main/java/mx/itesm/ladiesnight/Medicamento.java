package mx.itesm.ladiesnight;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class Medicamento extends ListActivity {

    private ListView listView = null;
    String id;

    private ArrayList<Drug> drugs=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        DrugListHandler drugListHandler;
        drugListHandler = new DrugListHandler(new Drug());
        drugListHandler.executeSearch();

        drugs =  drugListHandler.getList();



        DrugAdapter adapter = new DrugAdapter(Medicamento.this, R.layout.renglon_layout2, drugs); //contiene los datos y los controla
        setListAdapter(adapter);


    }

    public void mapaMedicamentos(View v){
        Intent it= new Intent(this, Medicamentos.class);
        startActivity(it);
    }
}

