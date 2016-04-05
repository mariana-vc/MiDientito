package com.example.violeta.nurse;

import java.sql.ResultSet;
import java.util.ArrayList;


public class HistorialDao {

    final private String tableName = "DRUG";

    // select statement uses fields
    final private String fields = "nombre,descripcion "; //columnas

    public ArrayList<Hist> executeSelect(Hist hist) {
        ArrayList<Hist> list = null;
        ResultSet rs = null;
        list = prepareResult(rs);

        return list;
    }

    private ArrayList<Hist> prepareResult(ResultSet rs){
        ArrayList<Hist> list = new ArrayList<Hist>();
        for(int i=0; i<=5;i++){
            Hist hist = new Hist("Fecha: 22/03/2016","Revision general");
            list.add(hist);
        }
        return list;
    }
}
