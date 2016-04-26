package mx.itesm.ladiesnight;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Historial extends Activity implements AdapterView.OnItemClickListener {
    ListView listView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter;
        ArrayList<Historia> listItems=new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        HistoriaListHandler historiaListHandler;
        historiaListHandler = new HistoriaListHandler(new Historia(), id);
        historiaListHandler.executeSearch();

        listItems =  historiaListHandler.getList();


        List<String> strings = new ArrayList<String>();
        for (Object object : listItems) {
            strings.add(object != null ? object.toString() : null);
        }



        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    /*
     * Parameters:
        adapter - The AdapterView where the click happened.
        view - The view within the AdapterView that was clicked
        position - The position of the view in the adapter.
        id - The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();
    }
}
