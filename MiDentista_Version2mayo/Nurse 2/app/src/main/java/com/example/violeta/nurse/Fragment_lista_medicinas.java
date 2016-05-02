package com.example.violeta.nurse;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class Fragment_lista_medicinas extends Fragment implements AdapterView.OnItemClickListener {


    //private AbsListView listView;
    private ArrayAdapter <String> mAdapter;
    ArrayList medicinas;
    String idPaciente;

    private ListView mlistView = null;
    private ArrayList<String> nombres;
    ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lista_medicinas, container, false);

        nombres = new ArrayList<String>();
        Button save = (Button)view.findViewById(R.id.save);
        medicinas = new ArrayList();

        mlistView = (ListView) view.findViewById(R.id.list);
        mlistView.setOnItemClickListener(this);


        idPaciente = getArguments().getString("id");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Medicamento");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : list) {
                        String nombreMedicina = parseObject.getString("nombre");
                        Log.v("entro",nombreMedicina);
                        nombres.add(nombreMedicina);
                    }
                    for(int i=0; i<nombres.size();i++){
                        Log.v("nombre",nombres.get(i).toString());


                    }
                    if(nombres != null) {
                        Log.v("entro","entro");
                        mAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, nombres);
                        mlistView.setAdapter(mAdapter);
                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medicinas.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Tu receta esta vacia",Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("medicinas",medicinas);
                    bundle.putString("id",idPaciente);
                    String fragmentStack = "GuardaReceta";
                    Fragment fragment = new GuardarReceta();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.content_layout, fragment);
                    ft.addToBackStack(fragmentStack);
                    ft.commit();
                }
            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String medicina  = nombres.get(position).toString();
        Log.v("Medicina",medicina);
        medicinas.add(medicina);
        for(int i =0;i<medicinas.size();i++){
            Log.v("MEDICINA FOR",medicinas.get(i).toString());
        }
        Toast.makeText(getActivity().getApplicationContext(),medicina+" agregado a la receta",Toast.LENGTH_LONG).show();
    }

}
