package com.example.violeta.nurse;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class HistorialDao {

    final private String tableName = "DRUG";
    private ArrayList<Hist> historial_list= new ArrayList<Hist>();

    // select statement uses fields
    final private String fields = "nombre,descripcion "; //columnas

    public ArrayList<Hist> executeSelect(Hist hist, String id) {
        ArrayList<Hist> list = null;
        ResultSet rs = null;
        list = prepareResult(rs, id);

        return list;
    }

    private ArrayList<Hist> prepareResult(ResultSet rs, String id){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Medicamento");
        //        query.whereEqualTo("id_p", id_paciente.getText().toString());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : list) {
                        /*IMPORTANTE !!!!!!!
                        Cambiar tabla, poner el equals id, cambiar getString(nombre) y getString(Descripcion)
                         */
                        String historia = parseObject.getString("nombre");
                        String fecha = parseObject.getString("descripcion");
                        historial_list.add(new Hist(historia,fecha));
                    }

                    for (int i = 0; i < historial_list.size(); i++) {
                        Log.v("nombre", historial_list.get(i).toString());


                    }
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    System.out.println("nooo");
                }

            }
        });


        return historial_list;
    }
}
