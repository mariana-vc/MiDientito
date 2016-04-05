package mx.itesm.ladiesnight;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by beeme on 03/04/2016.
 */
public class ReminderDAO {
        final private String tableName = "DRUG";

        // select statement uses fields
        final private String fields = "nombre,descripcion "; //columnas

        public ArrayList<Reminder> executeSelect(Reminder reminder) {
            ArrayList<Reminder> list = null;
            ResultSet rs = null;


/*
            Statement stmt= null;
            Connection con = getConnection();
            StringBuffer selectStatement =   new StringBuffer();
            selectStatement.append("SELECT "+ fields +
                    " FROM " + tableName + "where 1=1");


            if (reminder.getNombre() != null) {
                selectStatement.append (" AND nombre= '" +
                        reminder.getNombre() + "'");
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

        private ArrayList<Reminder> prepareResult(ResultSet rs){
            ArrayList<Reminder> list = new ArrayList<Reminder>();
            for(int i=0; i<=5;i++){
                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = formatter.format(dt);

                Reminder reminder = new Reminder("Aspirinas",fecha);
                list.add(reminder);

            }
            /*
            try {
                while(rs.next()) {

                    Reminder reminder = new
                            Reminder(rs.getString(0),rs.getString(1));

                    list.add(reminder);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            return list;
        }

    }
