package mx.itesm.ladiesnight;

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


/*
            Statement stmt= null;
            Connection con = getConnection();
            StringBuffer selectStatement =   new StringBuffer();
            selectStatement.append("SELECT "+ fields +
                    " FROM " + tableName + "where 1=1");


            if (drug.getNombre() != null) {
                selectStatement.append (" AND nombre= '" +
                        drug.getNombre() + "'");
            }


            try {
                stmt = con.prepareStatement(selectStatement);
                stmt.setString(1, resourceID);
                ResultSet rs = stmt.executeQuery();
                list = prepareResult(rs);
                stmt.close();
            }
            finally {
                con.close();
            }*/
            list = prepareResult(rs);


            return list;
        }

        private ArrayList<Drug> prepareResult(ResultSet rs){
            ArrayList<Drug> list = new ArrayList<Drug>();
            for(int i=0; i<=5;i++){
                Drug drug = new Drug("Aspirinas","Medicamento para blablablá");
                list.add(drug);

            }
            /*
            try {
                while(rs.next()) {

                    Drug drug = new
                            Drug(rs.getString(0),rs.getString(1));

                    list.add(drug);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            return list;
        }

    }
