package mx.itesm.ladiesnight;

import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReminderDAO {
        final private String tableName = "DRUG";
        final private String fields = "nombre,descripcion "; //columnas
    //private ArrayList<Reminder> reminders=null;
        String id;
        List<ParseObject> ob;

        public ReminderDAO(String id) {
        this.id = id;
    }


    public ArrayList<Reminder> executeSelect(Reminder reminder) {
            ArrayList<Reminder> list = null;
            ResultSet rs = null;
            list = prepareResult(rs);

            return list;
        }

        private ArrayList<Reminder> prepareResult(ResultSet rs){
            ArrayList<Reminder> reminders = new ArrayList<Reminder>();
            /*for(int i=0; i<=5;i++){
                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = formatter.format(dt);

                Reminder reminder = new Reminder("Aspirinas",fecha);
                list.add(reminder);
            }*/

            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Recordatorio");
                query.whereEqualTo("id_p", id);
                query.orderByAscending("nombre");
                ob = query.find();
                for (ParseObject rec : ob) {
                    Date dt = rec.getDate("fecha");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String fecha = formatter.format(dt);
                    Reminder t = new Reminder(rec.getString("nombre"), fecha);
                    reminders.add(t);
                }
            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }

            return reminders;
        }

    }
