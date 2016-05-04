package com.example.violeta.nurse;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beeme on 03/04/2016.
 */
public class DrugDAO{
        final private String tableName = "DRUG";
        private ArrayList<Drug> nombres;


    // select statement uses fields
        final private String fields = "nombre,descripcion "; //columnas

        public ArrayList<Drug> executeSelect(Drug drug) {
            ArrayList<Drug> list = null;
            ResultSet rs = null;

            list = prepareResult(rs);


            return list;
        }

        private ArrayList<Drug> prepareResult(ResultSet rs){
            nombres = new ArrayList<>();


            ParseQuery<ParseObject> query = ParseQuery.getQuery("Medicamento");
            query.orderByAscending("nombre");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, com.parse.ParseException e) {

                    if (e == null) {
                        for (ParseObject parseObject : list) {
                            String nombreMedicina = parseObject.getString("nombre");
                            String desc = parseObject.getString("descripcion");
                            Log.v("entro", nombreMedicina);
                            nombres.add(new Drug(nombreMedicina,desc));
                        }
                        for(int i=0; i<nombres.size();i++){
                            Log.v("nombre",nombres.get(i).toString());


                        }

                    } else {
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }

                }
            });

            return nombres;
        }


    /*
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
                        mListView.setAdapter(mAdapter);
                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }
        });
     */

    }
