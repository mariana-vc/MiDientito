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

    private ListView listView = null;
    private ArrayList<Drug> nombres;
    ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lista_medicinas, container, false);

        nombres = new ArrayList<Drug>();
        Button save = (Button)view.findViewById(R.id.save);
        medicinas = new ArrayList();

        listView = (ListView) view.findViewById(R.id.list);


        listView.setOnItemClickListener(this);
        idPaciente = getArguments().getString("id");

        /*nombres.add("Medicina 1");
        nombres.add("Medicina 2");
        nombres.add("Medicina 3");
        nombres.add("Medicina 4");
        nombres.add("Medicina 5");
        nombres.add("Medicina 6");
        nombres.add("Medicina 7");
        nombres.add("Medicina 8");
        nombres.add("Medicina 9");
        nombres.add("Medicina 10");
        nombres.add("Medicina 11");
        nombres.add("Medicina 12");

        if(nombres != null) {
            mAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, nombres);
            mListView.setAdapter(mAdapter);
        }*/

        new RemoteDataTask().execute();

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

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            DrugListHandler drugListHandler;
            drugListHandler = new DrugListHandler(new Drug());
            drugListHandler.executeSearch();
            nombres =  drugListHandler.getList();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            //SearchListAdapter adapter = new SearchListAdapter(this, getActivity(), rowItems(your data to inflate in adapter));
            //listView.setAdapter(adapter);

            DrugAdapter adapter = new DrugAdapter(getActivity().getApplicationContext(), R.layout.renglon_layout2, nombres); //contiene los datos y los controla
            listView.setAdapter(adapter);

            // Close the progressdialog
            //mProgressDialog.dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String medicina  = nombres.get(position).toString();
        medicinas.add(medicina);
        Toast.makeText(getActivity().getApplicationContext(),"Medicina agregada a la receta",Toast.LENGTH_LONG).show();
    }


}
