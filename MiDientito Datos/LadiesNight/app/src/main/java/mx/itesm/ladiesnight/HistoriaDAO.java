package mx.itesm.ladiesnight;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by beeme on 03/04/2016.
 */
public class HistoriaDAO {
        final private String tableName = "DRUG";

        // select statement uses fields
        final private String fields = "nombre,descripcion "; //columnas
        String id;
        List<ParseObject> ob;

    public HistoriaDAO(String id) {
        this.id = id;
    }

    public ArrayList<Historia> executeSelect(Historia historia) {
            ArrayList<Historia> list = null;
            ResultSet rs = null;
            list = prepareResult(rs);

            return list;
        }

        private ArrayList<Historia> prepareResult(ResultSet rs){
            ArrayList<Historia> historias = new ArrayList<Historia>();
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
                query.orderByAscending("nombre");
                ob = query.find();
                for (ParseObject rec : ob) {
                    Date dt = rec.getDate("fecha");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String fecha = formatter.format(dt);
                    Historia t = new Historia(rec.getString("nombre"), fecha);
                    historias.add(t);
                }
            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }
            return historias;
        }

    }
