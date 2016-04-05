package mx.itesm.ladiesnight;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by beeme on 03/04/2016.
 */
public class HistoriaDAO {
        final private String tableName = "DRUG";

        // select statement uses fields
        final private String fields = "nombre,descripcion "; //columnas

        public ArrayList<Historia> executeSelect(Historia historia) {
            ArrayList<Historia> list = null;
            ResultSet rs = null;


/*
            Statement stmt= null;
            Connection con = getConnection();
            StringBuffer selectStatement =   new StringBuffer();
            selectStatement.append("SELECT "+ fields +
                    " FROM " + tableName + "where 1=1");


            if (historia.getNombre() != null) {
                selectStatement.append (" AND nombre= '" +
                        historia.getNombre() + "'");
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

        private ArrayList<Historia> prepareResult(ResultSet rs){
            ArrayList<Historia> list = new ArrayList<Historia>();
            for(int i=0; i<=5;i++){
                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = formatter.format(dt);

                Historia historia = new Historia("Historia"+i,fecha);
                list.add(historia);

            }
            /*
            try {
                while(rs.next()) {

                    Historia historia = new
                            Historia(rs.getString(0),rs.getString(1));

                    list.add(historia);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            return list;
        }

    }
