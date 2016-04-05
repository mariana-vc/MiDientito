package com.example.violeta.nurse;

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

        // select statement uses fields
        final private String fields = "nombre,descripcion "; //columnas

        public ArrayList<Drug> executeSelect(Drug drug) {
            ArrayList<Drug> list = null;
            ResultSet rs = null;

            list = prepareResult(rs);


            return list;
        }

        private ArrayList<Drug> prepareResult(ResultSet rs){
            ArrayList<Drug> list = new ArrayList<Drug>();
            for(int i=0; i<=5;i++){
                Drug drug = new Drug("Aspirinas","Medicamento para blablablá");
                list.add(drug);

            }

            return list;
        }

    }
