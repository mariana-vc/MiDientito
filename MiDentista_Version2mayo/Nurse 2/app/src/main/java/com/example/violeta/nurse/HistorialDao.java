package com.example.violeta.nurse;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistorialDao {

    final private String tableName = "DRUG";


    // select statement uses fields
    final private String fields = "nombre,descripcion "; //columnas
    List<ParseObject> ob;
    String id;

    public HistorialDao(String id){
        this.id=id;
    }

    public ArrayList<Hist> executeSelect(Hist hist) {
        ArrayList<Hist> list = null;
        ResultSet rs = null;
        list = prepareResult(rs);

        System.out.println("id: "+ id);

        return list;
    }

    private ArrayList<Hist> prepareResult(ResultSet rs){
        /*historial_list=new ArrayList<>();
        historial_list.add(new Hist("Limpieza","15-04-2016"));
        historial_list.add(new Hist("Extracción tercer molar","20-04-2016"));*/
        ArrayList<Hist> historias = new ArrayList<Hist>();
        System.out.println("+++ " + id);
            /*for(int i=0; i<=5;i++){
                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = formatter.format(dt);

                Historia historia = new Historia("Historia"+i,fecha);
                list.add(historia);

            }*/
        try {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Recordatorio");
            query.whereEqualTo("id_p", id);
            query.orderByAscending("fecha");
            ob = query.find();
            for (ParseObject rec : ob) {
                Date dt = rec.getDate("fecha");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = formatter.format(dt);
                Hist t = new Hist(rec.getString("nombre"), fecha);
                historias.add(t);
                System.out.println("--> " + historias);
            }
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }

        System.out.println("***"+historias);
        return historias;


/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Medicamento");
        //        query.whereEqualTo("id_p", id_paciente.getText().toString());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : list) {
                        IMPORTANTE !!!!!!!
                        Cambiar tabla, poner el equals id, cambiar getString(nombre) y getString(Descripcion)

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
        });*/


        //return historial_list;
    }
}
