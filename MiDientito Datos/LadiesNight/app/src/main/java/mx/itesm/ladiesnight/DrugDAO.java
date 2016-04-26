package mx.itesm.ladiesnight;

import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.FindCallback;
import com.parse.GetCallback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DrugDAO{

        private List<Drug> id_meds = new ArrayList<Drug>();
        String id, periodo;
        List<ParseObject> ob;

        ArrayList<Drug> lista;
        ArrayList<Drug> listD=null;
        ArrayList<Drug> listDrugs=null;


    public DrugDAO(String id) {
        this.id=id;
    }

    public ArrayList<Drug> executeSelect(Drug drug) {
            lista = null;
            ResultSet rs = null;
            lista = prepareResult(rs);

            return lista;
        }

        private ArrayList<Drug> prepareResult(ResultSet rs){
            listD = new ArrayList<Drug>();
            listDrugs = new ArrayList<Drug>();
            /*for(int i=0; i<=5;i++){
                Drug drug = new Drug("Aspirinas","Medicamento para blablablá");
                listD.add(drug);

            }*/

            //lista = new ArrayList<Drug>();

            ParseQuery query = new ParseQuery("Paciente_medicamento");
            query.whereEqualTo("id_p", id);//current user
            Log.i("id", id);

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    // TODO Auto-generated method stub
                    if (e == null) {
                        for (ParseObject j : list) {
                            Drug d = new Drug(j.getString("id_m"), j.getString("periodo"));
                            id_meds.add(d);
                            Log.i("coincide", j.getObjectId());
                        }

                        for (Drug id_med : id_meds) {
                            periodo = id_med.getDescripcion();
                            try {
                                ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("Medicamento");
                                query2.whereEqualTo("objectId", id_med.getNombre());
                                query2.orderByAscending("nombre");
                                ob = query2.find();
                                for (ParseObject med : ob) {
                                    //Drug dr = new Drug(med.getString("nombre"), periodo);
                                    Drug dr = new Drug("nombre", "periodo");
                                    listD.add(dr);
                                    Log.i("DRUGS", med.getString("nombre"));
                                }

                            } catch (ParseException ex) {
                                Log.e("Error", ex.getMessage());
                                ex.printStackTrace();
                            }
                            listDrugs = listD;

                            Log.i("RETURN", listDrugs.toString());
                        }
                    } else {
                        //Toast.makeText(Medicamento.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("Error", e.getMessage());
                    }
                }
            });

            return listDrugs;
        }

    }
